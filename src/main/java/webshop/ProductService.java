package webshop;

public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void saleProduct(long id, int amount) {
        if (productRepository.getCurrentStockOfProduct(id) < amount) {
            throw new IllegalArgumentException("Not enough products in stock.");
        }
        productRepository.updateProductStock(id, amount);
    }
}
