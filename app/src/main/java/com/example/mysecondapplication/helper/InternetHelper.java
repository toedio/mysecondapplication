package com.example.mysecondapplication.helper;

import java.net.InetAddress;

public class InternetHelper {

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            return !ipAddr.equals("");
        } catch (Exception e) {
            return false;
        }
    }
}
