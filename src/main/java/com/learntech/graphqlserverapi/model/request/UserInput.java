package com.learntech.graphqlserverapi.model.request;

import lombok.Data;

import java.util.List;

/**
 * UserInput
 *
 * @author Uthiraraj Saminathan
 */
@Data
public class UserInput {

    private String id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private List<AddressInput> address;
    private List<PhoneInput> phone;
}
