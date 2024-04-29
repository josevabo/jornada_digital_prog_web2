package com.example.banco.cliente.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IdadeMinimaValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IdadeMinima {
    int idade() default 18;
    String message() default "Pessoa deve ser maior de idade";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
