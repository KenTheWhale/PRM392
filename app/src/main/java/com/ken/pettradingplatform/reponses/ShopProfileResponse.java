package com.ken.pettradingplatform.reponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopProfileResponse {
    private String status;
    private String message;
    private String shopName;
    private String address;
}
