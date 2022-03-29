package com.product.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	boolean existsByModelName(String modelName);

	List<Product> findAllByModelNameIgnoreCase(String name);

	List<Product> findAllByVendorId(long vendorId);

	boolean existsByModelNameIgnoreCase(String modelName);

	void deleteAllByVendorId(long id);

	List<Product> findAllByModelNameIgnoreCaseContaining(String name);

	boolean existsByIdNotAndModelName(long id, String modelName);

	Page<Product> findAllByVendorId(long vendorId, Pageable pageable);

	Page<Product> findAllByModelNameIgnoreCaseContaining(String name, Pageable pageable);

}
