package com.learntech.graphqlserverapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * UserResponse
 *
 * @author Uthiraraj Saminathan
 */
@Data
@AllArgsConstructor
public class UserResponse {
    private List<User> users;
}
