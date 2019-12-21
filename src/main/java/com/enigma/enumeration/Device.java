package com.enigma.enumeration;

public enum Device {
    MOBILE("mobile"),
    WEB("web");

    private String label;

    Device(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static Device getDeviceByLabel(String label){
        for (Device device : Device.values()){
            if (device.getLabel().equals(label)) return device;
        }
        return null;
    }
}
