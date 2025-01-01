package ma.formations.ioc.servicepayment.dto;

public class ProductRequest {
    private Long amount;
    private String currency;

    public ProductRequest() {
    }

    public ProductRequest(Long amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
