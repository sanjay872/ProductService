package com.product.service;


import org.springframework.data.domain.Page;

import com.product.entity.Product;

public interface ProductService {
	Product createProduct(Product product);
	Product updateProduct(Product product);
	Product getProduct(long id);
	Page<Product> getAllProducts(int pageNo,int pageSize);
	Page<Product> getProductsByVendorId(long vendorId,int pageNo,int pageSize);
	Page<Product> getProductsByName(String name,int pageNo,int pageSize);
	void deleteProduct(long id);
	void deleteAllProductByVendor(long id);
}
