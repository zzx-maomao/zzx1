package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.UserDao;
import vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "AjaxLoginServlet",urlPatterns = "/AjaxLoginServlet")
public class AjaxLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String useName_enter = request.getParameter("usename");
        String passWord_enter = request.getParameter("password");
        String vcode_enter = request.getParameter("vcode");
        String loginradio = request.getParameter("loginradio");
        HttpSession session = request.getSession();
        String vcode = (String) session.getAttribute("vcode");
        UserDao userDao = new UserDao();
        User user = null;
        try {
            user = userDao.getUser(useName_enter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        if (!vcode_enter.equalsIgnoreCase(vcode)) {
            map.put("code", 1);
            map.put("info", "error");
        } else {
            if (user == null) {
                map.put("code", 2);
                map.put("info", "error");
            } else {
                if (!passWord_enter.equals(user.getPassWord())) {
                    map.put("code", 3);
                    map.put("info", "error");
                } else {
                    if (loginradio != null) {
                        Cookie passwordcookie = new Cookie("password", user.getPassWord());
                        Cookie usernamecookie = new Cookie("username", useName_enter);
                        passwordcookie.setMaxAge(7 * 24 * 60 * 60);
                        usernamecookie.setMaxAge(7 * 24 * 60 * 60);
                        response.addCookie(passwordcookie);
                        response.addCookie(usernamecookie);
                    }
                    map.put("code",0);
                    map.put("info", "yes");
                }
            }
            }
            response.setContentType("text/html;charset=utf-8");
            System.out.println(map);
            String jsonstr=new Gson().toJson(map);
            System.out.println(jsonstr);
            PrintWriter out = response.getWriter();
            out.print(jsonstr);
            out.flush();
            out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
