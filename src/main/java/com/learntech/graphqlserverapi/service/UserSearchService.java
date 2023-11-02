package com.learntech.graphqlserverapi.service;

import com.learntech.graphqlserverapi.model.SearchInput;
import com.learntech.graphqlserverapi.model.User;

import java.util.List;

/**
 * UserSearchService
 *
 * @author Uthiraraj Saminathan
 */
public interface UserSearchService {

    List<User> searchUser(SearchInput searchInput);

    User searchById(Integer id);
}
