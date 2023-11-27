package com.learntech.graphqlserverapi.datafetcher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learntech.graphqlserverapi.model.SearchInput;
import com.learntech.graphqlserverapi.model.User;
import com.learntech.graphqlserverapi.service.UserSearchService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

/**
 * UserDataFetcher
 *
 * @author Uthiraraj Saminathan
 */
@DgsComponent
@Slf4j
public class UserDataFetcher {

    private final UserSearchService userSearchService;

    public UserDataFetcher(UserSearchService userSearchService) {
        this.userSearchService = userSearchService;
    }


    @DgsQuery
    public Mono<List<User>> users(@InputArgument SearchInput searchInput) throws JsonProcessingException {
        log.info("users() Starts");
        log.info("First Name {}", searchInput.getFirstName());
        log.info("Last Name {}", searchInput.getLastName());

        List<User> users = userSearchService.searchUser(searchInput);
        return Mono.just(users);
    }

    @DgsQuery
    public Mono<User> user(@InputArgument UUID id){
        log.info("user() Starts");
        User user = userSearchService.searchById(id);
        return Mono.just(user);
    }
}
