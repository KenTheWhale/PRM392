package com.ken.pettradingplatform.reponses;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopPostListResponse {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Post{
        private int id;
        private String typeName;
        private float price;
    }

    private String status;
    private String message;
    private ArrayList<ShopPostListResponse.Post> data;
}
