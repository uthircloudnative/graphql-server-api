package com.learntech.graphqlserverapi.repository;

import com.learntech.graphqlserverapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * UserRepository
 *
 * @author Uthiraraj Saminathan
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @Query("SELECT distinct u FROM UserEntity u JOIN FETCH u.addresses a  WHERE  u.firstName = :firstName AND u.lastName = :lastName")
    List<UserEntity> findUserAddressByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query("SELECT distinct u FROM UserEntity u JOIN FETCH u.phones p  WHERE  u.firstName = :firstName AND u.lastName = :lastName")
    List<UserEntity> findUserPhoneByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    UserEntity findUserEntityById(UUID id);

}
