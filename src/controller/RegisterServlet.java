package controller;


import com.google.gson.Gson;
import dao.NewUser;
import tools.Jdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "RegisterServlet",urlPatterns ="/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uesname=request.getParameter("usename");
        String name=request.getParameter("name");
        String password=request.getParameter("password");
        String province=request.getParameter("province");
        String city=request.getParameter("city");
        String email=request.getParameter("email");
        NewUser newUser=new NewUser();
        try {
            newUser.InsertNerUser(uesname,name,password,email,province,city);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code",1);
        map.put("info","注册成功");
        String jsonstr = new Gson().toJson(map);
        System.out.println(jsonstr);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
