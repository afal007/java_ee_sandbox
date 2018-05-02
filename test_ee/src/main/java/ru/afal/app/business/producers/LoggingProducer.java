package ru.afal.app.business.producers;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.apache.log4j.Logger;

public class LoggingProducer {

    @Produces
    private Logger createLogger( InjectionPoint injectionPoint ) {
        return Logger.getLogger( injectionPoint.getMember().getDeclaringClass().getName() );
    }
}
