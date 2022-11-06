package com.project.sales.service.impl;

import com.project.sales.dto.SaleDetailDto;
import com.project.sales.entity.Customer;
import com.project.sales.entity.Sale;
import com.project.sales.entity.SaleDetail;
import com.project.sales.repository.SaleRepository;
import com.project.sales.service.SaleDetailService;
import com.project.sales.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired private SaleRepository saleRepository;
    @Autowired private SaleDetailService saleDetailService;

    @Override
    @Transactional
    public Sale registerSale(List<SaleDetailDto> saleDetailList, Customer customer) {
        Sale sale = Sale.builder()
                .date(new Date())
                .customer(customer)
                .total(this.calculateTotal(saleDetailList))
                .build();
        Sale saleDB = saleRepository.save(sale);
        saleDetailService.registerSaleDetail(saleDetailList,saleDB);
        return saleDB;
    }

    @Override
    public List<Sale> listSales() {
        return null;
    }

    private BigDecimal calculateTotal(List<SaleDetailDto> saleDetailList){
        return saleDetailList.stream().map(saleDetail -> new BigDecimal(String.valueOf((saleDetail.getPrice().multiply(new BigDecimal(saleDetail.getCant()))))))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        /*hola soy diego*/
    }
}
