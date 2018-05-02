package ru.afal.app.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class QueryUtils {
	public static Query jpqlQuery( EntityManagerFactory emf, String query ) {
		EntityManager em = emf.createEntityManager();

		return em.createQuery( query );
	}
}
