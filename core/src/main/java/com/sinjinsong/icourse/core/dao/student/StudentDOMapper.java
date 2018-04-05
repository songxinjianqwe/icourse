package com.sinjinsong.icourse.core.dao.student;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */

import com.github.pagehelper.Page;
import com.sinjinsong.icourse.core.domain.entity.student.StudentDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated
     */
    int insert(StudentDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated
     */
    StudentDO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(StudentDO record);

    StudentDO findByUsername(@Param("username") String username);

    StudentDO findByEmail(@Param("email") String email);

    Page<StudentDO> findAll(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

}


