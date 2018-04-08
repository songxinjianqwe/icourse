package com.sinjinsong.icourse.core.domain.entity.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column class.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column class.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column class.course_id
     *
     * @mbggenerated
     */
    private Long courseId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column class.price
     *
     * @mbggenerated
     */
    private Double price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column class.threshold
     *
     * @mbggenerated
     */
    private Integer threshold;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column class.current_count
     *
     * @mbggenerated
     */
    private Integer currentCount;
    private CourseDO course;
}