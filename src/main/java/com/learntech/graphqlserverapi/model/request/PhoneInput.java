package com.learntech.graphqlserverapi.model.request;

import lombok.Data;

/**
 * PhoneInput
 *
 * @author Uthiraraj Saminathan
 */
@Data
public class PhoneInput {

    private String id;
    private String type;
    private String number;
    private String countryCode;
}
