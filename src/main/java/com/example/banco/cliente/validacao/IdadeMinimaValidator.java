package com.example.banco.cliente.validacao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class IdadeMinimaValidator implements ConstraintValidator<IdadeMinima, LocalDate> {
    private int idade;

    @Override
    public void initialize(IdadeMinima constraintAnnotation) {
        this.idade = constraintAnnotation.idade();
    }

    @Override
    public boolean isValid(LocalDate dataNascimento, ConstraintValidatorContext constraintValidatorContext) {

        var dataAtual = LocalDate.now();
        if(Period.between(dataNascimento, dataAtual).getYears() < this.idade)
            return false;

        return true;
    }
}
