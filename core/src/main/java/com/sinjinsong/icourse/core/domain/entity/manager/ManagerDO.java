package com.sinjinsong.icourse.core.domain.entity.manager;

import com.sinjinsong.icourse.core.domain.entity.role.RoleDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column manager.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column manager.username
     *
     * @mbggenerated
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column manager.password
     *
     * @mbggenerated
     */
    private String password;
    private List<RoleDO> roles;
}