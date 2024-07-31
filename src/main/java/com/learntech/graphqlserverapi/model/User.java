package com.learntech.graphqlserverapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * User
 *
 * @author Uthiraraj Saminathan
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class User {
    private UUID id;
    private String firstName;
    private String lastName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String dateOfBirth;
    private String gender;
    private List<Address> address;
    private List<Phone> phone;

//    public UUID getId() {
//        return id;
//    }
//
//    public void setId(UUID id) {
//        this.id = id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    public String getDateOfBirth() {
//        return dateOfBirth;
//    }
//
//    public void setDateOfBirth(String dateOfBirth) {
//        this.dateOfBirth = dateOfBirth;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public List<Address> getAddress() {
//        return address;
//    }
//
//    public void setAddress(List<Address> address) {
//        this.address = address;
//    }
//
//    public List<Phone> getPhone() {
//        return phone;
//    }
//
//    public void setPhone(List<Phone> phone) {
//        this.phone = phone;
//    }
}
