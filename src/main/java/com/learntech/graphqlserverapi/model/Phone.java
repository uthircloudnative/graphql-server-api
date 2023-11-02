package com.learntech.graphqlserverapi.model;

import lombok.Data;

/**
 * Phone
 *
 * @author Uthiraraj Saminathan
 */
@Data
public class Phone {

    private String type;
    private String number;
    private String countryCode;
}
