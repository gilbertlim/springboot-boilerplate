package com.boilerplate.common.config.scheduling.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SchedulingGrouping {

    String[] value() default {};
}
