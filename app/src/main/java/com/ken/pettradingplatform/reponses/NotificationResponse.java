package com.ken.pettradingplatform.reponses;

import java.util.ArrayList;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationResponse {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Notification{
        private String shopName;
        private String postName;
        private String address;
        private float money;
        private String sellerName;
        private String sellerPhone;
        private Date meetDate;
    }
    private String status;
    private String message;
    private ArrayList<NotificationResponse.Notification> data;
}
