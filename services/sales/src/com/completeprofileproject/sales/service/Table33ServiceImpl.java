/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.completeprofileproject.sales.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.wavemaker.commons.InvalidInputException;
import com.wavemaker.commons.MessageResource;
import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.completeprofileproject.sales.Table33;


/**
 * ServiceImpl object for domain model class Table33.
 *
 * @see Table33
 */
@Service("sales.Table33Service")
@Validated
public class Table33ServiceImpl implements Table33Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(Table33ServiceImpl.class);


    @Autowired
    @Qualifier("sales.Table33Dao")
    private WMGenericDao<Table33, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<Table33, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "salesTransactionManager")
    @Override
    public Table33 create(Table33 table33) {
        LOGGER.debug("Creating a new Table33 with information: {}", table33);

        Table33 table33Created = this.wmGenericDao.create(table33);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(table33Created);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public Table33 getById(Integer table33Id) {
        LOGGER.debug("Finding Table33 by id: {}", table33Id);
        return this.wmGenericDao.findById(table33Id);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public Table33 findById(Integer table33Id) {
        LOGGER.debug("Finding Table33 by id: {}", table33Id);
        try {
            return this.wmGenericDao.findById(table33Id);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No Table33 found with id: {}", table33Id, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public List<Table33> findByMultipleIds(List<Integer> table33Ids, boolean orderedReturn) {
        LOGGER.debug("Finding Table33s by ids: {}", table33Ids);

        return this.wmGenericDao.findByMultipleIds(table33Ids, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "salesTransactionManager")
    @Override
    public Table33 update(Table33 table33) {
        LOGGER.debug("Updating Table33 with information: {}", table33);

        this.wmGenericDao.update(table33);
        this.wmGenericDao.refresh(table33);

        return table33;
    }

    @Transactional(value = "salesTransactionManager")
    @Override
    public Table33 partialUpdate(Integer table33Id, Map<String, Object>table33Patch) {
        LOGGER.debug("Partially Updating the Table33 with id: {}", table33Id);

        Table33 table33 = getById(table33Id);

        try {
            ObjectReader table33Reader = this.objectMapper.readerForUpdating(table33);
            table33 = table33Reader.readValue(this.objectMapper.writeValueAsString(table33Patch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", table33Patch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        table33 = update(table33);

        return table33;
    }

    @Transactional(value = "salesTransactionManager")
    @Override
    public Table33 delete(Integer table33Id) {
        LOGGER.debug("Deleting Table33 with id: {}", table33Id);
        Table33 deleted = this.wmGenericDao.findById(table33Id);
        if (deleted == null) {
            LOGGER.debug("No Table33 found with id: {}", table33Id);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), Table33.class.getSimpleName(), table33Id);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "salesTransactionManager")
    @Override
    public void delete(Table33 table33) {
        LOGGER.debug("Deleting Table33 with {}", table33);
        this.wmGenericDao.delete(table33);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public Page<Table33> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Table33s");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public Page<Table33> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Table33s");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service sales for table Table33 to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service sales for table Table33 to {} format", options.getExportType());
        this.wmGenericDao.export(options, pageable, outputStream);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }



}