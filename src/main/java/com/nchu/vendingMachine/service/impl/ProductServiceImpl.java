package com.nchu.vendingMachine.service.impl;

import com.nchu.vendingMachine.dao.ProductMapper;
import com.nchu.vendingMachine.entity.Product;
import com.nchu.vendingMachine.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * 162015班 第13组
 * 智能售货机后台管理系统——商品采购
 *
 * @Author: 16201531 周小华
 * @Date: 2019/6/27 19:13
 * @Version 1.0
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Value("${web.upload-path}")
    private String path;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public boolean addProduct(Product product, MultipartFile file) {
        try{
            String originalFilename = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String fileName=uuid+originalFilename.substring(originalFilename.lastIndexOf("."));
            File f=new File(path+fileName);
            product.setImgPath(fileName);
            if (!f.getParentFile().exists()){
                f.getParentFile().mkdirs();
            }
            file.transferTo(f);
            return productMapper.insertSelective(product)>0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Product getProductById(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> getAllProduct() {
        return productMapper.selectByExample(null);
    }



    @Override
    public boolean updateProductById(Product order) {
        return productMapper.updateByPrimaryKeySelective(order) > 0;
    }

    @Override
    public boolean updateProductById(Product product, MultipartFile file) {
        try{
            if(file.getSize()>0){
                String originalFilename = file.getOriginalFilename();
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                String fileName=uuid+originalFilename.substring(originalFilename.lastIndexOf("."));
                product.setImgPath(fileName);
                File f=new File(path+fileName);
                file.transferTo(f);
            }
            return productMapper.updateByPrimaryKeySelective(product)>0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
