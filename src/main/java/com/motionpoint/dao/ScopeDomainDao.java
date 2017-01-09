package com.motionpoint.dao;

import com.motionpoint.entity.ScopeDomain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Matthew on 11/3/2016.
 */
@Repository
public interface ScopeDomainDao extends CrudRepository<ScopeDomain, Long> {
}
