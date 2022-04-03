package com.product.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.product.entity.Product;
import com.product.entity.ProductDetails;
import com.product.entity.Vendor;
import com.product.exception.CustomException;
import com.product.service.ProductService;

@RestController
//@RequestMapping("product")
public class ProductController {

	@Value("${vendor.url}")
	private String vendorUrl;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping
	public String check() {
		return "welcome!";
	}
	
	@PostMapping
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		Product newProduct=productService.createProduct(product);
		return new ResponseEntity<Product>(newProduct,HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		Product updatedProduct=productService.updateProduct(product);
		return new ResponseEntity<Product>(updatedProduct,HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDetails> getProduct(@PathVariable("id") long id) {
		Product product = productService.getProduct(id);
		if (product == null)
			throw new CustomException("product not found");
		ResponseEntity<Vendor> vendor = restTemplate
				.getForEntity(this.vendorUrl+"/"+ product.getVendorId(), Vendor.class);
		ProductDetails productDetails = new ProductDetails();
		BeanUtils.copyProperties(product, productDetails);
		productDetails.setVendorName(vendor.getBody().getName());
		return new ResponseEntity<ProductDetails>(productDetails, HttpStatus.OK);
	}

	@GetMapping("all")
	public ResponseEntity<List<ProductDetails>> getAllProducts(
			@RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "4") int pageSize) {

		Page<Product> products = productService.getAllProducts(pageNo, pageSize);
		List<ProductDetails> productDetails = new ArrayList<>();
		for (Product product : products.getContent()) {
			ResponseEntity<Vendor> vendor = restTemplate
					.getForEntity(this.vendorUrl+"/"+ product.getVendorId(), Vendor.class);
			ProductDetails productDetail = new ProductDetails();
			BeanUtils.copyProperties(product, productDetail);
			productDetail.setVendorName(vendor.getBody().getName());
			productDetails.add(productDetail);
		}

		// header
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("totalPages", String.valueOf(products.getTotalPages()));
		headers.add("totalElement",String.valueOf(products.getTotalElements()));

		return new ResponseEntity<List<ProductDetails>>(productDetails, headers, HttpStatus.ACCEPTED);
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<List<ProductDetails>> getAllProductByName(@PathVariable("name") String name,
			@RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "4") int pageSize) {

		Page<Product> products = productService.getProductsByName(name, pageNo, pageSize);
		List<ProductDetails> productDetails = new ArrayList<>();
		for (Product product : products.getContent()) {
			ResponseEntity<Vendor> vendor = restTemplate
					.getForEntity(this.vendorUrl+"/" + product.getVendorId(), Vendor.class);
			ProductDetails productDetail = new ProductDetails();
			BeanUtils.copyProperties(product, productDetail);
			productDetail.setVendorName(vendor.getBody().getName());
			productDetails.add(productDetail);
		}

		// header
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("totalElement",String.valueOf(products.getTotalElements()));
		headers.add("totalPages", String.valueOf(products.getTotalPages()));

		return new ResponseEntity<List<ProductDetails>>(productDetails, headers, HttpStatus.ACCEPTED);
	}

	@GetMapping("/vendor/{name}")
	public ResponseEntity<List<ProductDetails>> getAllProductByVendorName(@PathVariable("name") String name,
			@RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "4") int pageSize) {

		Vendor vendor = restTemplate.getForObject(this.vendorUrl+"/name/" + name, Vendor.class);
		if (vendor == null)
			return new ResponseEntity<List<ProductDetails>>(HttpStatus.NO_CONTENT);
		Page<Product> products = productService.getProductsByVendorId(vendor.getId(), pageNo, pageSize);
		List<ProductDetails> productDetails = new ArrayList<>();
		for (Product product : products.getContent()) {
			ProductDetails productDetail = new ProductDetails();
			BeanUtils.copyProperties(product, productDetail);
			productDetail.setVendorName(vendor.getName());
			productDetails.add(productDetail);
		}

		// header
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("totalElement",String.valueOf(products.getTotalElements()));
		headers.add("totalPages", String.valueOf(products.getTotalPages()));

		return new ResponseEntity<List<ProductDetails>>(productDetails,headers, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("id") long id) {
		productService.deleteProduct(id);
		return new ResponseEntity<Product>(HttpStatus.OK);
	}

	@DeleteMapping("/vendor/{id}")
	public ResponseEntity<Product> deleteProductByVendorId(@PathVariable("id") long id) {
		productService.deleteAllProductByVendor(id);
		return new ResponseEntity<Product>(HttpStatus.OK);
	}
}
