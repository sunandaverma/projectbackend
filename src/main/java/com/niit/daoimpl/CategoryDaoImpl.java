package com.niit.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.CategoryDao;
import com.niit.model.Category;
import com.niit.model.Product;

@Repository
public class CategoryDaoImpl implements CategoryDao{
	
	@Autowired
	SessionFactory sessionFactory;
	public CategoryDaoImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void insertOrUpdateCategory(Category category)
	{
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(category);
	}

	public Category getCategory(int cat_id)
	{
		Session session = sessionFactory.openSession();
		Category category = session.get(Category.class, cat_id);
		session.close();
		return category;
	}

	public List<Category> getCategoryDetails()
	{
		Session session = sessionFactory.openSession();
		List<Category> category_list = session.createQuery("from Category").list();
		session.close();
		return category_list;
	}

	@Transactional
	public void deleteCategory(Category category)
	{
		sessionFactory.getCurrentSession().delete(category);
	}

	public List<Category> retrieve()
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Category> li = session.createQuery("from Category").list();
		session.getTransaction().commit();
		return li;
	}
	
	public void deleteCat(int cat_id)
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Category c = (Category)session.get(Category.class, cat_id);
		session.delete(c);
		session.getTransaction().commit();
	}
}
