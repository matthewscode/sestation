package com.motionpoint.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Matthew on 11/3/2016.
 */
@Entity
@Table(name = "scope_domain")
public class ScopeDomain {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column
    @NotNull
    @NotEmpty
    private String domain;

    @ManyToOne
    @JsonIgnore
    private ScopeConfig scopeConfig;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public ScopeConfig getScopeConfig() {
        return scopeConfig;
    }

    public void setScopeConfig(ScopeConfig scopeConfig) {
        this.scopeConfig = scopeConfig;
    }
}
