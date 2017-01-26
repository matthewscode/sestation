package com.motionpoint.dao;

import com.motionpoint.entity.ScopeAnalysis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Matthew on 1/25/2017.
 */
@Repository
public interface ScopeAnalysisDao extends CrudRepository<ScopeAnalysis, Long> {
}