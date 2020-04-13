package com.karthik.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Author : Karthik Suresh <br>
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NoEmptyNullElementsValidator.class)
public @interface NoEmptyNullElements {

	String message() default "List cannot contain empty/null fields";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
