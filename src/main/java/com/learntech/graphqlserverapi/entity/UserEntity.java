package com.learntech.graphqlserverapi.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * UserEntity
 *
 * @author Uthiraraj Saminathan
 */
@Entity
@Table(name = "tb_user")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(generator = "UUID") //Let the underlying DB take care of UUID creation.
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "created_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime created_date;

    @Column(name = "modified_date", nullable = false)
    @UpdateTimestamp
    private LocalDateTime modified_date;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AddressEntity> addresses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneEntity> phones;
}
