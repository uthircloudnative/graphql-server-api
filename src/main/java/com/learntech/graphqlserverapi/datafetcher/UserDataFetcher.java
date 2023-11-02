package com.learntech.graphqlserverapi.datafetcher;

import com.learntech.graphqlserverapi.model.SearchInput;
import com.learntech.graphqlserverapi.model.User;
import com.learntech.graphqlserverapi.model.UserResponse;
import com.learntech.graphqlserverapi.service.UserSearchService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * UserDataFetcher
 *
 * @author Uthiraraj Saminathan
 */
@DgsComponent
public class UserDataFetcher {

    private static Logger logger = LoggerFactory.getLogger(UserDataFetcher.class);
    private final UserSearchService userSearchService;

    public UserDataFetcher(UserSearchService userSearchService) {
        this.userSearchService = userSearchService;
    }

    @DgsQuery
    public Mono<UserResponse> searchUser(@InputArgument SearchInput searchInput){
        logger.info("searchUser() Starts");
        List<User> users = userSearchService.searchUser(searchInput);
        logger.info("searchUser() Ends");
        return Mono.just(new UserResponse(users));
    }

    @DgsQuery
    public Mono<User> searchByUserId(@InputArgument Integer id){
        User user = userSearchService.searchById(id);
        return Mono.just(user);
    }

}
