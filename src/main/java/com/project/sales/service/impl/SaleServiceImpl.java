package com.project.sales.service.impl;

import com.project.sales.entity.Customer;
import com.project.sales.entity.Sale;
import com.project.sales.entity.SaleDetail;
import com.project.sales.repository.SaleRepository;
import com.project.sales.service.SaleDetailService;
import com.project.sales.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired private SaleRepository saleRepository;
    @Autowired private SaleDetailService saleDetailService;

    @Override
    public Sale registerSale(List<SaleDetail> saleDetailList, Customer customer) {
        Sale sale = Sale.builder()
                .date(new Date())
                .customer(customer)
                .total(this.calculateTotal(saleDetailList))
                .build();
        Sale saleDB = saleRepository.save(sale);
        saleDetailList.stream().map(saleDetail -> saleDetailService.registerSaleDetail(saleDetail,saleDB));
        return saleDB;
    }

    @Override
    public List<Sale> listSales() {
        return null;
    }

    private BigDecimal calculateTotal(List<SaleDetail> saleDetailList){
        return saleDetailList.stream().map(saleDetail -> {
            return new BigDecimal((saleDetail.getPrice() * saleDetail.getCant()));
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
