package com.nchu.vendingMachine.dao;

import com.nchu.vendingMachine.entity.Sale;
import com.nchu.vendingMachine.entity.SaleExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SaleMapper {
    int countByExample(SaleExample example);

    int deleteByExample(SaleExample example);

    @Delete({
        "delete from tb_sale",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tb_sale (id, product_id, ",
        "vm_id, quantity)",
        "values (#{id,jdbcType=INTEGER}, #{productId,jdbcType=INTEGER}, ",
        "#{vmId,jdbcType=INTEGER}, #{quantity,jdbcType=INTEGER})"
    })
    int insert(Sale record);

    int insertSelective(Sale record);

    List<Sale> selectByExample(SaleExample example);

    @Select({
        "select",
        "id, product_id, vm_id, quantity",
        "from tb_sale",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Sale selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Sale record, @Param("example") SaleExample example);

    int updateByExample(@Param("record") Sale record, @Param("example") SaleExample example);

    int updateByPrimaryKeySelective(Sale record);

    @Update({
        "update tb_sale",
        "set product_id = #{productId,jdbcType=INTEGER},",
          "vm_id = #{vmId,jdbcType=INTEGER},",
          "quantity = #{quantity,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Sale record);
}