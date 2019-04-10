/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.completeprofileproject.sales.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.completeprofileproject.sales.ActionItem;

/**
 * Service object for domain model class {@link ActionItem}.
 */
public interface ActionItemService {

    /**
     * Creates a new ActionItem. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on ActionItem if any.
     *
     * @param actionItem Details of the ActionItem to be created; value cannot be null.
     * @return The newly created ActionItem.
     */
    ActionItem create(@Valid ActionItem actionItem);


	/**
     * Returns ActionItem by given id if exists.
     *
     * @param actionitemId The id of the ActionItem to get; value cannot be null.
     * @return ActionItem associated with the given actionitemId.
	 * @throws EntityNotFoundException If no ActionItem is found.
     */
    ActionItem getById(Integer actionitemId);

    /**
     * Find and return the ActionItem by given id if exists, returns null otherwise.
     *
     * @param actionitemId The id of the ActionItem to get; value cannot be null.
     * @return ActionItem associated with the given actionitemId.
     */
    ActionItem findById(Integer actionitemId);

	/**
     * Find and return the list of ActionItems by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param actionitemIds The id's of the ActionItem to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return ActionItems associated with the given actionitemIds.
     */
    List<ActionItem> findByMultipleIds(List<Integer> actionitemIds, boolean orderedReturn);


    /**
     * Updates the details of an existing ActionItem. It replaces all fields of the existing ActionItem with the given actionItem.
     *
     * This method overrides the input field values using Server side or database managed properties defined on ActionItem if any.
     *
     * @param actionItem The details of the ActionItem to be updated; value cannot be null.
     * @return The updated ActionItem.
     * @throws EntityNotFoundException if no ActionItem is found with given input.
     */
    ActionItem update(@Valid ActionItem actionItem);


    /**
     * Partially updates the details of an existing ActionItem. It updates only the
     * fields of the existing ActionItem which are passed in the actionItemPatch.
     *
     * This method overrides the input field values using Server side or database managed properties defined on ActionItem if any.
     *
     * @param actionitemId The id of the ActionItem to be deleted; value cannot be null.
     * @param actionItemPatch The partial data of ActionItem which is supposed to be updated; value cannot be null.
     * @return The updated ActionItem.
     * @throws EntityNotFoundException if no ActionItem is found with given input.
     */
    ActionItem partialUpdate(Integer actionitemId, Map<String, Object> actionItemPatch);

    /**
     * Deletes an existing ActionItem with the given id.
     *
     * @param actionitemId The id of the ActionItem to be deleted; value cannot be null.
     * @return The deleted ActionItem.
     * @throws EntityNotFoundException if no ActionItem found with the given id.
     */
    ActionItem delete(Integer actionitemId);

    /**
     * Deletes an existing ActionItem with the given object.
     *
     * @param actionItem The instance of the ActionItem to be deleted; value cannot be null.
     */
    void delete(ActionItem actionItem);

    /**
     * Find all ActionItems matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching ActionItems.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<ActionItem> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all ActionItems matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching ActionItems.
     *
     * @see Pageable
     * @see Page
     */
    Page<ActionItem> findAll(String query, Pageable pageable);

    /**
     * Exports all ActionItems matching the given input query to the given exportType format.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param exportType The format in which to export the data; value cannot be null.
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return The Downloadable file in given export type.
     *
     * @see Pageable
     * @see ExportType
     * @see Downloadable
     */
    Downloadable export(ExportType exportType, String query, Pageable pageable);

    /**
     * Exports all ActionItems matching the given input query to the given exportType format.
     *
     * @param options The export options provided by the user; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @param outputStream The output stream of the file for the exported data to be written to.
     *
     * @see DataExportOptions
     * @see Pageable
     * @see OutputStream
     */
    void export(DataExportOptions options, Pageable pageable, OutputStream outputStream);

    /**
     * Retrieve the count of the ActionItems in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the ActionItem.
     */
    long count(String query);

    /**
     * Retrieve aggregated values with matching aggregation info.
     *
     * @param aggregationInfo info related to aggregations.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return Paginated data with included fields.
     *
     * @see AggregationInfo
     * @see Pageable
     * @see Page
	 */
    Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable);


}