package com.learntech.graphqlserverapi.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * ApplicationUtil
 *
 * @author Uthiraraj Saminathan
 */
public class ApplicationUtil {

    public static LocalDate convertStringToDate(String dateString, DateTimeFormatter formatter){
        return LocalDate.parse(dateString, formatter);
    }

    public static String convertDateToString(LocalDate date, String pattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }
}
