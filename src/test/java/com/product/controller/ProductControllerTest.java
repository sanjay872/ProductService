package com.product.controller;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.ProductServiceApplication;
import com.product.entity.Product;
import com.product.entity.ProductDetails;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = ProductServiceApplication.class)
@WebAppConfiguration
class ProductControllerTest {

	private MockMvc mvc;
	private static long createdProductId=0; 
	
	@Autowired
	WebApplicationContext webApplicationContext;


	@BeforeEach
	void setUp() {
	      mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	 }
	 
	 
	 @Test
	 @Order(1)
	 public void createProduct() throws Exception {
	    
	    ProductDetails productDetail=new ProductDetails("model","os",1,256,12,1,"dell"); 
	   String inputJson = mapToJson(productDetail);
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/")
	       .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	    
	    int status = mvcResult.getResponse().getStatus();
	    assertEquals(201, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    Product product=mapFromJson(content,Product.class);
	    createdProductId=product.getId();
	 }
	 
	 @Test
	 @Order(2)
	 public void updateProduct() throws Exception {
	    ProductDetails productDetail=new ProductDetails(createdProductId,"model","os",1,256,12,1,"dell"); 
	    String inputJson =mapToJson(productDetail);
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put("/")
	       .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	    int status = mvcResult.getResponse().getStatus();
	    assertEquals(200, status);
	 }
	 

//	 @Test
//	 @Order(3)
//	 public void getProductsList() throws Exception {
//	    String uri = "/product/all";
//	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
//	       .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//	    
//	    int status = mvcResult.getResponse().getStatus();
//	    assertEquals(202, status);
//	    String content = mvcResult.getResponse().getContentAsString();
//	    ProductDetails[] productlist = mapFromJson(content, ProductDetails[].class);
//	    assertTrue(productlist.length > 0);
//	 }
	 
	 @Test
	 @Order(3)
	 public void deleteProduct() throws Exception {
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete("/"+createdProductId)).andReturn();
	    int status = mvcResult.getResponse().getStatus();
	    assertEquals(200, status);
	 }
	 
	 protected String mapToJson(Object obj) throws JsonProcessingException {
	      ObjectMapper objectMapper = new ObjectMapper();
	      return objectMapper.writeValueAsString(obj);
	   }
	  protected <T> T mapFromJson(String json, Class<T> clazz)
	      throws JsonParseException, JsonMappingException, IOException {
	      
	      ObjectMapper objectMapper = new ObjectMapper();
	      return objectMapper.readValue(json, clazz);
	   }
}
