package com.murphy1.myrecipes.model;

import javax.persistence.Entity;

@Entity
public class Notes {

    private String notes;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
