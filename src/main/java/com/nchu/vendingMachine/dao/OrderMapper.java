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
        "insert into tb_order (id, sale_product_id, ",
        "price, order_time, ",
        "pay_no, status)",
        "values (#{id,jdbcType=INTEGER}, #{saleProductId,jdbcType=INTEGER}, ",
        "#{price,jdbcType=DECIMAL}, #{orderTime,jdbcType=TIMESTAMP}, ",
        "#{payNo,jdbcType=VARCHAR}, #{status,jdbcType=VARCHAR})"
    })
    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    @Select({
        "select",
        "id, sale_product_id, price, order_time, pay_no, status",
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
        "set sale_product_id = #{saleProductId,jdbcType=INTEGER},",
          "price = #{price,jdbcType=DECIMAL},",
          "order_time = #{orderTime,jdbcType=TIMESTAMP},",
          "pay_no = #{payNo,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Order record);
}