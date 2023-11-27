package com.learntech.graphqlserverapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * PhoneEntity
 *
 * @author Uthiraraj Saminathan
 */
@Entity
@Table(name = "phone")
@Getter
@Setter
public class PhoneEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "country_code", nullable = false)
    private String countryCode;

    @Column(name = "created_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime created_date;

    @Column(name = "modified_date", nullable = false)
    @UpdateTimestamp
    private LocalDateTime modified_date;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;
}
