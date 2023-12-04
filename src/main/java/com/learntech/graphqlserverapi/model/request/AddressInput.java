package com.learntech.graphqlserverapi.model.request;

import lombok.Data;

/**
 * AddressInput
 *
 * @author Uthiraraj Saminathan
 */
@Data
public class AddressInput {

    private String id;
    private String type;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private Integer zip;
}
