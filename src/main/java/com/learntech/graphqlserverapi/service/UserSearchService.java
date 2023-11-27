package com.learntech.graphqlserverapi.service;

import com.learntech.graphqlserverapi.model.SearchInput;
import com.learntech.graphqlserverapi.model.User;

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
}
