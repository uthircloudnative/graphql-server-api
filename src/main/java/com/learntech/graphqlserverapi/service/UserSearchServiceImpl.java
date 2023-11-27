package com.learntech.graphqlserverapi.service;

import com.learntech.graphqlserverapi.entity.AddressEntity;
import com.learntech.graphqlserverapi.entity.PhoneEntity;
import com.learntech.graphqlserverapi.entity.UserEntity;
import com.learntech.graphqlserverapi.model.Address;
import com.learntech.graphqlserverapi.model.Phone;
import com.learntech.graphqlserverapi.model.SearchInput;
import com.learntech.graphqlserverapi.model.User;
import com.learntech.graphqlserverapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
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

    public UserSearchServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public User searchById(UUID id) {
        UserEntity userEntity = userRepository.findUserEntityById(id);
        User user = new User();
        BeanUtils.copyProperties(userEntity,user);

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

    private UserEntity getUserById(List<UserEntity> userEntities, UUID userId){
       return userEntities.stream()
                    .filter(userEntity -> userEntity.getId().equals(userId))
                .findFirst().get();
    }
    private User buildUser(UserEntity userEntity){
        User user = new User();
        BeanUtils.copyProperties(userEntity, user);
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
