package com.gaoming.web.servlet;


import com.alibaba.fastjson.JSON;
import com.gaoming.pojo.*;
import com.gaoming.service_20211015_114634.ShopCarService;
import com.gaoming.service_20211015_114634.OrderService;
import com.gaoming.service_20211015_114634.impl.ShopCarServletImpl;
import com.gaoming.service_20211015_114634.impl.OrderServletImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/shopcar/*")
public class ShopCarServlet extends BaseServlet{
    private ShopCarService shopcarService = new ShopCarServletImpl();


    //
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理乱码问题
        request.setCharacterEncoding("utf-8");
        //1.调用service查询

        List<ShopCar> shopcars = shopcarService.selectAll();

        System.out.println(shopcars);

        //2.转为JSON
        String jsonString = JSON.toJSONString(shopcars);

        //3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectAllByCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.接受数据


        //2.接受session
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        System.out.println("接收session"+customer);
        System.out.println("接收username"+customer.getUsername());
        //2.转为Order对象
        //Order order = JSON.parseObject(params, Order.class);
        //Customer customer1 = JSON.parseObject(params, Customer.class);
//        order.setUserName(customer.getUsername());
//        System.out.println(order);



        //调用deleteByName
        List<ShopCar> shopCars = shopcarService.selectAllByCustomer(customer.getUsername());
        //System.out.println(shopCars);
        //2.转为JSON
        String jsonString = JSON.toJSONString(shopCars);

        //3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);

    }


    //添加数据
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受数据
        //BufferedReader br = request.getReader();
        BufferedReader br = request.getReader();
        String params = br.readLine();

        //2.接受session
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        System.out.println(customer);

        //2.转为ShopCar对象
        ShopCar shopcar = JSON.parseObject(params, ShopCar.class);
        shopcar.setCustomer(customer.getUsername());

        //获得库存数
        Integer ordered = shopcar.getOrdered();
        System.out.println(shopcar);
        System.out.println("这里这里"+shopcar.getOrdered());
        //判断库存是否足够
        if(ordered >= shopcar.getShopSum()){
            //成功表示
            shopcarService.add(shopcar);
            response.getWriter().write("success");
        }else{
            response.getWriter().write("erro");
        }
        //调用add
//        shopcarService.add(shopcar);
//        //成功表示
//        response.getWriter().write("success");
    }

    public void add2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受数据
        //BufferedReader br = request.getReader();
        BufferedReader br = request.getReader();
        String params = br.readLine();

        //2.接受session
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        System.out.println(customer);

        //2.转为ShopCar对象
        ShopCar shopcar = JSON.parseObject(params, ShopCar.class);
        //shopcar.setCustomer(customer.getUsername());
            //成功表示
            shopcarService.add2(shopcar);
            response.getWriter().write("success");


    }

    public void updateOrdered(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受数据
        //BufferedReader br = request.getReader();
        BufferedReader br = request.getReader();
        String params = br.readLine();


        //2.转为ShopCar对象
        ShopCar shopcar = JSON.parseObject(params, ShopCar.class);
        System.out.println(shopcar);

        //调用add
        shopcarService.updateOrdered(shopcar);
//        //成功表示
        response.getWriter().write("success");
    }

    /**
     * 删除数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受数据
        BufferedReader br = request.getReader();
        String params = br.readLine();

        //2.转为shopcar对象
        ShopCar shopcar = JSON.parseObject(params, ShopCar.class);

        //调用deleteByName
        shopcarService.deleteByName(shopcar.getBrandName());
        //成功表示
        response.getWriter().write("success");
    }






}



