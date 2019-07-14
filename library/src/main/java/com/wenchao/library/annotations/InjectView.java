package com.wenchao.library.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wenchao
 * @date 2019/7/14.
 * @time 14:23
 * description：
 */
@Target(ElementType.FIELD)//该注解只能作用在类之上
@Retention(RetentionPolicy.RUNTIME)//jvm运行时通过反射获取
public @interface InjectView {

    int value();
}
