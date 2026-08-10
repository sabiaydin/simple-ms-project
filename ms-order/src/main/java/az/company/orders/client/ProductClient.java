package az.company.orders.client;

import az.company.orders.config.FeignConfig;
import az.company.orders.model.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "ms-product",
        configuration = FeignConfig.class
)
public interface ProductClient {
    @GetMapping("/api/products/{id}")
    ProductResponse getProduct(@PathVariable Long id);

    @PatchMapping("/api/products/{id}")
    void reduceQuantity(@PathVariable Long id,
                        @RequestParam("reduceQuantity") Integer reduceQuantity);
}
