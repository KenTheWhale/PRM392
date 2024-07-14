package com.ken.pettradingplatform.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangePasswordRequest {
    private String email;
    private String oldPass;
    private String newPass;
    private String confirmNewPass;
}
