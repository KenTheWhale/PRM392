package com.ken.pettradingplatform.reponses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerPostListResponse {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Post{
        private String typeName;
        private float price;
    }

    private String status;
    private String message;
    private List<Post> posts;
}
