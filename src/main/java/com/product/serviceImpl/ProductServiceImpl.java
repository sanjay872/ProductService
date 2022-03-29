package com.product.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.product.entity.Product;
import com.product.exception.CustomException;
import com.product.repository.ProductRepository;
import com.product.service.ProductService;
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Product createProduct(Product product) {
		if(!productRepository.existsByModelNameIgnoreCase(product.getModelName()))
			return productRepository.save(product);
		else
			throw new CustomException("Product already exist");
	}

	@Override
	public Product updateProduct(Product product) {
		if(productRepository.existsById(product.getId())) 
			if(!productRepository.existsByIdNotAndModelName(product.getId(),product.getModelName()))
				return productRepository.save(product);
			else
				throw new CustomException("Product already exist");
		else
			throw new CustomException("Product not found");
	}

	@Override
	public Page<Product> getAllProducts(int pageNo,int pageSize) {
		Pageable pageable=PageRequest.of(pageNo, pageSize);
		Page<Product> page=productRepository.findAll(pageable);
		return page;
	}

	@Override
	public Page<Product> getProductsByVendorId(long vendorId,int pageNo,int pageSize) {
		Pageable pageable=PageRequest.of(pageNo, pageSize);
		Page<Product> page=productRepository.findAllByVendorId(vendorId,pageable);
		return page;
	}

	@Override
	public Page<Product> getProductsByName(String name,int pageNo,int pageSize) {
		Pageable pageable=PageRequest.of(pageNo, pageSize);
		Page<Product> page=productRepository.findAllByModelNameIgnoreCaseContaining(name,pageable);
		return page;
	}

	@Override
	public void deleteProduct(long id) {
		if(productRepository.existsById(id))
			productRepository.deleteById(id);
		else
			throw new CustomException("Product not found");
	}

	@Override
	public Product getProduct(long id) {
		Product product=productRepository.findById(id).get();
		if(product==null)
			throw new CustomException("product not found");
		return product;
	}

	@Override
	public void deleteAllProductByVendor(long id) {
		productRepository.deleteAllByVendorId(id);
	}

}
