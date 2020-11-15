package com.adt.edu.mvcframework.annotations;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AdtAutoWired {
    String value() default "";
}
