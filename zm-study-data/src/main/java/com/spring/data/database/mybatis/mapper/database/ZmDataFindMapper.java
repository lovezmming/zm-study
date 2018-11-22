package com.spring.data.database.mybatis.mapper.database;

import com.spring.data.database.mybatis.model.ZmDataFind;
import java.util.List;

public interface ZmDataFindMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zm_data_find
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zm_data_find
     *
     * @mbg.generated
     */
    int insert(ZmDataFind record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zm_data_find
     *
     * @mbg.generated
     */
    ZmDataFind selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zm_data_find
     *
     * @mbg.generated
     */
    List<ZmDataFind> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zm_data_find
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ZmDataFind record);
}