package com.motionpoint.controller;

import com.motionpoint.dao.MachineDao;
import com.motionpoint.entity.Machine;
import com.motionpoint.entity.ScopeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Matthew on 1/11/2017.
 */
@RestController
public class AdminController {

    @Autowired
    private MachineDao machineDao;

    @RequestMapping(value = "/machine/create/", method = RequestMethod.POST)
    boolean createMachine(@RequestBody Machine jsonMachine){
        machineDao.save(jsonMachine);
        return true;
    }
    @RequestMapping(value = "/machine/list/all/", method = RequestMethod.GET)
    List<Machine> displayMachineList(){
        return (List<Machine>)machineDao.findAll();
    }
}
