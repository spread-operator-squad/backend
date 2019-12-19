package com.enigma.enumeration;

import com.enigma.constans.ProgressConstants;

public enum TransactionProgress {
    WAITING(ProgressConstants.PROGRESS_WAITING),
    ONPROGRESS(ProgressConstants.PROGRESS_ON_PROGRESS),
    DONE(ProgressConstants.PROGRESS_DONE);

    private String label;

    TransactionProgress(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
