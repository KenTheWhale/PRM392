package com.ken.pettradingplatform.reponses;


import java.io.Serializable;

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
    private Account data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Account{
        private int accountID;
        private String role;
    }
}
