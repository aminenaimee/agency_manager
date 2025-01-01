package ma.formations.ioc.servicepayment.service;

import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import ma.formations.ioc.servicepayment.dto.ProductRequest;
import ma.formations.ioc.servicepayment.dto.StripeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ServiceStripe {

    @Value("${stripe.secretKey}")
    private String secretKey;

    public StripeResponse checkoutProduct(ProductRequest productRequest) {
        Stripe.apiKey = secretKey;

        // Create product data with a placeholder name
        SessionCreateParams.LineItem.PriceData.ProductData productData =
                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                        .setName("Unnamed Product") // Placeholder name
                        .build();

        // Define price data
        SessionCreateParams.LineItem.PriceData priceData =
                SessionCreateParams.LineItem.PriceData.builder()
                        .setCurrency(productRequest.getCurrency() == null ? "USD" : productRequest.getCurrency())
                        .setUnitAmount(productRequest.getAmount())
                        .setProductData(productData)
                        .build();

        // Create line item
        SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
                .setPriceData(priceData)
                .setQuantity(1L)
                .build();

        try {
            // Create session parameters
            SessionCreateParams params = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl("http://localhost:8086/success")
                    .setCancelUrl("http://localhost:8086/cancel")
                    .addLineItem(lineItem)
                    .build();

            // Create the session
            Session session = Session.create(params);

            return StripeResponse.builder()
                    .status("success")
                    .message("Checkout session created successfully")
                    .sessionId(session.getId())
                    .sessionUrl(session.getUrl())
                    .build();

        } catch (Exception e) {
            return StripeResponse.builder()
                    .status("failure")
                    .message("Error creating checkout session: " + e.getMessage())
                    .build();
        }
    }
}
