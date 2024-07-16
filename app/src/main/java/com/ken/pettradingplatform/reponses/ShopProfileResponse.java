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
    private DataResponse data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class DataResponse {
        private String name;
        private String address;
    }
}
