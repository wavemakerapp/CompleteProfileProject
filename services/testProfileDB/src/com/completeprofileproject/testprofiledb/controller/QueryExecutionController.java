/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.completeprofileproject.testprofiledb.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wavemaker.commons.wrapper.StringWrapper;
import com.wavemaker.runtime.data.export.ExportOptions;
import com.wavemaker.runtime.file.manager.ExportedFileManager;
import com.wavemaker.runtime.security.xss.XssDisable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import com.completeprofileproject.testprofiledb.service.TestProfileDBQueryExecutorService;
import com.completeprofileproject.testprofiledb.models.query.*;

@RestController(value = "TestProfileDB.QueryExecutionController")
@RequestMapping("/testProfileDB/queryExecutor")
@Api(value = "QueryExecutionController", description = "controller class for query execution")
public class QueryExecutionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryExecutionController.class);

    @Autowired
    private TestProfileDBQueryExecutorService queryService;

    @Autowired
	private ExportedFileManager exportedFileManager;

    @RequestMapping(value = "/queries/testQuery", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "test")
    public Page<TestQueryResponse> executeTestQuery(Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Executing named query: testQuery");
        Page<TestQueryResponse> _result = queryService.executeTestQuery(pageable);
        LOGGER.debug("got the result for named query: testQuery, result:{}", _result);
        return _result;
    }

    @ApiOperation(value = "Returns downloadable file url for query testQuery")
    @RequestMapping(value = "/queries/testQuery/export", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public StringWrapper exportTestQuery(@RequestBody ExportOptions exportOptions, Pageable pageable) {
        LOGGER.debug("Exporting named query: testQuery");

        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = "testQuery";
        }
        exportedFileName += exportOptions.getExportType().getExtension();

        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName,
                        outputStream -> queryService.exportTestQuery( exportOptions, pageable, outputStream));

        return new StringWrapper(exportedUrl);
    }

}