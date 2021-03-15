package com.teamthree.studentevaluation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping("/login")
    public ResponseEntity login(@RequestBody Login login) {
        LoginValidation validation = new LoginValidation();
        boolean validationObject = validation.validate(login);

        //ResponseEntity.notFound(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}