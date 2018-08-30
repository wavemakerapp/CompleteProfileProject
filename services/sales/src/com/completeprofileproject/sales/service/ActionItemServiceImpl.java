/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.completeprofileproject.sales.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

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

import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.completeprofileproject.sales.ActionItem;


/**
 * ServiceImpl object for domain model class ActionItem.
 *
 * @see ActionItem
 */
@Service("sales.ActionItemService")
@Validated
public class ActionItemServiceImpl implements ActionItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActionItemServiceImpl.class);


    @Autowired
    @Qualifier("sales.ActionItemDao")
    private WMGenericDao<ActionItem, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<ActionItem, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "salesTransactionManager")
    @Override
    public ActionItem create(ActionItem actionItem) {
        LOGGER.debug("Creating a new ActionItem with information: {}", actionItem);

        ActionItem actionItemCreated = this.wmGenericDao.create(actionItem);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(actionItemCreated);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public ActionItem getById(Integer actionitemId) {
        LOGGER.debug("Finding ActionItem by id: {}", actionitemId);
        return this.wmGenericDao.findById(actionitemId);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public ActionItem findById(Integer actionitemId) {
        LOGGER.debug("Finding ActionItem by id: {}", actionitemId);
        try {
            return this.wmGenericDao.findById(actionitemId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No ActionItem found with id: {}", actionitemId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public List<ActionItem> findByMultipleIds(List<Integer> actionitemIds, boolean orderedReturn) {
        LOGGER.debug("Finding ActionItems by ids: {}", actionitemIds);

        return this.wmGenericDao.findByMultipleIds(actionitemIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "salesTransactionManager")
    @Override
    public ActionItem update(ActionItem actionItem) {
        LOGGER.debug("Updating ActionItem with information: {}", actionItem);

        this.wmGenericDao.update(actionItem);
        this.wmGenericDao.refresh(actionItem);

        return actionItem;
    }

    @Transactional(value = "salesTransactionManager")
    @Override
    public ActionItem delete(Integer actionitemId) {
        LOGGER.debug("Deleting ActionItem with id: {}", actionitemId);
        ActionItem deleted = this.wmGenericDao.findById(actionitemId);
        if (deleted == null) {
            LOGGER.debug("No ActionItem found with id: {}", actionitemId);
            throw new EntityNotFoundException(String.valueOf(actionitemId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "salesTransactionManager")
    @Override
    public void delete(ActionItem actionItem) {
        LOGGER.debug("Deleting ActionItem with {}", actionItem);
        this.wmGenericDao.delete(actionItem);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public Page<ActionItem> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all ActionItems");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public Page<ActionItem> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all ActionItems");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service sales for table ActionItem to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service sales for table ActionItem to {} format", options.getExportType());
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