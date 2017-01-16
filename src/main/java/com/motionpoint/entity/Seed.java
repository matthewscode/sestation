package com.motionpoint.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Matthew on 8/10/2016.
 */
@Entity
@Table(name = "seed")
public class Seed {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @NotNull
    @NotEmpty
    private String seedUrl;

    @ManyToOne
    @JsonIgnore
    private ScopeConfig scopeConfig;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSeedUrl() {
        return seedUrl;
    }

    public void setSeedUrl(String seedUrl) {
        this.seedUrl = seedUrl;
    }

    public ScopeConfig getScopeConfig() {
        return scopeConfig;
    }

    public void setScopeConfig(ScopeConfig scopeConfig) {
        this.scopeConfig = scopeConfig;
    }
}
