package com.nchu.vendingMachine.dao;

import com.nchu.vendingMachine.entity.Product;
import com.nchu.vendingMachine.entity.ProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ProductMapper {
    int countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    @Delete({
        "delete from tb_product",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tb_product (id, price, ",
        "name, inventory)",
        "values (#{id,jdbcType=INTEGER}, #{price,jdbcType=DECIMAL}, ",
        "#{name,jdbcType=VARCHAR}, #{inventory,jdbcType=INTEGER})"
    })
    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    @Select({
        "select",
        "id, price, name, inventory",
        "from tb_product",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Product selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    @Update({
        "update tb_product",
        "set price = #{price,jdbcType=DECIMAL},",
          "name = #{name,jdbcType=VARCHAR},",
          "inventory = #{inventory,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Product record);
}