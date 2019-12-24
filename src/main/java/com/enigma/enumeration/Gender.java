package com.enigma.enumeration;

import com.enigma.constans.GenderConstants;

public enum Gender {
    MALE(GenderConstants.MALE),
    FEMALE(GenderConstants.FEMALE);

    private String label;

    Gender(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static Gender getGenderByLabel(String label){
        for (Gender gender:Gender.values()) {
            if (gender.getLabel().equals(label)) return gender;
        }
        return null;
    }
}
