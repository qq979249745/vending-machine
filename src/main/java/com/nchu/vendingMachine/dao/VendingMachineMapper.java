package com.nchu.vendingMachine.dao;

import com.nchu.vendingMachine.entity.VendingMachine;
import com.nchu.vendingMachine.entity.VendingMachineExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface VendingMachineMapper {
    int countByExample(VendingMachineExample example);

    int deleteByExample(VendingMachineExample example);

    @Delete({
        "delete from tb_vending_machine",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tb_vending_machine (id, price, ",
        "location, state, ",
        "description)",
        "values (#{id,jdbcType=INTEGER}, #{price,jdbcType=DECIMAL}, ",
        "#{location,jdbcType=VARCHAR}, #{state,jdbcType=VARCHAR}, ",
        "#{description,jdbcType=VARCHAR})"
    })
    int insert(VendingMachine record);

    int insertSelective(VendingMachine record);

    List<VendingMachine> selectByExample(VendingMachineExample example);

    @Select({
        "select",
        "id, price, location, state, description",
        "from tb_vending_machine",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    VendingMachine selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VendingMachine record, @Param("example") VendingMachineExample example);

    int updateByExample(@Param("record") VendingMachine record, @Param("example") VendingMachineExample example);

    int updateByPrimaryKeySelective(VendingMachine record);

    @Update({
        "update tb_vending_machine",
        "set price = #{price,jdbcType=DECIMAL},",
          "location = #{location,jdbcType=VARCHAR},",
          "state = #{state,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(VendingMachine record);
}