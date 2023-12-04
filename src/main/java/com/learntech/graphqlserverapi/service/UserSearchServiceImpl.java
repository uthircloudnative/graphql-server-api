package com.learntech.graphqlserverapi.service;

import com.learntech.graphqlserverapi.entity.AddressEntity;
import com.learntech.graphqlserverapi.entity.PhoneEntity;
import com.learntech.graphqlserverapi.entity.UserEntity;
import com.learntech.graphqlserverapi.model.*;
import com.learntech.graphqlserverapi.model.request.AddressInput;
import com.learntech.graphqlserverapi.model.request.PhoneInput;
import com.learntech.graphqlserverapi.model.request.UserInput;
import com.learntech.graphqlserverapi.repository.UserRepository;
import com.learntech.graphqlserverapi.util.ApplicationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * UserSearchServiceImpl
 *
 * @author Uthiraraj Saminathan
 */
@Service
@Slf4j
public class UserSearchServiceImpl implements UserSearchService {

    private final UserRepository userRepository;

    private static final String YYYY_MM_DD_FORMAT = "yyyy-MM-dd";

    public UserSearchServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @param userInput
     * @return
     */
    @Override
    public User addUser(UserInput userInput) {
        UserEntity userEntity = userRepository.save(buildUserEntity(userInput));

        User user = buildUser(userEntity);
        user.setAddress(buildAddress(userEntity.getAddresses()));

        user.setPhone(buildPhone(userEntity.getPhones()));
        return user;
    }

    /**
     * @param userInput
     * @return
     */
    @Transactional
    @Override
    public User updateUser(UserInput userInput) {
        UserEntity dbUserEntity = userRepository.findUserEntityById(UUID.fromString(userInput.getId()));
        BeanUtils.copyProperties(userInput,dbUserEntity);
        dbUserEntity.setDateOfBirth(ApplicationUtil.convertStringToDate(userInput.getDateOfBirth(),
                                                                        DateTimeFormatter.ofPattern(YYYY_MM_DD_FORMAT)));

        //Update Address
        if(!CollectionUtils.isEmpty(userInput.getAddress())){
            if(!CollectionUtils.isEmpty(dbUserEntity.getAddresses())){
                userInput.getAddress()
                        .forEach(addressInput -> {
                            if(null != addressInput.getId()){
                                dbUserEntity.getAddresses().add(updateAddress(addressInput, dbUserEntity));
                            }else{
                                AddressEntity updateAddress = buildAddressEntity(addressInput,dbUserEntity);
                                dbUserEntity.getAddresses().add(updateAddress);
                            }
                        });
            }else{
                List<AddressEntity> addressEntities = userInput.getAddress()
                        .stream()
                        .map(addressInput -> buildAddressEntity(addressInput,dbUserEntity))
                        .collect(Collectors.toList());
                dbUserEntity.setAddresses(addressEntities);
            }
        }

        //Update Phone
        if(!CollectionUtils.isEmpty(userInput.getPhone())){
            if(!CollectionUtils.isEmpty(dbUserEntity.getPhones())){
                userInput.getPhone().forEach(phoneInput -> {
                    if(null != phoneInput.getId()){
                        dbUserEntity.getPhones().add(updatePhone(phoneInput,dbUserEntity));
                    }else{
                        PhoneEntity phoneEntity = buildPhoneEntity(phoneInput,dbUserEntity);
                        dbUserEntity.getPhones().add(phoneEntity);
                    }
                });
            }else{
               List<PhoneEntity> phoneEntities = userInput.getPhone()
                                                            .stream()
                                                            .map(phoneInput -> buildPhoneEntity(phoneInput, dbUserEntity))
                                                            .collect(Collectors.toList());
                dbUserEntity.setPhones(phoneEntities);
            }
        }

        UserEntity updatedUserEntity = userRepository.save(dbUserEntity);

        User user = buildUser(updatedUserEntity);
        user.setAddress(buildAddress(updatedUserEntity.getAddresses()));

        user.setPhone(buildPhone(updatedUserEntity.getPhones()));
        return user;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public DeleteUser deleteUser(UUID id) {
        DeleteUser deleteUser = new DeleteUser();
        try{
            UserEntity dbUserEntity = userRepository.findUserEntityById(id);
            userRepository.delete(dbUserEntity);

            deleteUser.setSuccess(true);
            deleteUser.setMessage("User deleted successfully");
            deleteUser.setId(id);

        }catch(Exception exp){
            deleteUser.setSuccess(false);
            deleteUser.setMessage("User not deleted successfully");
            deleteUser.setId(id);
        }
        return deleteUser;
    }


    private AddressEntity updateAddress(AddressInput addressInput,UserEntity dbUserEntity){
       Optional<AddressEntity> addressOptional = dbUserEntity.getAddresses()
                           .stream()
                           .filter(addressEntity -> addressEntity.getId().equals(UUID.fromString(addressInput.getId())))
                           .map(addressEntity -> {
                               BeanUtils.copyProperties(addressInput, addressEntity);
                               //addressEntity.setUser(dbUserEntity);
                               return addressEntity;
                              }).findFirst();
        return addressOptional.orElse(null);
    }

    private PhoneEntity updatePhone(PhoneInput phoneInput,UserEntity dbUserEntity){
       return dbUserEntity.getPhones()
                    .stream()
                    .filter(phoneEntity -> phoneEntity.getId().equals(UUID.fromString(phoneInput.getId())))
                    .map(phoneEntity -> {
                        BeanUtils.copyProperties(phoneInput, phoneEntity);
                        //phoneEntity.setUser(dbUserEntity);
                        return phoneEntity;
                    }).findFirst().orElse(null);
    }

    @Transactional
    @Override
    public User searchById(UUID id) {
        UserEntity userEntity = userRepository.findUserEntityById(id);
        User user = new User();
        BeanUtils.copyProperties(userEntity,user);
        user.setDateOfBirth(userEntity.getDateOfBirth().toString());

        user.setAddress(buildAddress(userEntity.getAddresses()));
        user.setPhone(buildPhone(userEntity.getPhones()));
        return user;
    }

    /**
     * @return
     */
    //@Transactional
    @Override
    public List<User> searchUser(SearchInput searchInput) {
        log.info("searchUser Starts");
        List<UserEntity> userAddressEntities = userRepository.findUserAddressByFirstNameAndLastName(searchInput.getFirstName(), searchInput.getLastName());
        List<UserEntity> userPhoneEntities = userRepository.findUserPhoneByFirstNameAndLastName(searchInput.getFirstName(), searchInput.getLastName());

        List<User> users = new ArrayList<>();

        if(!CollectionUtils.isEmpty(userAddressEntities)){

            users =  userAddressEntities.stream()
                            .map(userEntity -> {
                                User user = buildUser(userEntity);
                                user.setAddress(buildAddress(userEntity.getAddresses()));
                                UserEntity userPhoneEntity = getUserById(userPhoneEntities, userEntity.getId());
                                user.setPhone(buildPhone(userPhoneEntity.getPhones()));
                                return user;
                            }).collect(Collectors.toList());
        }
        return users;
    }



    private UserEntity buildUserEntity(UserInput userInput){
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userInput,userEntity);
        userEntity.setDateOfBirth(ApplicationUtil.convertStringToDate(userInput.getDateOfBirth(),
                                                                       DateTimeFormatter.ofPattern(YYYY_MM_DD_FORMAT)));
        if(!CollectionUtils.isEmpty(userInput.getAddress())){
        List<AddressEntity> addressEntities = new ArrayList<>();
        addressEntities = userInput.getAddress()
                                   .stream()
                                   .map(addressInput -> buildAddressEntity(addressInput, userEntity))
                                   .collect(Collectors.toList());
            userEntity.setAddresses(addressEntities);
        }

        if(!CollectionUtils.isEmpty(userInput.getPhone())){
            List<PhoneEntity> phoneEntities = new ArrayList<>();
            phoneEntities =  userInput.getPhone()
                                      .stream()
                                      .map(phoneInput -> buildPhoneEntity(phoneInput,userEntity))
                                      .collect(Collectors.toList());
            userEntity.setPhones(phoneEntities);
        }
        return userEntity;
    }

