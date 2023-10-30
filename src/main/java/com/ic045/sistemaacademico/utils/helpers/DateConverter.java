package com.ic045.sistemaacademico.utils.helpers;

import java.time.LocalDateTime;

public class DateConverter {
    public static String getAnoPontoSemestre(LocalDateTime now) {
        int ano = now.getYear();
        int mes = now.getMonthValue();

        return mes > 6 ? ano + "." + "2" : ano + "." + 1;
    }
}