package com.niit.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.ProductDao;
import com.niit.model.Category;
import com.niit.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	SessionFactory sessionFactory;
	public ProductDaoImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public void insertOrUpdateProduct(Product product) {
		
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		
		
	}

	public Product getProduct(int pro_id) {
		
		Session session = sessionFactory.openSession();
		Product product = session.get(Product.class, pro_id);
		session.close();
		return product;
	}

	public List<Product> getProductDetails() {

		Session session = sessionFactory.openSession();
		List<Product> productList = session.createQuery("from Product").list();
		session.close();
		return productList;
	}

	public List<Product> getProductCatWise(Category category) {
		
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Product where category=:category");
		query.setParameter("category", category);
		List<Product> catWiseProdList = query.list();
		return catWiseProdList;
	}

	public void deleteProduct(Product product) {
		
		sessionFactory.getCurrentSession().delete(product);
	}
	
	public List<Product> retrieve()
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Product> li = session.createQuery("from Product").list();
		session.getTransaction().commit();
		return li;
	}
	
	
	public Product findByPID(int pro_id)
	{
		Session session = sessionFactory.openSession();
		Product p = null;
		try 
		{
			session.beginTransaction();
			p=session.get(Product.class, pro_id);
			session.getTransaction().commit();
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return p;
	}

	public List<Product> getProductByCatID(int cid) 
	{
		
		Session session = sessionFactory.openSession();
		List<Product> c = null;
		try
		{
			session.beginTransaction();
			c = session.createQuery("from Product where cid=" +cid).list();
			session.getTransaction().commit();
		}
		catch (HibernateException e) 
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}
	

	public void deleteProd(int pro_id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Product p = (Product)session.get(Product.class, pro_id);
		session.delete(p);
		session.getTransaction().commit();
	}

	
	
}
