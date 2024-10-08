package org.example;

import com.phone.*;

public class FactoryMain {
    public static void main(String[] args) {
        OperatingSystemFactory osf = new OperatingSystemFactory();
        OS obj = osf.getInstance("Closed");
        obj.spec();
    }
}
