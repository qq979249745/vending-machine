package com.nchu.vendingMachine.controller;

import com.nchu.vendingMachine.entity.Product;
import com.nchu.vendingMachine.entity.RestResponse;
import com.nchu.vendingMachine.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * 162015班 第13组
 * 智能售货机后台管理系统——商品采购模块
 *
 * @Author: 16201533朱天保
 * @Date: 2019/6/27 19:03
 * @Version 1.0
 */
@Controller()
@RequestMapping("/back")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/business/addProduct")
    public String addProduct(){
        return "back/business/addProduct";
    }

    @ResponseBody
    @PostMapping("/business/addProduct")
    public RestResponse addProduct(@Valid Product product, BindingResult result,@RequestParam("file") MultipartFile file){
        if (file.getSize()<=0){
            return RestResponse.fail().add("data","请上传图片");
        }
        if (result.hasErrors()){
            return RestResponse.fail().add("data",result.getFieldError().getDefaultMessage());
        }else {
            return productService.addProduct(product,file)? RestResponse.success().add("data","/back/business/editProduct/"+product.getId())
                    :RestResponse.fail().add("data","上传失败");
        }
    }

    @GetMapping("/business/showProduct")
    public String showProduct(Model model){
        List<Product> allProduct = productService.getAllProduct();
        model.addAttribute(allProduct);
        return "back/business/showProduct";
    }

    @GetMapping("/business/editProduct/{id}")
    public String editProduct(@PathVariable(value = "id")Integer id, Model model){
        Product product = productService.getProductById(id);
        model.addAttribute(product);
        return "back/business/editProduct";
    }
    @PostMapping("/business/editProduct")
    @ResponseBody
    public RestResponse editProduct(@Valid Product product, BindingResult result,@RequestParam("file") MultipartFile file ){
        if (result.hasErrors()){
            return RestResponse.fail().add("data",result.getFieldError().getDefaultMessage());
        }else {
            return productService.updateProductById(product,file)? RestResponse.success().add("data","/back/business/editProduct/"+product.getId())
                    :RestResponse.fail().add("data","上传失败");
        }
    }
}
