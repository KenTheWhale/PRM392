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
public class CustomerPostResponse {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Post{
        private int id;
        private String typeName;
        private float price;
        private String description;
    }
    private String status;
    private String message;
    private Post data;
}
