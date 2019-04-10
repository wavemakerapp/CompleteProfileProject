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

import com.completeprofileproject.sales.Table8;


/**
 * ServiceImpl object for domain model class Table8.
 *
 * @see Table8
 */
@Service("sales.Table8Service")
@Validated
public class Table8ServiceImpl implements Table8Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(Table8ServiceImpl.class);


    @Autowired
    @Qualifier("sales.Table8Dao")
    private WMGenericDao<Table8, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<Table8, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "salesTransactionManager")
    @Override
    public Table8 create(Table8 table8) {
        LOGGER.debug("Creating a new Table8 with information: {}", table8);

        Table8 table8Created = this.wmGenericDao.create(table8);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(table8Created);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public Table8 getById(Integer table8Id) {
        LOGGER.debug("Finding Table8 by id: {}", table8Id);
        return this.wmGenericDao.findById(table8Id);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public Table8 findById(Integer table8Id) {
        LOGGER.debug("Finding Table8 by id: {}", table8Id);
        try {
            return this.wmGenericDao.findById(table8Id);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No Table8 found with id: {}", table8Id, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public List<Table8> findByMultipleIds(List<Integer> table8Ids, boolean orderedReturn) {
        LOGGER.debug("Finding Table8s by ids: {}", table8Ids);

        return this.wmGenericDao.findByMultipleIds(table8Ids, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "salesTransactionManager")
    @Override
    public Table8 update(Table8 table8) {
        LOGGER.debug("Updating Table8 with information: {}", table8);

        this.wmGenericDao.update(table8);
        this.wmGenericDao.refresh(table8);

        return table8;
    }

    @Transactional(value = "salesTransactionManager")
    @Override
    public Table8 partialUpdate(Integer table8Id, Map<String, Object>table8Patch) {
        LOGGER.debug("Partially Updating the Table8 with id: {}", table8Id);

        Table8 table8 = getById(table8Id);

        try {
            ObjectReader table8Reader = this.objectMapper.readerForUpdating(table8);
            table8 = table8Reader.readValue(this.objectMapper.writeValueAsString(table8Patch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", table8Patch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        table8 = update(table8);

        return table8;
    }

    @Transactional(value = "salesTransactionManager")
    @Override
    public Table8 delete(Integer table8Id) {
        LOGGER.debug("Deleting Table8 with id: {}", table8Id);
        Table8 deleted = this.wmGenericDao.findById(table8Id);
        if (deleted == null) {
            LOGGER.debug("No Table8 found with id: {}", table8Id);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), Table8.class.getSimpleName(), table8Id);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "salesTransactionManager")
    @Override
    public void delete(Table8 table8) {
        LOGGER.debug("Deleting Table8 with {}", table8);
        this.wmGenericDao.delete(table8);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public Page<Table8> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Table8s");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public Page<Table8> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Table8s");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service sales for table Table8 to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service sales for table Table8 to {} format", options.getExportType());
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