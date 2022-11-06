package com.project.sales.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDetailDto {
    private BigDecimal price;
    private Integer cant;
    private Long producId;
    private String name;
    private String category;
}
