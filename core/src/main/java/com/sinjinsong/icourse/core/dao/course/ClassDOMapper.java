package com.sinjinsong.icourse.core.dao.course;

import com.sinjinsong.icourse.core.domain.entity.course.ClassDO;

public interface ClassDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class
     *
     * @mbggenerated
     */
    int insert(ClassDO record);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class
     *
     * @mbggenerated
     */
    ClassDO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ClassDO record);
}