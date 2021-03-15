package com.teamthree.studentevaluation.service;

import com.teamthree.studentevaluation.models.AuthenticationRequest;

public interface LoginService {
    String authenticate(AuthenticationRequest
                             authenticationRequest) throws Exception;
}
