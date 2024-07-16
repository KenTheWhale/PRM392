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
    private String status;
    private String message;
    private ArrayList<Notification> data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Notification {
        private int id;
        private String shopName;
        private String title;
        private String address;
        private float money;
        private String sellerName;
        private String sellerPhone;
        private Date meetDate;
    }
}
