package com.tomo.mcauthentication.ddd.event;

import com.tomo.mcauthentication.ddd.AssertionConcern;
import com.tomo.mcauthentication.ddd.domain.DomainEvent;

import java.util.Date;

public class StoredEvent extends AssertionConcern {

    private String eventBody;
    private long eventId;
    private Date occurredOn;
    private String typeName;

    public StoredEvent(String aTypeName, Date anOccurredOn, String anEventBody) {
        this();

        this.setEventBody(anEventBody);
        this.setOccurredOn(anOccurredOn);
        this.setTypeName(aTypeName);
    }

    public StoredEvent(String aTypeName, Date anOccurredOn, String anEventBody, long anEventId) {
        this(aTypeName, anOccurredOn, anEventBody);

        this.setEventId(anEventId);
    }

    public String eventBody() {
        return this.eventBody;
    }

    public long eventId() {
        return this.eventId;
    }

    public Date occurredOn() {
        return this.occurredOn;
    }

    @SuppressWarnings("unchecked")
    public <T extends DomainEvent> T toDomainEvent() {
        Class<T> domainEventClass = null;

        try {
            domainEventClass = (Class<T>) Class.forName(this.typeName());
        } catch (Exception e) {
            throw new IllegalStateException(
                    "Class load error, because: "
                    + e.getMessage());
        }

        T domainEvent =
            EventSerializer
                .instance()
                .deserialize(this.eventBody(), domainEventClass);

        return domainEvent;
    }

    public String typeName() {
        return this.typeName;
    }

    @Override
    public boolean equals(Object anObject) {
        boolean equalObjects = false;

        if (anObject != null && this.getClass() == anObject.getClass()) {
            StoredEvent typedObject = (StoredEvent) anObject;
            equalObjects = this.eventId() == typedObject.eventId();
        }

        return equalObjects;
    }

    @Override
    public int hashCode() {
        int hashCodeValue =
            + (1237 * 233)
            + (int) this.eventId();

        return hashCodeValue;
    }

    @Override
    public String toString() {
        return "StoredEvent [eventBody=" + eventBody + ", eventId=" + eventId + ", occurredOn=" + occurredOn + ", typeName="
                + typeName + "]";
    }

    public StoredEvent() {
        super();

        this.setEventId(-1);
    }

    protected void setEventBody(String anEventBody) {
        this.assertArgumentNotEmpty(anEventBody, "The event body is required.");
        this.assertArgumentLength(anEventBody, 1, 65000, "The event body must be 65000 characters or less.");

        this.eventBody = anEventBody;
    }

    protected void setEventId(long anEventId) {
        this.eventId = anEventId;
    }

    protected void setOccurredOn(Date anOccurredOn) {
        this.occurredOn = anOccurredOn;
    }

    protected void setTypeName(String aTypeName) {
        this.assertArgumentNotEmpty(aTypeName, "The event type name is required.");
        this.assertArgumentLength(aTypeName, 1, 100, "The event type name must be 100 characters or less.");

        this.typeName = aTypeName;
    }
}
