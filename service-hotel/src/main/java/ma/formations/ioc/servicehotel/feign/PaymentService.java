package ma.formations.ioc.servicehotel.feign;

import ma.formations.ioc.servicehotel.dto.ProductRequest;
import ma.formations.ioc.servicehotel.dto.StripeResponse;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "SERVICE-PAYMENT")
public interface PaymentService {

        @PostMapping("/api/payments/checkout")
        public ResponseEntity<StripeResponse> checkoutProduct(@RequestBody ProductRequest productRequest);
}