package com.motionpoint.dao;

import com.motionpoint.entity.Exclude;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Matthew on 8/10/2016.
 */
@Repository
public interface ExcludeDao extends CrudRepository<Exclude, Long> {
}
