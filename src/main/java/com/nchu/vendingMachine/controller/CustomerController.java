package com.nchu.vendingMachine.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.nchu.vendingMachine.config.AlipayConfig;
import com.nchu.vendingMachine.entity.Order;
import com.nchu.vendingMachine.entity.SaleProduct;
import com.nchu.vendingMachine.service.BuyMachineService;
import com.nchu.vendingMachine.service.OrderService;
import com.nchu.vendingMachine.service.SaleProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 162015班 第13组
 * 智能售货机后台管理系统——顾客购买产品模块
 *
 * @Author: 16201533朱天保
 * @Date: 2019/6/27 23:13
 * @Version 1.0
 */
@Controller
public class CustomerController {

    @Autowired
    private AlipayConfig alipayConfig;
    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private BuyMachineService buyMachineService;

    @Autowired
    private SaleProductService saleProductService;

    @Autowired
    private OrderService orderService;

    /** 同步地址 */
    @Value("${pay.alipay.returnUrl}")
    private String returnUrl;

    /** 异步地址 */
    @Value("${pay.alipay.notifyUrl}")
    private String notifyUrl;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute(buyMachineService.getAllBuyMachineByOnline());
        return "index";
    }

    @GetMapping("/getSaleProduct/{vmId}")
    public String getSaleProduct(@PathVariable(name = "vmId")Integer vmId, Model model){
        model.addAttribute(saleProductService.getAllSaleProduct(vmId));
        return "getSaleProduct";
    }

    @GetMapping("/buyProduct/{saleProductId}")
    public void buyProduct(@PathVariable(name = "saleProductId")Integer saleProductId, HttpServletResponse response) throws IOException, AlipayApiException {
        SaleProduct saleProduct = saleProductService.getSaleProductById(saleProductId);
        Order order = new Order();
        order.setSaleProductId(saleProduct.getId());
        Date date = new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate=sdf.format(new Date());
        int mod=saleProduct.getBuyMachineId()%100;
        newDate=newDate+((mod>9)?mod:"0"+mod);
        order.setOrderTime(date);
        order.setPrice(saleProduct.getProduct().getPrice());
        order.setStatus("未付款");
        order.setPayNo(newDate);
        orderService.insertOrder(order);
        aliPay(order,saleProduct.getProduct().getName(),response);
    }

    private void aliPay(Order order,String subject,HttpServletResponse response ) throws AlipayApiException, IOException {
        AlipayTradePagePayModel model=new AlipayTradePagePayModel();

        // 订单模型
        model.setOutTradeNo(order.getPayNo());
        model.setSubject(subject);
        model.setTotalAmount(String.valueOf(order.getPrice()));
        model.setBody("感谢你的购买，退款联系软件学院16201533");
        model.setProductCode("FAST_INSTANT_TRADE_PAY");

        AlipayTradePagePayRequest alipayTradePagePayRequest = new AlipayTradePagePayRequest();
        alipayTradePagePayRequest.setNotifyUrl(notifyUrl);
        alipayTradePagePayRequest.setReturnUrl(returnUrl);
        alipayTradePagePayRequest.setBizModel(model);

        // 调用SDK生成表单, 并直接将完整的表单html输出到页面
        String form = alipayClient.pageExecute(alipayTradePagePayRequest).getBody();
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(form);
        response.getWriter().flush();
        response.getWriter().close();
    }

    /**
     * 支付宝页面跳转同步通知页面
     */
    @RequestMapping("/return_url")
    public String returnUrl(HttpServletRequest request, HttpServletResponse response,Model model) throws AlipayApiException {
        response.setContentType("text/html;charset=UTF-8");

        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Object o : requestParams.keySet()) {
            String name = (String) o;
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            valueStr = new String(valueStr.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            params.put(name, valueStr);
        }

        boolean verifyResult = AlipaySignature.rsaCheckV1(params, alipayConfig.getAlipayPublicKey(), alipayConfig.getCharset(), "RSA2");
        //商户订单号
        String payNo = new String(request.getParameter("out_trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        if(verifyResult){
            //验证成功
            //请在这里加上商户的业务逻辑程序代码，如保存支付宝交易号
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

            //String trade_status = new String(request.getParameter("trade_status").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            System.out.println(payNo);
            System.out.println(trade_no);

            Order orderByPayNo = orderService.getOrderByPayNo(payNo);

            orderByPayNo.setStatus("已支付");

            orderService.updateOrder(orderByPayNo);
            model.addAttribute(orderByPayNo);
        }
        return "order";
    }
}
