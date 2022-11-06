package com.project.sales.controller;

import com.project.sales.dto.SaleDto;
import com.project.sales.entity.Sale;
import com.project.sales.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/sale")
public class SaleController {
    @Autowired SaleService saleService;

    @PostMapping("/register")
    public ResponseEntity<Sale> registerSale(@RequestBody SaleDto saleDto){
        Sale sale = saleService.registerSale(saleDto.getSaleDetailList(),saleDto.getCustomer());
        return ResponseEntity.ok(sale);
    }
}
