package com.project.sales.service;

import com.project.sales.entity.Sale;
import com.project.sales.entity.SaleDetail;

public interface SaleDetailService {
    SaleDetail registerSaleDetail(SaleDetail saleDetail, Sale sale);
}
