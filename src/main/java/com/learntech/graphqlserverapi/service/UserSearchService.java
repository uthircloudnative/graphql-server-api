package com.learntech.graphqlserverapi.service;

import com.learntech.graphqlserverapi.model.DeleteUser;
import com.learntech.graphqlserverapi.model.SearchInput;
import com.learntech.graphqlserverapi.model.User;
import com.learntech.graphqlserverapi.model.request.UserInput;

import java.util.List;
import java.util.UUID;

/**
 * UserSearchService
 *
 * @author Uthiraraj Saminathan
 */
public interface UserSearchService {

    List<User> searchUser(SearchInput searchInput);

    User searchById(UUID id);

    User addUser(UserInput userInput);

    User updateUser(UserInput userInput);

    DeleteUser deleteUser(UUID id);
}
