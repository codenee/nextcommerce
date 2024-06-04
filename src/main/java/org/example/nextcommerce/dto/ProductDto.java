package org.example.nextcommerce.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ProductDto {
    private Long productId;
    private String code;
    private String name;
    private String price;
    private Integer stock;
    public void updateId(Long productId){
        this.productId = productId;
    }
}