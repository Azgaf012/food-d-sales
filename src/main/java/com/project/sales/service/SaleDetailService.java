package com.project.sales.service;

import com.project.sales.dto.SaleDetailDto;
import com.project.sales.entity.Sale;
import com.project.sales.entity.SaleDetail;

import java.util.List;

public interface SaleDetailService {

    void registerSaleDetail(List<SaleDetailDto> saleDetailDtoList, Sale sale);
}
