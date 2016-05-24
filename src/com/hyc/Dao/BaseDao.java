package com.hyc.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class BaseDao {
	
	private final static Configuration configuration = new Configuration().configure();
	private final static ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
	private final static SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	private final static Session session = sessionFactory.openSession();
	private final static Transaction transaction = session.beginTransaction();
	
	public Session getSession() {
		return session;
	}
	public Transaction getTransaction() {
		return transaction;
	}
}
