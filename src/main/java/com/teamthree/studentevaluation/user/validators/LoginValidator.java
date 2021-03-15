package com.teamthree.studentevaluation;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LoginValidation {
    public boolean validate(Login login){
        if(login.email == null || login.email.trim().length() == 0 || !login.email.contains("@")){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not valid");
        }

        DataAccessRepository dataAccess = new DataAccessRepository();
        String emailFromDataBase = dataAccess.getEmail(login);
        String passwordFromDataBase = dataAccess.getPassword(login);

        return emailFromDataBase == login.email && passwordFromDataBase == login.password;
    }
}