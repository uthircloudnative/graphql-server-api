package com.learntech.graphqlserverapi.model;

import lombok.Data;

import java.util.UUID;

/**
 * DeleteUser
 *
 * @author Uthiraraj Saminathan
 */
@Data
public class DeleteUser {
    private Boolean success;
    private String message;
    private UUID id;
}
