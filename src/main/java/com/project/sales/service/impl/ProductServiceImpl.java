package com.project.sales.service.impl;

import com.project.sales.entity.Product;
import com.project.sales.repository.ProductRepository;
import com.project.sales.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
