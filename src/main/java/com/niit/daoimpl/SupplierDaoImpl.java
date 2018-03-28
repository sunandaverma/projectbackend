package com.niit.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.SupplierDao;
import com.niit.model.Product;
import com.niit.model.Supplier;

@Repository
public class SupplierDaoImpl implements SupplierDao{

	@Autowired
	SessionFactory sessionFactory;
	public SupplierDaoImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public void insertOrUpdateSupplier(Supplier supplier) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(supplier);
	}

	public Supplier getSupplier(int suppid) {
		
		Session session = sessionFactory.openSession();
		Supplier supplier = session.get(Supplier.class, suppid);
		session.close();
		return supplier;
	}

	public List<Supplier> getSupplierDetails() {
		
		Session session = sessionFactory.openSession();
		List<Supplier> supplierlist = session.createQuery("from Supplier").list();
		session.close();
		return supplierlist;
	}

	@Transactional
	public void deleteSupplier(Supplier supplier) {
		
		sessionFactory.getCurrentSession().delete(supplier);
	}

	public List<Supplier> retrieve()
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Supplier> li = session.createQuery("from Supplier").list();
		session.getTransaction().commit();
		return li;
	}
	
	public void deleteSupp(int supp_id)
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Supplier s = (Supplier)session.get(Supplier.class, supp_id);
		session.delete(s);
		session.getTransaction().commit();
	}
	
}
