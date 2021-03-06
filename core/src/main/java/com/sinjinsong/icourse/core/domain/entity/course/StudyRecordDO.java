package com.sinjinsong.icourse.core.domain.entity.course;

import com.sinjinsong.icourse.core.domain.entity.order.OrderDO;
import com.sinjinsong.icourse.core.domain.entity.student.StudentDO;
import com.sinjinsong.icourse.core.enumeration.course.StudyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudyRecordDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column study_record.id
     *
     * @mbggenerated
     */
    @NotNull
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column study_record.class_id
     *
     * @mbggenerated
     */
    private ClassDO classDO;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column study_record.student_id
     *
     * @mbggenerated
     */
    private StudentDO student;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column study_record.normal_score
     *
     * @mbggenerated
     */
    private Double normalScore;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column study_record.total_score
     *
     * @mbggenerated
     */
    private Double totalScore;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column study_record.status
     *
     * @mbggenerated
     */
    private StudyStatus status;
    private OrderDO order;
}