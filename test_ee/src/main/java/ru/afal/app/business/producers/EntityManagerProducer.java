package ru.afal.app.business.producers;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerProducer {

    @Produces
    public EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory( "PU" ).createEntityManager();
    }

    private void closeEntityManager( @Disposes EntityManager em ) {
        em.close();
    }
}
