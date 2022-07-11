package com.zurum.graphql.service.implementations;

import com.zurum.graphql.dto.constants.Responses;
import com.zurum.graphql.dto.request.UpdateUserDetailsRequestDto;
import com.zurum.graphql.dto.request.UserRegisterRequestDto;
import com.zurum.graphql.dto.response.GetUserResponseDto;
import com.zurum.graphql.dto.response.PaginatedResponse;
import com.zurum.graphql.entity.User;
import com.zurum.graphql.exceptions.ResourceConflictException;
import com.zurum.graphql.exceptions.ResourceNotFoundException;
import com.zurum.graphql.repository.UserRepository;
import com.zurum.graphql.service.UserService;
import com.zurum.graphql.utils.ModelMapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public String registerUser(UserRegisterRequestDto userRegisterRequestDto) {

        if (doesUserAlreadyExist(userRegisterRequestDto.getEmail())) {
            throw new ResourceConflictException("User already exist");
        }

        User newUser = new User();
        ModelMapperUtils.map(userRegisterRequestDto,newUser);
        userRepository.save(newUser);
        return Responses.SUCCESS.getStatus();
    }

    @Override
    public GetUserResponseDto getUserById(String userId) {
        log.info("finding user with #id :: {}", userId);
        return ModelMapperUtils.map(getUserByUserId(userId), GetUserResponseDto.class);
    }

    @Override
    public PaginatedResponse<GetUserResponseDto> getAllUsers(int start, int limit) {
        Page<User> users = userRepository.findAll(PageRequest.of(start, limit));
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No user found");
        }
        return PaginatedResponse.<GetUserResponseDto>builder()
                .content(ModelMapperUtils.mapAll(users.getContent(), GetUserResponseDto.class))
                .totalElements(users.getTotalElements())
                .build();
    }

    @Override
    public List<GetUserResponseDto> getAllUserz() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No user found");
        }
        return ModelMapperUtils.mapAll(users, GetUserResponseDto.class);
    }

    @Override
    public String updateUserDetails(UpdateUserDetailsRequestDto updateUserDetailsDto, String userId) {
       User user = getUserByUserId(userId);
       ModelMapperUtils.map(updateUserDetailsDto, getUserByUserId(userId));
       userRepository.save(user);
       return Responses.SUCCESS.getStatus();
    }

    @Override
    public void deleteUserById(String userId) {

        userRepository.findById(userId).orElseThrow(
                () -> {
                    throw new ResourceNotFoundException("Not found");
                }
        );
        userRepository.deleteById(userId);
    }


    private boolean doesUserAlreadyExist(String email) {
        return userRepository.getUserByEmail(email).isPresent();
    }

    private User getUserByUserId(String userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> {
                    throw new ResourceNotFoundException("user not found");
                }
        );
    }
}
