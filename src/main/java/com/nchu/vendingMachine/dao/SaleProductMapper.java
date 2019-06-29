package com.nchu.vendingMachine.dao;

import com.nchu.vendingMachine.entity.SaleProduct;
import com.nchu.vendingMachine.entity.SaleProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SaleProductMapper {
    int countByExample(SaleProductExample example);

    int deleteByExample(SaleProductExample example);

    @Delete({
        "delete from tb_sale_product",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tb_sale_product (id, product_id, ",
        "sale_num, buy_machine_id)",
        "values (#{id,jdbcType=INTEGER}, #{productId,jdbcType=INTEGER}, ",
        "#{saleNum,jdbcType=INTEGER}, #{buyMachineId,jdbcType=INTEGER})"
    })
    int insert(SaleProduct record);

    int insertSelective(SaleProduct record);

    List<SaleProduct> selectByExample(SaleProductExample example);

    @Select({
        "select",
        "id, product_id, sale_num, buy_machine_id",
        "from tb_sale_product",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    SaleProduct selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SaleProduct record, @Param("example") SaleProductExample example);

    int updateByExample(@Param("record") SaleProduct record, @Param("example") SaleProductExample example);

    int updateByPrimaryKeySelective(SaleProduct record);

    @Update({
        "update tb_sale_product",
        "set product_id = #{productId,jdbcType=INTEGER},",
          "sale_num = #{saleNum,jdbcType=INTEGER},",
          "buy_machine_id = #{buyMachineId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SaleProduct record);
}