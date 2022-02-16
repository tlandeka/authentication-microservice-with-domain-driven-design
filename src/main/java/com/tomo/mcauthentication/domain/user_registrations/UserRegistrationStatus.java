package com.tomo.mcauthentication.domain.user_registrations;
public enum
UserRegistrationStatus {
    WaitingForConfirmation("WaitingForConfirmation"),
    Confirmed("Confirmed"),
    Expired("Expired");

    String value;

    UserRegistrationStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
