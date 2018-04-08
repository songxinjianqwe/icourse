package com.sinjinsong.icourse.core.domain.entity.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinjinsong.icourse.common.properties.DateTimeProperties;
import com.sinjinsong.icourse.core.domain.entity.role.RoleDO;
import com.sinjinsong.icourse.core.enumeration.student.StudentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.username
     *
     * @mbggenerated
     */
    @NotNull
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.password
     *
     * @mbggenerated
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.reg_time
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = DateTimeProperties.LOCAL_DATE_TIME_PATTERN)
    private LocalDateTime regTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.email
     *
     * @mbggenerated
     */
    @NotNull
    @Email
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.status
     *
     * @mbggenerated
     */
    private StudentStatus status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.consumptions
     *
     * @mbggenerated
     */
    private Double consumptions;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.vip_level
     *
     * @mbggenerated
     */
    private Integer vipLevel;
    private List<RoleDO> roles;
    @NotNull
    private String nickname;
}