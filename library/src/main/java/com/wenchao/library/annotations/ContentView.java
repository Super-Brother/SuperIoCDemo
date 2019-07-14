package com.wenchao.library.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wenchao
 * @date 2019/7/14.
 * @time 14:03
 * description：
 */
@Target(ElementType.TYPE)//该注解只能作用在类之上
@Retention(RetentionPolicy.RUNTIME)//jvm运行时通过反射获取
public @interface ContentView {

    int value();
}
