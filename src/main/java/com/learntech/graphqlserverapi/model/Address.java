package com.learntech.graphqlserverapi.model;

import lombok.Data;

import java.util.UUID;

/**
 * Address
 *
 * @author Uthiraraj Saminathan
 */
@Data
public class Address {

    private UUID id;
    private String type;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private Integer zip;
}
