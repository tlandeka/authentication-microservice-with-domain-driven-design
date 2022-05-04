package com.tomo.mcauthentication.infrastructure.springboot.controller;

import com.tomo.mcauthentication.application.users.command.ChangeUserDetailsCommand;
import com.tomo.mcauthentication.application.users.dto.BaseUserDto;
import com.tomo.mcauthentication.application.users.query.GetUserQuery;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class UserController extends AbstractController {

    @RequestMapping(method = RequestMethod.GET, path = "/user/{userId}")
    public ResponseEntity user(@PathVariable(value = "userId") String userId){
        BaseUserDto dto = this.executeQuery(new GetUserQuery(userId), BaseUserDto.class);

        return ResponseEntity.ok(dto);
    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/user")
    public ResponseEntity user(@RequestBody @Validated ChangeUserDetailsCommand command){
        this.executeCommand(command);
        return new ResponseEntity(HttpStatus.OK);
    }
}
