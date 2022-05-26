package com.tomo.mcauthentication.infrastructure.processing.builder;

import com.tomo.mcauthentication.application.configuration.RequestHandler;
import com.tomo.mcauthentication.application.contracts.Request;
import com.tomo.mcauthentication.infrastructure.processing.PipelineBuilder;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public abstract class AbstractPipelineBuilder<E extends Request, S extends RequestHandler> implements PipelineBuilder<E, S>, ApplicationContextAware {

    protected E request;
    protected S handler;

    protected ApplicationContext applicationContext;

    @Override
    public PipelineBuilder with(E aRequest) {
        this.request = aRequest;
        this.handler = this.getHandler();
        return this;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    protected S getHandler() {
        String beanName = this.getHandlerName();
        return (S) applicationContext.getBean(beanName);
    }

    protected String getHandlerName() {
        String fullHandlerName = this.request.getClass().getSimpleName() + "Handler";
        return Character.toLowerCase(fullHandlerName.charAt(0)) + fullHandlerName.substring(1);
    }
}
