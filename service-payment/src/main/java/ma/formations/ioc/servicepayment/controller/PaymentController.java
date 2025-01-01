package ma.formations.ioc.servicepayment.controller;

import ma.formations.ioc.servicepayment.dto.ProductRequest;
import ma.formations.ioc.servicepayment.dto.StripeResponse;
import ma.formations.ioc.servicepayment.service.ServiceStripe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private ServiceStripe stripeService;

    @PostMapping("/checkout")
    public ResponseEntity<StripeResponse> checkoutProduct(@RequestBody ProductRequest productRequest) {
        StripeResponse stripeResponse = stripeService.checkoutProduct(productRequest);
        return ResponseEntity.status(HttpStatus.OK).body(stripeResponse);
    }
}
