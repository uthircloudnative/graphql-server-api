package com.learntech.graphqlserverapi.service;

import com.learntech.graphqlserverapi.model.Address;
import com.learntech.graphqlserverapi.model.Phone;
import com.learntech.graphqlserverapi.model.SearchInput;
import com.learntech.graphqlserverapi.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * UserSearchServiceImpl
 *
 * @author Uthiraraj Saminathan
 */
@Service
@Slf4j
public class UserSearchServiceImpl implements UserSearchService {

    /**
     * @return
     */
    @Override
    public List<User> searchUser(SearchInput searchInput) {
        log.info("searchUser Starts");
        return Arrays.asList(getUser());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public User searchById(Integer id) {
        return getUser();
    }

    private User getUser() {
        User user = new User();
        user.setId(1);
        user.setFirstName("Jhon");
        user.setLastName("Victor");
        user.setGender("M");
        user.setDateOfBirth(LocalDate.now().toString());

        Address address = new Address();
        address.setType("Home");
        address.setStreet1("1234 First Street");
        address.setStreet2("Apt 9");
        address.setCity("Louisville");
        address.setState("NY");
        address.setType("Home");
        address.setZip(12345);

        user.setAddress(Arrays.asList(address));

        Phone phone = new Phone();

        phone.setType("Mobile");
        phone.setCountryCode("+1");
        phone.setNumber("1234567890");

        user.setPhone(Arrays.asList(phone));

        return user;
    }
}
