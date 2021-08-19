package com.algaworks.algafood.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { })
@Pattern(regexp = "[+-]([0][0-9]|[1][0-2]):00", 
	message = "Padrão de offset inválido.")
public @interface Offset {

	String message() default "offset inválido";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
