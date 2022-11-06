package com.project.sales.service.impl;

import com.project.sales.dto.SaleDetailDto;
import com.project.sales.entity.Product;
import com.project.sales.entity.Sale;
import com.project.sales.entity.SaleDetail;
import com.project.sales.repository.ProductRepository;
import com.project.sales.repository.SaleDetailRepository;
import com.project.sales.service.ProductService;
import com.project.sales.service.SaleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleDetailServiceImpl implements SaleDetailService {

    @Autowired
    private SaleDetailRepository saleDetailRepository;

    @Autowired
    private ProductRepository productoRepository;

    @Autowired
    private ProductService productService;

    @Override
    public void registerSaleDetail(List<SaleDetailDto> saleDetailDtoList, Sale sale) {

        saleDetailDtoList.forEach( saleDetailUnit -> {

            Product producto = productService.findById(saleDetailUnit.getProducId());

            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setSale(sale);
            saleDetail.setCant(saleDetailUnit.getCant());
            saleDetail.setPrice(saleDetailUnit.getPrice());
            saleDetail.setProduct(producto);
            saleDetailRepository.save(saleDetail);
        });
    }
}
