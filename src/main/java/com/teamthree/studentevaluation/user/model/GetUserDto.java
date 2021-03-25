package com.teamthree.studentevaluation.user.model;

public class GetUserDto {

    private final Long id;

    private final String username;

    private final String stream;

    public GetUserDto(Long id, String username, String stream) {
        this.id = id;
        this.username = username;
        this.stream = stream;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getStream() {
        return stream;
    }
}
