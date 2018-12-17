/*
 * Copyright (c) 2016 上海极值信息技术有限公司 All Rights Reserved.
 */
package com.spring.common.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * RecordSet.
 * 
 * @author <A HREF="mailto:ljunjie@qiujieda.com">JunJie.Lu</A>
 * @version 1.0, $Revision: 0$, $Date: May 31, 2016$
 * @since 1.0
 */
public class RecordSet extends RecordCount
{

    /** serialVersionUID */
    private static final long serialVersionUID = 3490400620036270197L;

    private Object[] values;

    /**
     * Constructor.
     */
    public RecordSet()
    {
        super(0, 0, 0);
        setValues(new Object[0]);
    }

    /**
     * Constructor.
     * 
     * @param rc
     *            the record count
     * @param values
     *            the values
     */
    public RecordSet(RecordCount rc, Object[] values)
    {
        super(rc.getStartCount(), rc.getPageSize(), rc.getTotalCount());
        setValues(values == null ? new Object[0] : values);
    }

    /**
     * 
     * Constructor.
     * 
     * @param values
     */
    public RecordSet(Collection<Object> values)
    {
        super(0, values.size(), values.size());
        setValues(values == null ? new Object[0] : values.toArray());
    }

    /**
     * 
     * Constructor.
     * 
     * @param values
     */
    public RecordSet(List<Object> values)
    {
        super(0, values.size(), values.size());
        setValues(values == null ? new Object[0] : values.toArray());
    }

    /**
     * Constructor.
     * 
     * @param start
     *            the starting index.
     * @param max
     *            the maximum records to return.
     * @param total
     *            the total number of records.
     * @param values
     *            the query result values.
     */
    public RecordSet(int start, int max, int total, Object[] values)
    {
        super(start, max, total);
        setValues(values == null ? new Object[0] : values);
    }

    /**
     * Gets the first value in the record set.
     * 
     * @return the first value in the record set or null if record set is empty.
     */
    public Object firstValue()
    {
        return (this.values.length == 0 ? null : this.values[0]);
    }

    /**
     * Gets the result values.
     * 
     * @return the result values.
     */
    public Object[] getValues()
    {
        return this.values;
    }

    /**
     * Sets the result values.
     * 
     * @param values
     *            the result values.
     */
    public void setValues(Object[] values)
    {
        this.values = values;
    }

    /**
     * Gets the records as list.
     * 
     * @return the list
     */
    public List<Object> asList()
    {
        return Arrays.asList(getValues());
    }

    /**
     * convert recordSet to list
     * 
     * @param recordSet
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> RecordSetToList()
    {
        if (getValues() == null || getValues().length == 0)
            return new ArrayList<T>();
        return Arrays.stream(getValues()).map(o -> (T) o).collect(Collectors.toList());
    }
}
