package com.zurum.graphql.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "users")
public class User extends BaseEntity{

    @Column(name = "first_name", length = 80)
    private String firstName;

    @Column(name = "last_name", length = 80)
    private String lastName;

    @Column( length = 120)
    private String email;

    @Column(length = 10)
    private int age;

    @Column(name = "date_of_birth", length = 20)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dob;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;
}
