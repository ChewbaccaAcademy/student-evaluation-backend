package com.teamthree.studentevaluation.user.model;

public class GetUserDto {

    private Long id;

    private String username;

    private String stream;

    public GetUserDto(Long id, String username, String stream) {
        this.id = id;
        this.username = username;
        this.stream = stream;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() { return username; }

    public String getStream() {
        return stream;
    }
}
