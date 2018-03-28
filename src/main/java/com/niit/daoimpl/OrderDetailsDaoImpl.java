 package com.niit.daoimpl;



import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.CartDao;
import com.niit.dao.OrderDetailsDao;
import com.niit.model.Cart;
import com.niit.model.OrderDetails;
import com.niit.model.Product;

@Repository
public class OrderDetailsDaoImpl implements OrderDetailsDao{
	
	@Autowired
	SessionFactory sessionFactory;
	
	public OrderDetailsDaoImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	
	@Transactional
	public void insertOrderDetails(OrderDetails orderdetails)
	{
		Session session = sessionFactory.getCurrentSession();
		session.persist(orderdetails);
	}
	@SuppressWarnings("unchecked")
	public List<Cart> findByCartId(String userId)
	{
		Session session = sessionFactory.openSession();
		List<Cart> cr = null;
		try 
		{
			session.beginTransaction();
			cr=(List<Cart>)session.createQuery("from Cart where username=: username").setString("username",userId).list();
			session.getTransaction().commit();
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return cr;
	}
	
	public Cart getCartById(int cartId,String username) //My Cart option on header
	{
		Session session = sessionFactory.openSession();
		Cart cr= null;
		session.beginTransaction();
		cr=(Cart)session.createQuery("from Cart where username=:username and cartProductID=:pro_id").setString("username",username).setInteger("pid", cartId).uniqueResult();
		session.getTransaction().commit();
		return cr;
	}
	
	public void deleteCart(int cartId)
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Cart cr=(Cart)session.get(Cart.class,cartId);
		session.delete(cr);
		session.getTransaction().commit();
	}
	
	public void updateCart(Cart cr)
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(cr);
		session.getTransaction().commit();
	}
}












































/*package com.niit.daoImpl;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.OrderDetailsDao;
import com.niit.model.OrderDetails;


@Repository
public class OrderDetailsDaoImpl implements OrderDetailsDao{
	@Autowired
	SessionFactory sessionFactory;
	public OrderDetailsDaoImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	@Transactional
	public void insertOrderDetails(OrderDetails orderDetails)
	{
		Session session=sessionFactory.getCurrentSession();
		session.save(orderDetails.getShipping());
		session.save(orderDetails.getBilling());
		session.save(orderDetails);
		}
	
	public OrderDetails getOrderDetails(String username,int cart_id)
	{
		Session session=sessionFactory.openSession();
		Query q=session.createQuery("from OrderDetails where username=:username and cart_id=:cart_id");
		q.setParameter("username", username);
		q.setParameter("cart_id", cart_id);
		List<OrderDetails> list=q.list();
		session.close();
		OrderDetails orderDetails=null;
		for(OrderDetails item:list)
			orderDetails=item;
		return orderDetails;
	}
	
	@Transactional
	public void deleteOrderDetails(String username)
	{
		Session session=sessionFactory.openSession();
		Query q=session.createQuery("from OrderDetails where username=:username");
		q.setParameter("username", username);
		List<OrderDetails> list=q.list();
		session.close();
		for(OrderDetails item:list){
		sessionFactory.getCurrentSession().delete(item.getBilling());
		sessionFactory.getCurrentSession().delete(item.getShipping());
		sessionFactory.getCurrentSession().delete(item);		
		}
	}
	
}

*/