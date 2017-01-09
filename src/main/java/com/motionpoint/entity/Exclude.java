package com.motionpoint.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Matthew on 8/10/2016.
 */
@Entity
@Table(name = "exclude")
public class Exclude {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;

    private String excludePattern;

    @ManyToOne
    @JsonIgnore
    private ScopeConfig scopeConfig;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExcludePattern() {
        return excludePattern;
    }

    public void setExcludePattern(String excludePattern) {
        this.excludePattern = excludePattern;
    }

    public ScopeConfig getScopeConfig() {
        return scopeConfig;
    }

    public void setScopeConfig(ScopeConfig scopeConfig) {
        this.scopeConfig = scopeConfig;
    }
}
