package br.com.fiap.healthix.validation;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(FIELD)
@Constraint(validatedBy = EmailValidator.class)
@Retention(RUNTIME)


public @interface Email {
    
    String  message() default "Email inválido"; 

    Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
