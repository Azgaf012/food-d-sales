package com.project.sales.dto;

import com.project.sales.entity.Customer;
import com.project.sales.entity.SaleDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDto {
    private Customer customer;
    private List<SaleDetail> saleDetailList;
}
