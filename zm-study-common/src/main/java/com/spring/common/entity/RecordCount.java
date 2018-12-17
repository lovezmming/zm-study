package com.spring.common.entity;

import java.io.Serializable;

public class RecordCount implements Serializable
{
    /** serialVersionUID */
    private static final long serialVersionUID = -435328320159692760L;

    private int startCount;
    
    private int pageSize;
    
    private int totalCount;

    /**
     * Constructor.
     */
    public RecordCount() {}
    
    /**
     * Constructor.
     * @param start the starting count.
     * @param pageSize the maximum record count.
     * @param total the total record count.
     */
    public RecordCount(int start, int pageSize, int total)
    {
        if ((startCount = (start < 0 ? 0 : start)) >= total)
            startCount = (total - 1 >= 0 ? total - 1 : total);

        this.pageSize = pageSize;
        this.totalCount = total;
    }
    
    /**
     * Constructor.
     * @param rc the record count.
     */
    public RecordCount(RecordCount rc)
    {
        this(rc.getStartCount(), rc.getPageSize(), rc.getTotalCount());
    }
    
    /**
     * Gets the starting record count.
     * @return the starting record count.
     */
    public int getStartCount()
    {
        return startCount;
    }
    
    /**
     * Sets the starting record count.
     * @param count the starting record count.
     */
    public void setStartCount(int count)
    {
        this.startCount = count;
    }
  
    /**
     * Gets the ending record count.
     * @return the ending record count.
     */
    public int getEndCount()
    {
        int end = startCount + pageSize;
        return (end > totalCount ? totalCount : end) - 1;
    }
    
    /**
     * Gets the total record count.
     * @return the total record count.
     */
    public int getTotalCount()
    {
        return totalCount;
    }

    /**
     * Sets the total record count.
     * @param count the total record count.
     */
    public void setTotalCount(int count)
    {
        this.totalCount = count;
    }
    
    /**
     * Gets the maximum number of records to return.
     * @return the maximum number of records to return.
     */
    public int getPageSize()
    {
        return pageSize;
    }
    
    /**
     * Sets the starting maximum record count.
     * @param count the maximum record count.
     */
    public void setPageSize(int count)
    {
        this.pageSize = count;
    }
    
    /**
     * Gets the current page number.
     * @return the current page number.
     */
    public long getPageNumber()
    {
        int pageSize = getPageSize();
        if (pageSize <= 0 || pageSize == Integer.MAX_VALUE)
            return 1;
        
        return (getStartCount() / pageSize) + 1;
    }
    
    /**
     * Gets the total number of pages.
     * @return the total number of pages.
     */
    public int getPageTotal()
    {
        int pageSize = getPageSize();
        if (pageSize <= 0 || pageSize == Integer.MAX_VALUE)
            return 1;
        
        int total = getTotalCount();
        if (total == 0)
            return 1;
        
        int rem = total % pageSize;
        return (total / pageSize) + (rem == 0 ? 0 : 1);
    }

    @Override
    public String toString()
    {
        return "RecordCount [startCount=" + startCount + ", pageSize=" + pageSize + ", totalCount=" + totalCount + "]";
    }
}
