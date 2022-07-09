package com.zurum.graphql.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
@Builder
public class UpdateUserDetailsRequestDto {
    @NotBlank(message = "first name should not be blank")
    private String firstName;

    @NotBlank(message = "last name should not be blank")
    private String lastName;

    @NotBlank(message = "Phone number cannot be blank")
    private String phoneNumber;
}
