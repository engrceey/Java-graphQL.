package com.zurum.graphql.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserResponseDto {
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private LocalDate dob;
    private String phoneNumber;
}
