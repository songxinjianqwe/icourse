package com.sinjinsong.icourse.core.dao.vip;

import com.sinjinsong.icourse.core.domain.entity.vip.VIPDiscountDO;
import org.apache.ibatis.annotations.Param;

public interface VIPDiscountDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_discount
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer vipLevel);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_discount
     *
     * @mbggenerated
     */
    int insert(VIPDiscountDO record);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_discount
     *
     * @mbggenerated
     */
    VIPDiscountDO selectByPrimaryKey(Integer vipLevel);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_discount
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(VIPDiscountDO record);
    double findDiscountByVipLevel(@Param("vipLevel") int vipLevel);
    int findVipLevelByConsumptions(@Param("consumptions") double consumptions);
}