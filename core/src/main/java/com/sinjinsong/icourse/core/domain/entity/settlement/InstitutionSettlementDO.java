package com.sinjinsong.icourse.core.domain.entity.settlement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinjinsong.icourse.common.properties.DateTimeProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstitutionSettlementDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column institution_settlement.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column institution_settlement.institution_id
     *
     * @mbggenerated
     */
    private Long institutionId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column institution_settlement.percent
     *
     * @mbggenerated
     */
    @NotNull
    private Double percent;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column institution_settlement.create_time
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = DateTimeProperties.LOCAL_DATE_TIME_PATTERN)
    private LocalDateTime createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column institution_settlement.value
     *
     * @mbggenerated
     */
    @NotNull
    private Double value;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column institution_settlement.order_id
     *
     * @mbggenerated
     */
    @NotNull
    private Long orderId;
}