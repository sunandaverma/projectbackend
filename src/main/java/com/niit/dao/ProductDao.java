package com.niit.dao;

import java.util.List;

import com.niit.model.Category;
import com.niit.model.Product;

public interface ProductDao {

	public void insertOrUpdateProduct(Product product);
	public Product getProduct(int pro_id);
	public List<Product> getProductDetails();
	public List<Product> getProductCatWise(Category category);
	public void deleteProduct(Product product);
	public Product findByPID(int pro_id);
	public List<Product> retrieve();
	public List<Product> getProductByCatID(int cid);
	public void deleteProd(int pro_id);
}
