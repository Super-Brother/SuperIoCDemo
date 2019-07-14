package com.wenchao.library.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wenchao
 * @date 2019/7/14.
 * @time 15:13
 * description：
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventBase {

    String listenerSetter();

    Class<?> listenerType();

    String callBackListener();
}
