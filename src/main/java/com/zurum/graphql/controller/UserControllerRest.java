package com.zurum.graphql.controller;

import com.zurum.graphql.dto.request.UpdateUserDetailsRequestDto;
import com.zurum.graphql.dto.request.UserRegisterRequestDto;
import com.zurum.graphql.dto.response.ApiResponse;
import com.zurum.graphql.dto.response.GetUserResponseDto;
import com.zurum.graphql.dto.response.PaginatedResponse;
import com.zurum.graphql.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("user")
public class UserControllerRest {

    private final UserService userService;

    @PostMapping(path="/register")
    public ResponseEntity<ApiResponse<String>> registerUser(@RequestBody @Valid final UserRegisterRequestDto registrationRequestDto) {
        log.info("controller register: register user :: [{}] ::", registrationRequestDto.getEmail());
        String response = userService.registerUser(registrationRequestDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/user/register").toUriString());
        return ResponseEntity.created(uri).body(ApiResponse.<String>builder()
                .statusMessage("success")
                .isSuccessful(true)
                .data(response)
                .build()
        );
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<ApiResponse<Boolean>> updateUser(@RequestBody final UpdateUserDetailsRequestDto updateUserRequestDto,
                                                           @PathVariable("id") final String id) {
        log.info("controller update - updated user with id :: [{}]",id);

        userService.updateUserDetails(updateUserRequestDto, id);
        return ResponseEntity.ok(ApiResponse.<Boolean>builder()
                .isSuccessful(true)
                .statusMessage("user updated successfully")
                .data(true)
                .build()
        );
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteUser(@PathVariable("id") final String id) {
        log.info("controller delete - delete user with id :: [{}]",id);

        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.<Boolean>builder()
                .isSuccessful(true)
                .statusMessage("user deleted successfully")
                .data(true)
                .build()
        );
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ApiResponse<GetUserResponseDto>> getUserById(@PathVariable("id") final String id) {
        log.info("controller get - get user with id :: [{}]",id);

        GetUserResponseDto userResponseDto = userService.getUserById(id);
        return ResponseEntity.ok(ApiResponse.<GetUserResponseDto>builder()
                .isSuccessful(true)
                .statusMessage("successfully")
                .data(userResponseDto)
                .build()
        );
    }

    @GetMapping(path="users", produces = "application/json")
    public ResponseEntity<ApiResponse<PaginatedResponse<GetUserResponseDto>>> getExpanses(
            @RequestParam(defaultValue = "0", required = false) final int start,
            @RequestParam(defaultValue = "10", required = false) final int limit
    ) {
        log.info("initiate request fetch from :: {} :: to :: {} ::", start, limit);
        PaginatedResponse<GetUserResponseDto> response = userService.getAllUsers(start, limit);
        return ResponseEntity.ok().body(ApiResponse.<PaginatedResponse<GetUserResponseDto>>builder()
                .data(response)
                .isSuccessful(true)
                .statusMessage("success")
                .build());
    }
}
