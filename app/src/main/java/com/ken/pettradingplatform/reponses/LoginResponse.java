package com.ken.pettradingplatform.reponses;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private String status;
    private String message;
    private Account account;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    private static class Account{
        String fullName;
        String email;
        String password;
        String phoneNumber;
        String role;
    }
}
