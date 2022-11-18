package com.project.sales.client;

import com.project.sales.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="inventory-client", url="${app.config.apiInventary}")
public interface InventoryClient {

    @PostMapping("${app.config.kardexOutEndpoint}")
    Boolean registerOut(@RequestBody List<ProductDTO> products);
}
