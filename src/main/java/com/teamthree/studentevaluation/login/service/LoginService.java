package com.teamthree.studentevaluation.login.service;

import com.teamthree.studentevaluation.login.models.AuthenticationRequest;
import com.teamthree.studentevaluation.login.models.AuthenticationResponse;

public interface LoginService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) throws Exception;
}
