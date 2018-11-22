package com.spring.data.database.mybatis.mapper.database;

import com.spring.data.database.mybatis.model.HibernateSequence;
import java.util.List;

public interface HibernateSequenceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hibernate_sequence
     *
     * @mbg.generated
     */
    int insert(HibernateSequence record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hibernate_sequence
     *
     * @mbg.generated
     */
    List<HibernateSequence> selectAll();
}