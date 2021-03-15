package com.teamthree.studentevaluation;

//when connection with DB will be established, these methods implementation will change. Now it's only to fake data
public class DataAccessRepository {
    public String getEmail(Login login) {
        return login.email;
    }

    public String getPassword(Login login) {
        return login.password;       
    }

}