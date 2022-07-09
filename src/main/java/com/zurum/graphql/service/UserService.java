package com.zurum.graphql.service;

import com.zurum.graphql.dto.request.UpdateUserDetailsRequestDto;
import com.zurum.graphql.dto.request.UserRegisterRequestDto;
import com.zurum.graphql.dto.response.GetUserResponseDto;
import com.zurum.graphql.dto.response.PaginatedResponse;

public interface UserService {
    String registerUser(UserRegisterRequestDto userRegisterRequestDto);
    GetUserResponseDto getUserById(String userId);
    PaginatedResponse<GetUserResponseDto> getAllUsers(int start, int limit);
    String updateUserDetails(UpdateUserDetailsRequestDto updateUserDetailsDto, String userId);
    void deleteUserById(String userId);


}
