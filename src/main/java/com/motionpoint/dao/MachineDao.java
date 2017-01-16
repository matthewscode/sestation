package com.motionpoint.dao;

import com.motionpoint.entity.Machine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Matthew on 1/11/2017.
 */
@Repository
public interface MachineDao extends CrudRepository<Machine, Long> {
}