    private AddressEntity buildAddressEntity(AddressInput addressInput, UserEntity userEntity){
        AddressEntity addressEntity = new AddressEntity();
        BeanUtils.copyProperties(addressInput,addressEntity);
        addressEntity.setUser(userEntity);
        return addressEntity;
    }

    private PhoneEntity buildPhoneEntity(PhoneInput phoneInput, UserEntity userEntity){
        PhoneEntity phoneEntity = new PhoneEntity();
        BeanUtils.copyProperties(phoneInput,phoneEntity);
        phoneEntity.setUser(userEntity);
        return phoneEntity;
    }

    private UserEntity getUserById(List<UserEntity> userEntities, UUID userId){
       return userEntities.stream()
                    .filter(userEntity -> userEntity.getId().equals(userId))
                .findFirst().get();
    }
    private User buildUser(UserEntity userEntity){
        User user = new User();
        BeanUtils.copyProperties(userEntity, user);
        user.setDateOfBirth(userEntity.getDateOfBirth().toString());
        return user;
    }
    private List<Address> buildAddress(List<AddressEntity> addressEntities){
        List<Address> addresses = new ArrayList<>();
        addressEntities.forEach(addressEntity -> {
            Address address = new Address();
            BeanUtils.copyProperties(addressEntity, address);
            addresses.add(address);
        });
        return addresses;
    }

    private List<Phone> buildPhone(List<PhoneEntity> phoneEntities){
        List<Phone> phones = new ArrayList<>();
        phoneEntities.forEach(phoneEntity -> {
            Phone phone = new Phone();
            BeanUtils.copyProperties(phoneEntity, phone);
            phones.add(phone);
        });
        return phones;
    }
}
