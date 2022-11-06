package com.project.sales.service;

import com.project.sales.dto.SaleDetailDto;
import com.project.sales.entity.Customer;
import com.project.sales.entity.Sale;
import com.project.sales.entity.SaleDetail;

import java.util.List;

public interface SaleService {

    Sale registerSale(List<SaleDetailDto> detailList, Customer customer);

    List<Sale> listSales();

}
