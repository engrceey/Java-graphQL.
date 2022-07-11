package com.zurum.graphql.controller;

import com.zurum.graphql.dto.response.GetUserResponseDto;
import com.zurum.graphql.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GraphQlUserController {
    private final UserService userService;

    @QueryMapping
    public List<GetUserResponseDto> getUsers() {
        return  userService.getAllUserz();
    }

    @QueryMapping
    public GetUserResponseDto getUserById(String id ) {
        return  userService.getUserById(id);
    }

}
