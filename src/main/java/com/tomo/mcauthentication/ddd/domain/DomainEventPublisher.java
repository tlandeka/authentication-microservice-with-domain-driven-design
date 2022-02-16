package com.tomo.mcauthentication.ddd.domain;

import java.util.ArrayList;
import java.util.List;

public class DomainEventPublisher {

    private static final ThreadLocal<DomainEventPublisher> instance = ThreadLocal.withInitial(() -> new DomainEventPublisher());

    List<DomainEventSubscriber> subscribers;

    private boolean publishing = false;

    public static DomainEventPublisher instance() {
        return instance.get();
    }

    public void publish(final DomainEvent aDomainEvent) {
        if (!this.isPublishing() && hasSubscribers()) {
            this.setPublishing(true);

            try {
                Class<?> eventType = aDomainEvent.getClass();

                this.subscribers().stream()
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

        if (!this.isPublishing()) {
            this.subscribers().add(aSubscriber);
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
            this.setSubscribers(new ArrayList());
        }
    }

    private void setSubscribers(List sSubscriberList) {
        this.subscribers = sSubscriberList;
    }

    private List<DomainEventSubscriber> subscribers() {
        return this.subscribers;
    }

    private boolean hasSubscribers() {
        return this.subscribers() != null;
    }
}
