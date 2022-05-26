package com.tomo.mcauthentication.ddd.domain;

import java.util.HashMap;
import java.util.Map;

public class DomainEventPublisher {

    private static final ThreadLocal<DomainEventPublisher> instance = ThreadLocal.withInitial(() -> new DomainEventPublisher());

    Map<Class, DomainEventSubscriber> subscribers;

    private boolean publishing = false;

    public static DomainEventPublisher instance() {
        return instance.get();
    }

    public void publish(final DomainEvent aDomainEvent) {
        if (!this.isPublishing() && hasSubscribers()) {
            this.setPublishing(true);

            try {
                Class<?> eventType = aDomainEvent.getClass();

                this.subscribers().values().stream()
                        .filter(subscriber -> {
                            Class<?> subscibedType = subscriber.subscribedToEventType();
                            return subscibedType == eventType || subscibedType == DomainEvent.class;
                        })
                        .forEach(subscriber -> subscriber.handleEvent(aDomainEvent));

            } finally {
                this.setPublishing(false);
            }
        }
    }

    public void subscribe(DomainEventSubscriber aSubscriber) {
        this.ensureSubscribers();

        if (!this.isPublishing() && !this.subscribers.containsKey(aSubscriber.getClass())) {
            this.subscribers.put(aSubscriber.getClass(), aSubscriber);
        }
    }

    private boolean isPublishing() {
        return this.publishing;
    }

    private void setPublishing(boolean aFlag) {
        this.publishing = aFlag;
    }

    private void ensureSubscribers() {
        if (!this.hasSubscribers()) {
            this.setSubscribers(new HashMap<>());
        }
    }

    private void setSubscribers(Map<Class, DomainEventSubscriber> sSubscriberMap) {
        this.subscribers = sSubscriberMap;
    }

    private Map<Class, DomainEventSubscriber> subscribers() {
        return this.subscribers;
    }

    private boolean hasSubscribers() {
        return this.subscribers() != null;
    }
}
