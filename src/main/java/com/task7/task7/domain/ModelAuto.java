package com.task7.task7.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class ModelAuto {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private boolean active;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "model_id")
    private List<Modification> modificationList;

    public ModelAuto() {
        this.active = true;
    }

    public ModelAuto(String name) {
        this.name = name;
        this.active = true;
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

    public List<Modification> getModificationList() {
        return modificationList;
    }

    public void setModificationList(List<Modification> modificationList) {
        this.modificationList = modificationList;
    }
}
