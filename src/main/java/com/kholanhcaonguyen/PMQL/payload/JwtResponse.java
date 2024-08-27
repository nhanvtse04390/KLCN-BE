package com.kholanhcaonguyen.PMQL.payload;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private Boolean isActive;

    public JwtResponse(String accessToken, Long id, String username, String email, Boolean isActive) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.isActive = isActive;
    }

    // Getters and Setters
}
