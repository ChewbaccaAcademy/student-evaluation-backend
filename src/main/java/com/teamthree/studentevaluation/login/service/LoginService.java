package com.teamthree.studentevaluation.login.service;

import com.teamthree.studentevaluation.login.models.AuthenticationRequest;

public interface LoginService {
    String authenticate(AuthenticationRequest
                                authenticationRequest) throws Exception;
}
