package com.motionpoint.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Matthew on 11/3/2016.
 */
@Entity
@Table(name = "scope_include")
public class ScopeInclude {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String includePattern;

    @ManyToOne
    @JsonIgnore
    private ScopeConfig scopeConfig;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIncludePattern() {
        return includePattern;
    }

    public void setIncludePattern(String includePattern) {
        this.includePattern = includePattern;
    }

    public ScopeConfig getScopeConfig() {
        return scopeConfig;
    }

    public void setScopeConfig(ScopeConfig scopeConfig) {
        this.scopeConfig = scopeConfig;
    }
}
