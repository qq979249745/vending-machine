package com.nchu.vendingMachine.dao;

import com.nchu.vendingMachine.entity.Order;
import com.nchu.vendingMachine.entity.OrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface OrderMapper {
    int countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    @Delete({
        "delete from tb_order",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tb_order (id, sale_id, ",
        "order_time, pay_type, ",
        "pay_no)",
        "values (#{id,jdbcType=INTEGER}, #{saleId,jdbcType=INTEGER}, ",
        "#{orderTime,jdbcType=TIMESTAMP}, #{payType,jdbcType=VARCHAR}, ",
        "#{payNo,jdbcType=VARCHAR})"
    })
    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    @Select({
        "select",
        "id, sale_id, order_time, pay_type, pay_no",
        "from tb_order",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    @Update({
        "update tb_order",
        "set sale_id = #{saleId,jdbcType=INTEGER},",
          "order_time = #{orderTime,jdbcType=TIMESTAMP},",
          "pay_type = #{payType,jdbcType=VARCHAR},",
          "pay_no = #{payNo,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Order record);
}