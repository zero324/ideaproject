package com.adt.edu.mvcframework.annotations;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AdtRequestMapping {
    String value() default "";
}
