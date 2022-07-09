package com.zurum.graphql.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
public class UserRegisterRequestDto implements Serializable {

    @NotBlank(message = "first name should not be blank")
    private String firstName;

    @NotBlank(message = "last name should not be blank")
    private String lastName;

    @Email
    @NotBlank(message = "email should not be blank")
    private String email;

    @Min(value = 18, message = "must be 18 years and above")
    private int age;

    @Past(message = "Date of birth can't be current or future date")
    @JsonAlias(value = "date_of_birth")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dob;

    @NotBlank(message = "Phone number cannot be blank")
    private String phoneNumber;
}
