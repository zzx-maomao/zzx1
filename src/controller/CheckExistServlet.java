package controller;

import com.google.gson.Gson;
import dao.UserDao;
import vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CheckExistServlet",urlPatterns = "/CheckExistServlet")
public class CheckExistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usename=request.getParameter("usename");
        UserDao userDao=new UserDao();
        User user=null;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            user=userDao.getUser(usename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user!=null){
            map.put("code",1);
            map.put("info","用户名已注册");
        }else
        {
            map.put("code",0);
            map.put("info","用户名可以注册");
        }
        String jsonstr = new Gson().toJson(map);
        System.out.println(jsonstr);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonstr);
        out.flush();
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
