package com.task7.task7.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Mark {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private boolean active;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "mark_id")
    private List<ModelAuto> modelAutoList;

    public Mark() {
        this.active = true;
    }

    public Mark(String name) {
        this.name = name.toUpperCase();
        this.active = true;
    }

    public Mark(String name, List<ModelAuto> modelAutoList) {
        this.name = name.toUpperCase();
        ;
        this.active = true;
        this.modelAutoList = modelAutoList;
    }

    public Mark(Long id, String name, boolean active, List<ModelAuto> modelAutoList) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.modelAutoList = modelAutoList;
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

    public List<ModelAuto> getModelAutoList() {
        return modelAutoList;
    }

    public void setModelAutoList(List<ModelAuto> modelAutoList) {
        this.modelAutoList = modelAutoList;
    }
}
