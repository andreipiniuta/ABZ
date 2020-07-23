package sample.service;


import sample.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    public  void saveProduct(Product product) throws IOException;

    List<Product> getAllProduct() throws IOException;

    Product getProductByID(Integer ID) throws IOException;

    public void deleteProduct (Integer ID) throws IOException;
}
