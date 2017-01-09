package com.motionpoint.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Matthew on 8/10/2016.
 */
@Entity
@Table(name = "scope_config")
public class ScopeConfig {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String name;

    private int issueNum;

    private long timestamp = (new Date().getTime())/1000;

    @OneToMany(mappedBy = "scopeConfig", orphanRemoval = true)
    private List<ScopeDomain> domainList;

    @OneToMany(mappedBy = "scopeConfig", orphanRemoval = true)
    private List<ScopeInclude> includeList;

    @OneToMany(mappedBy = "scopeConfig", orphanRemoval = true)
    private List<Seed> seedList;

    @OneToMany(mappedBy = "scopeConfig", orphanRemoval = true)
    private List<Exclude> excludeList;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIssueNum() {
        return issueNum;
    }

    public void setIssueNum(int issueNum) {
        this.issueNum = issueNum;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<Seed> getSeedList() {
        return seedList;
    }

    public void setSeedList(List<Seed> seedList) {
        this.seedList = seedList;
    }

    public List<Exclude> getExcludeList() {
        return excludeList;
    }

    public void setExcludeList(List<Exclude> excludeList) {
        this.excludeList = excludeList;
    }


    public List<ScopeDomain> getDomainList() {
        return domainList;
    }

    public void setDomainList(List<ScopeDomain> domainList) {
        this.domainList = domainList;
    }

    public List<ScopeInclude> getIncludeList() {
        return includeList;
    }

    public void setIncludeList(List<ScopeInclude> includeList) {
        this.includeList = includeList;
    }
}
