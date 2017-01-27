package com.motionpoint.controller;

import com.motionpoint.dao.ScopeAnalysisDao;
import com.motionpoint.entity.ScopeAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Matthew on 1/26/2017.
 */
@Controller
public class ApiController {

    @Autowired
    private ScopeAnalysisDao scopeAnalysisDao;

    @RequestMapping(value = "/api/analysis/get/{analysisId}/", method = RequestMethod.GET)
    public ScopeAnalysis getAnalysis(@PathVariable long analysisId) {
        return scopeAnalysisDao.findOne(analysisId);
    }
}
