package com.example.applibrary.helper;

import com.example.applibrary.model.EncapsulatedUsers;

public class GeneralData {

    private static EncapsulatedUsers loggedUser ;

    public static void storeLoggedUser(EncapsulatedUsers user) {
        loggedUser = user;
    }

    public static EncapsulatedUsers getLoggedUser() {
        return loggedUser;
    }

    public static void logUser(EncapsulatedUsers user) {
        loggedUser = user;
    }



}
