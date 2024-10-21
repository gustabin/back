package ar.com.santanderrio.obp.servicios.api.offersservice.entities;

public class Campaign {
    private Long id;
    private String description;
    private Product product; 
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
}
