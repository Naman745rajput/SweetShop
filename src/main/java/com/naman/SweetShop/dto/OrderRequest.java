package com.naman.SweetShop.dto;

import lombok.Data;

@Data
public class OrderRequest {

    private Long sweetId;
    private Integer quantity;
}
