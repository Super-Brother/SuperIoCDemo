package com.wenchao.library;

import android.app.Activity;
import android.view.View;

import com.wenchao.library.annotations.ContentView;
import com.wenchao.library.annotations.EventBase;
import com.wenchao.library.annotations.InjectView;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wenchao
 * @date 2019/7/14.
 * @time 14:00
 * description：
 */
public class InjectManager {

    public static void inject(Activity activity) {
        //布局的注入
        injectLayout(activity);
        //控件的注入
        injectViews(activity);
        //事件的注入
        injectEvents(activity);
    }

    /**
     * 布局的注入
     */
    private static void injectLayout(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        final ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView != null) {
            final int layoutId = contentView.value();

            //第一种写法
//            activity.setContentView(layoutId);

            //第二种写法
            try {
                final Method method = clazz.getMethod("setContentView", int.class);
                method.invoke(activity, layoutId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 控件的注入
     */
    private static void injectViews(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        final Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            final InjectView injectView = field.getAnnotation(InjectView.class);
            if (injectView != null) {
                final int viewId = injectView.value();
                try {
                    Method method = clazz.getMethod("findViewById", int.class);
                    final Object view = method.invoke(activity, viewId);
                    //设置访问权限
                    field.setAccessible(true);
                    //给控件赋值
                    field.set(activity, view);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 事件的注入
     */
    private static void injectEvents(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        final Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            //获取每个方法的所有注解
            final Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                final Class<? extends Annotation> annotationType = annotation.annotationType();
                if (annotationType != null) {
                    final EventBase eventBase = annotationType.getAnnotation(EventBase.class);
                    if (eventBase != null) {
                        final String listenerSetter = eventBase.listenerSetter();
                        final String callBackListener = eventBase.callBackListener();
                        final Class<?> listenerType = eventBase.listenerType();

                        try {
                            final Method valueMethod = annotationType.getDeclaredMethod("value");
                            int[] viewIds = (int[]) valueMethod.invoke(annotation);

                            ListenerInvocationHandler handler = new ListenerInvocationHandler(activity);
                            handler.addMethod(callBackListener, method);
                            Object listener = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class[]{listenerType}, handler);

                            for (int viewId : viewIds) {
                                final View view = activity.findViewById(viewId);
                                final Method setter = view.getClass().getMethod(listenerSetter, listenerType);
                                setter.invoke(view, listener);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
