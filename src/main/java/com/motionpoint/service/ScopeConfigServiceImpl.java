package com.motionpoint.service;

import com.motionpoint.dao.*;
import com.motionpoint.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew on 8/10/2016.
 */
@Service("scopeConfigService")
public class ScopeConfigServiceImpl implements ScopeConfigService{

    @Autowired
    private ScopeConfigDao scopeConfigDao;
    @Autowired
    private ScopeDomainDao scopeDomainDao;
    @Autowired
    private SeedDao seedDao;
    @Autowired
    private ScopeIncludeDao scopeIncludeDao;
    @Autowired
    private ExcludeDao excludeDao;


    @Override
    @Transactional
    public boolean createScopeConfig(ScopeConfig scopeConfig) {
        ScopeConfig cs = scopeConfigDao.save(scopeConfig);
        for(ScopeDomain sd : cs.getDomainList()){
            sd.setScopeConfig(cs);
            scopeDomainDao.save(sd);
        }
        for(Seed seed : cs.getSeedList()){
            seed.setScopeConfig(cs);
            seedDao.save(seed);
        }
        for(ScopeInclude si : cs.getIncludeList()){
            si.setScopeConfig(cs);
            scopeIncludeDao.save(si);
        }
        for(Exclude exclude : cs.getExcludeList()){
            exclude.setScopeConfig(cs);
            excludeDao.save(exclude);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean updateScopeConfig(ScopeConfig scopeConfig) {
        ScopeConfig oldConfig = scopeConfigDao.findOne(scopeConfig.getId());
        for(Seed seed : oldConfig.getSeedList()){
            seedDao.delete(seed);
        }
        for(ScopeDomain domain : oldConfig.getDomainList()){
            scopeDomainDao.delete(domain);
        }
        for(ScopeInclude include : oldConfig.getIncludeList()){
            scopeIncludeDao.delete(include);
        }
        for(Exclude exclude : oldConfig.getExcludeList()){
            excludeDao.delete(exclude);
        }
        createScopeConfig(scopeConfig);
        return true;
    }

    @Override
    public List<ScopeConfig> getAllScopeConfigs() {
        return (List<ScopeConfig>)scopeConfigDao.findAll();
    }

    @Override
    public boolean deleteScopeConfig(long configId) {
        ScopeConfig sc = scopeConfigDao.findOne(configId);
        scopeConfigDao.delete(sc);
        return true;
    }

    @Override
    @Transactional
    public ScopeConfig getScopeConfig(long configId) {
        ScopeConfig sc = scopeConfigDao.findOne(configId);
        sc.getSeedList().size();
        sc.getExcludeList().size();
        sc.getDomainList().size();
        sc.getIncludeList().size();
        return sc;
    }

    @Override
    public boolean updateCurrentScopeConfig(ScopeConfig scopeConfig) {
//
        return false;
    }
}
