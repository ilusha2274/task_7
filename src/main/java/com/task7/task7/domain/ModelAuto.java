package com.task7.task7.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @ManyToOne
    @JoinColumn(name = "mark_id")
    private Mark mark;

    public ModelAuto() {
        this.active = true;
    }

    public ModelAuto(String name) {
        this.name = name.toLowerCase();
        this.active = true;
    }

    public ModelAuto(String name, List<Modification> modificationList) {
        this.name = name.toLowerCase();
        this.active = true;
        this.modificationList = modificationList;
    }

    public ModelAuto(Long id, String name, boolean active, List<Modification> modificationList, Mark mark) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.modificationList = modificationList;
        this.mark = mark;
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

    @JsonIgnore
    public Mark getMark() {
        return mark;
    }

    @JsonIgnore
    public void setMark(Mark mark) {
        this.mark = mark;
    }
}
