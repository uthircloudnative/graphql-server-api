package com.learntech.graphqlserverapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**Ø
 * AddressEntity
 *
 * @author Uthiraraj Saminathan
 */
@Entity
@Table(name = "address")
@Getter
@Setter
public class AddressEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    private UUID id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "street1", nullable = false)
    private String street1;

    @Column(name = "street2", nullable = true)
    private String street2;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "zip", nullable = false)
    private Integer zip;

    @Column(name = "created_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime created_date;

    @Column(name = "modified_date", nullable = false)
    @UpdateTimestamp
    private LocalDateTime modified_date;

    @ManyToOne //Defines a Bi-Directional mapping/relationship between User & Address entities.
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;
}
