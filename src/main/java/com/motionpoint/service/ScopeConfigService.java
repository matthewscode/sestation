package com.motionpoint.service;

import com.motionpoint.entity.ScopeConfig;

import java.util.List;

/**
 * Created by Matthew on 8/10/2016.
 */
public interface ScopeConfigService {
    boolean createScopeConfig(ScopeConfig scopeConfig);
    List<ScopeConfig> getAllScopeConfigs();
    boolean deleteScopeConfig(long configId);
    ScopeConfig getScopeConfig(long configId);
    boolean updateCurrentScopeConfig(ScopeConfig scopeConfig);
    boolean updateScopeConfig(ScopeConfig scopeConfig);
}

