package com.niit.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.model.Cart;
import com.niit.model.Category;
import com.niit.model.OrderDetails;
import com.niit.model.Product;
import com.niit.model.Supplier;
import com.niit.model.User;

@Configuration
@ComponentScan("com.niit")
@EnableTransactionManagement
public class HiberConfig {

	@Bean(name="dataSource")
	public DataSource getDataSource()
	{
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName("org.h2.Driver");
		datasource.setUrl("jdbc:h2:tcp://localhost/~/closet17");      
		datasource.setUsername("sa");     
		datasource.setPassword(""); 
		System.out.println("H2 Connected");
		
		return datasource;
		
	}
	
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory()
	{
		Properties prop = new Properties();
		prop.put("hibernate.hbm2ddl.auto","update");
		prop.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
		prop.put("hibernate.show_sql","true");
		LocalSessionFactoryBuilder sessionfactorybuilder = new LocalSessionFactoryBuilder(getDataSource());
		sessionfactorybuilder.addProperties(prop);
		sessionfactorybuilder.addAnnotatedClass(User.class);
		sessionfactorybuilder.addAnnotatedClass(Category.class);
		sessionfactorybuilder.addAnnotatedClass(Supplier.class);
		sessionfactorybuilder.addAnnotatedClass(Product.class);
		sessionfactorybuilder.addAnnotatedClass(Cart.class);
		sessionfactorybuilder.addAnnotatedClass(OrderDetails.class);
		return sessionfactorybuilder.buildSessionFactory();
		
		
	}
	
	
	@Bean(name="transManager")
	public HibernateTransactionManager getTransactionManager()
	{
		HibernateTransactionManager tm= new HibernateTransactionManager(getSessionFactory());
		return tm;
		
	}
	
	
}
