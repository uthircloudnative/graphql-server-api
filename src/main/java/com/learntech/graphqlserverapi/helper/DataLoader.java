package com.learntech.graphqlserverapi.helper;

import com.learntech.graphqlserverapi.entity.AddressEntity;
import com.learntech.graphqlserverapi.entity.PhoneEntity;
import com.learntech.graphqlserverapi.entity.UserEntity;
import com.learntech.graphqlserverapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * DataLoader
 *
 * @author Uthiraraj Saminathan
 */
@Component
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @param args incoming main method arguments
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {

        UserEntity userEntity = new UserEntity();

        //userEntity.setId(UUID.randomUUID());
        userEntity.setFirstName("Jhon");
        userEntity.setLastName("Victor");
        userEntity.setDateOfBirth(LocalDate.now());
        userEntity.setGender("M");
        userEntity.setCreated_date(LocalDateTime.now());
        userEntity.setModified_date(LocalDateTime.now());

        AddressEntity homeAddress = new AddressEntity();

        //homeAddress.setId(UUID.randomUUID());
        homeAddress.setType("Home");
        homeAddress.setStreet1("1234 First Street");
        homeAddress.setStreet2("Apt 10");
        homeAddress.setCity("Louesville");
        homeAddress.setState("TX");
        homeAddress.setZip(12345);

        homeAddress.setUser(userEntity);
        AddressEntity officeAddress = new AddressEntity();

        officeAddress.setType("Office");
        officeAddress.setStreet1("1234 First Street");
        officeAddress.setStreet2("Suite 3");
        officeAddress.setCity("Louesville");
        officeAddress.setState("TX");
        officeAddress.setZip(12345);
        //To associate Parent UserEntity to Child to create Foreign Key relationship
        officeAddress.setUser(userEntity);
        userEntity.setAddresses(Arrays.asList(homeAddress,officeAddress));

        PhoneEntity homePhone = new PhoneEntity();

        //homePhone.setId(UUID.randomUUID());
        homePhone.setType("Home");
        homePhone.setNumber("1234567890");
        homePhone.setCountryCode("+1");
        homePhone.setCreated_date(LocalDateTime.now());
        homePhone.setModified_date(LocalDateTime.now());

        homePhone.setUser(userEntity);
        PhoneEntity officePhone = new PhoneEntity();

        officePhone.setType("Office");
        officePhone.setNumber("9876543210");
        officePhone.setCountryCode("+1");
        officePhone.setCreated_date(LocalDateTime.now());
        officePhone.setModified_date(LocalDateTime.now());

        officePhone.setUser(userEntity);
        userEntity.setPhones(Arrays.asList(homePhone,officePhone));

        userRepository.deleteAll();

        userRepository.save(userEntity);

        List<UserEntity> users =userRepository.findUserAddressByFirstNameAndLastName("Jhon","Victor");

        log.info("Fetched user entities {}", users.get(0).getId());

        //UserEntity userEntity1 = userRepository.findUserEntityById(users.get(0).getId());

        //log.info("Actual User Entity {}", userEntity1.getId());

        //List<UserEntity> users = userRepository.findUserEntityByFirstNameAndLastName("Jhon","Victor");

       // log.info("Fetched user entities {}", users.get(0).getId());
    }
}
