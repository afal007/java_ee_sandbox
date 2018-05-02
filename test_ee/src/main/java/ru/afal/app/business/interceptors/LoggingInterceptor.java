package ru.afal.app.business.interceptors;


import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

import ru.afal.app.business.interceptors.interceptorbinding.Loggable;

@Interceptor
@Loggable
public class LoggingInterceptor {

    @Inject
    private Logger logger;

    @AroundInvoke
    public Object logMethod( InvocationContext ic ) throws Exception {
        logger.debug( ic.getMethod().getName() );

        return ic.proceed();
    }

}
