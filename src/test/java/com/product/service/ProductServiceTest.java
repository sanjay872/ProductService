package com.product.service;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.product.entity.Product;
import com.product.repository.ProductRepository;
import com.product.serviceImpl.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private ProductServiceImpl productService;
	
	private Product product;
	
	@BeforeEach
	void setup() {
		product=new Product(1,"modelName","os",1,1,1,1);
	}
	
	@AfterEach
	void cleanup() {
		product=null;
	}
	
	@Test
	void createProductTest() {
		when(productRepository.existsByModelNameIgnoreCase(product.getModelName())).thenReturn(false);
		when(productRepository.save(any())).thenReturn(product);
		productService.createProduct(product);
		verify(productRepository,times(1)).save(any());
	}
	
	@Test
	void updateProductTest() {
		when(productRepository.existsById(product.getId())).thenReturn(true);
		when(productRepository.existsByIdNotAndModelName(product.getId(),product.getModelName())).thenReturn(false);
		when(productRepository.save(any())).thenReturn(product);
		productService.updateProduct(product);
		verify(productRepository,times(1)).save(any());
	}

	@Test
	void getProductByIdTest()
	{
		Optional<Product> res=Optional.of(product);
		when(productRepository.findById(product.getId())).thenReturn(res);
		Product expectedProduct=productService.getProduct(product.getId());
		assertEquals(expectedProduct.getId(),product.getId());
	}
		
	@Test 
	void deleteProductTest() {
		when(productRepository.existsById(product.getId())).thenReturn(true);
		productService.deleteProduct(product.getId());
		verify(productRepository,times(1)).deleteById(product.getId());
	}
	
	@Test
	void deleteAllProductByVendorIdTest() {
		productService.deleteAllProductByVendor(product.getVendorId());
		verify(productRepository,times(1)).deleteAllByVendorId(product.getVendorId());
	}


	
}
