package com.learntech.graphqlserverapi.model;

import lombok.Data;

import java.util.Date;

/**
 * SearchInput
 *
 * @author Uthiraraj Saminathan
 */
@Data
public class SearchInput {

    private String firstName;
    private String lastName;
    private Date dateOfBirth;
}
