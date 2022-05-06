package com.tomo.mcauthentication.infrastructure.springboot.controller;

public class RestApiRoutes {
    public static final String REST = "/rest";
    public static final String PUBLIC = REST + "/public";
    public static final String PRIVATE = REST + "/private";

    public static class RegistrationRoutes {
        public static final String FORM_REGISTRATION = PUBLIC + "/register/form";
        public static final String CONFIRM_REGISTRATION = PUBLIC + "/register/confirm/";
        public static final String CREATE_PASSWORD_RECOVERY_CODE = PUBLIC + "/register/password/recovery-code";
        public static final String PASSWORD_RESET = PUBLIC + "/register/password/reset";
    }

    public static class AuthRoutes {
        public static final String FORM_LOGIN = PUBLIC + "/login/form";
        public static final String FACEBOOK_LOGIN = PUBLIC + "/login/facebook";
        public static final String GOOGLE_LOGIN = PUBLIC + "/login/google";
        public static final String LOGOUT = PRIVATE + "/logout";
    }

    public static class User {
        public static final String USER = PRIVATE + "/user";
        public static final String GET_USER = PRIVATE + USER + "/{userId}";
    }
}
