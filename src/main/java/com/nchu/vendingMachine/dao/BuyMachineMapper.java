package com.nchu.vendingMachine.dao;

import com.nchu.vendingMachine.entity.BuyMachine;
import com.nchu.vendingMachine.entity.BuyMachineExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface BuyMachineMapper {
    int countByExample(BuyMachineExample example);

    int deleteByExample(BuyMachineExample example);

    @Delete({
        "delete from tb_buy_machine",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tb_buy_machine (id, vm_id, ",
        "location, state)",
        "values (#{id,jdbcType=INTEGER}, #{vmId,jdbcType=INTEGER}, ",
        "#{location,jdbcType=VARCHAR}, #{state,jdbcType=VARCHAR})"
    })
    int insert(BuyMachine record);

    int insertSelective(BuyMachine record);

    List<BuyMachine> selectByExample(BuyMachineExample example);

    @Select({
        "select",
        "id, vm_id, location, state",
        "from tb_buy_machine",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    BuyMachine selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BuyMachine record, @Param("example") BuyMachineExample example);

    int updateByExample(@Param("record") BuyMachine record, @Param("example") BuyMachineExample example);

    int updateByPrimaryKeySelective(BuyMachine record);

    @Update({
        "update tb_buy_machine",
        "set vm_id = #{vmId,jdbcType=INTEGER},",
          "location = #{location,jdbcType=VARCHAR},",
          "state = #{state,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(BuyMachine record);
}