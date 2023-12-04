package com.learntech.graphqlserverapi.model;

import lombok.Data;

import java.util.UUID;

/**
 * Phone
 *
 * @author Uthiraraj Saminathan
 */
@Data
public class Phone {

    private UUID id;
    private String type;
    private String number;
    private String countryCode;
}
