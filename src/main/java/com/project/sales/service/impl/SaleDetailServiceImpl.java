package com.project.sales.service.impl;

import com.project.sales.client.InventoryClient;
import com.project.sales.dto.ProductDTO;
import com.project.sales.dto.SaleDetailDto;
import com.project.sales.entity.Product;
import com.project.sales.entity.Sale;
import com.project.sales.entity.SaleDetail;
import com.project.sales.repository.ProductRepository;
import com.project.sales.repository.SaleDetailRepository;
import com.project.sales.service.ProductService;
import com.project.sales.service.SaleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleDetailServiceImpl implements SaleDetailService {

    @Autowired private SaleDetailRepository saleDetailRepository;
    @Autowired private ProductRepository productoRepository;
    @Autowired private ProductService productService;
    @Autowired private InventoryClient inventoryClient;

    @Override
    public void registerSaleDetail(List<SaleDetailDto> saleDetailDtoList, Sale sale) {

        Boolean hasStock = this.registerKardexOut(saleDetailDtoList);
        if(!hasStock){
            throw new ResponseStatusException( HttpStatus.NOT_ACCEPTABLE,"No hay Stock");
        }
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

    private Boolean registerKardexOut(List<SaleDetailDto> saleDetailDtoList){

        List<ProductDTO> productDTOList = saleDetailDtoList.stream()
                .map(saleDetailDto -> ProductDTO.builder()
                        .idProduct(saleDetailDto.getProducId())
                        .cant(saleDetailDto.getCant())
                        .description("SALE")
                        .build())
                .collect(Collectors.toList());

        return inventoryClient.registerOut(productDTOList);
    }


}
