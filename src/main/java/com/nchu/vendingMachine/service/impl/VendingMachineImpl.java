package com.nchu.vendingMachine.service.impl;

import com.nchu.vendingMachine.dao.VendingMachineMapper;
import com.nchu.vendingMachine.entity.VendingMachine;
import com.nchu.vendingMachine.service.VendingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * 162015班 第13组
 * 智能售货机后台管理系统——售货机
 *
 * @Author: 16201533朱天保
 * @Date: 2019/6/25 14:45
 * @Version 1.0
 */
@Service
public class VendingMachineImpl implements VendingMachineService {
    @Value("${web.upload-path}")
    private String path;
    @Autowired
    private VendingMachineMapper vendingMachineMapper;

    @Override
    public boolean addVendingMachine(VendingMachine vendingMachine, MultipartFile file) {
        try{
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String originalFilename = file.getOriginalFilename();
            String fileName=uuid+originalFilename.substring(originalFilename.lastIndexOf("."));
            vendingMachine.setImgPath(fileName);
            File f=new File(path+fileName);
            if (!f.getParentFile().exists()){
                f.getParentFile().mkdirs();
            }
            file.transferTo(f);
           return vendingMachineMapper.insertSelective(vendingMachine)>0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public VendingMachine getVendingMachineById(Integer id) {
        return vendingMachineMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<VendingMachine> getAllVendingMachine() {
        return vendingMachineMapper.selectByExample(null);
    }

    @Override
    public boolean deleteVendingMachineById(Integer id) {
        return false;
    }

    @Override
    public boolean updateVendingMachineById(VendingMachine vendingMachine, MultipartFile file) {
        try{
            if (file.getSize()>0){
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                String originalFilename = file.getOriginalFilename();
                String fileName=uuid+originalFilename.substring(originalFilename.lastIndexOf("."));
                vendingMachine.setImgPath(fileName);
                File f=new File(path+fileName);
                file.transferTo(f);
            }
            return vendingMachineMapper.updateByPrimaryKeySelective(vendingMachine)>0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
