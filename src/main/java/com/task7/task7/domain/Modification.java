package com.task7.task7.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Modification {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private boolean active;

    private int periodBegin;

    private int periodEnd;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private ModelAuto modelAuto;

    public Modification() {
        this.active = true;
    }

    public Modification(String name) {
        this.name = name.toLowerCase();
        this.active = true;
    }

    public Modification(String name, int periodBegin, int periodEnd) {
        this.name = name.toLowerCase();
        this.active = true;
        this.periodBegin = periodBegin;
        this.periodEnd = periodEnd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getPeriodBegin() {
        return periodBegin;
    }

    public void setPeriodBegin(int periodBegin) {
        this.periodBegin = periodBegin;
    }

    public int getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(int periodEnd) {
        this.periodEnd = periodEnd;
    }

    @JsonIgnore
    public ModelAuto getModelAuto() {
        return modelAuto;
    }

    @JsonIgnore
    public void setModelAuto(ModelAuto modelAuto) {
        this.modelAuto = modelAuto;
    }
}
