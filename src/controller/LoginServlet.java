package controller;

import dao.GetPower;
import dao.UserDao;
import vo.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet(name = "LoginServlet",urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String useName_enter = request.getParameter("usename");
        String passWord_enter = request.getParameter("password");
        String vcode_enter = request.getParameter("vcode");
        String loginradio=request.getParameter("loginradio");


        UserDao userDao = new UserDao();
        User user =null;
        try {
            user = userDao.getUser(useName_enter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        String vcode = (String)session.getAttribute("vcode");


        HttpSession session1=request.getSession();
        session1.setAttribute("username",useName_enter);

        String path;
        if (!vcode_enter.equalsIgnoreCase(vcode)) {
            request.setAttribute("info", "验证码不正确");
            path = "/error.jsp";
        } else if (user == null) {
            request.setAttribute("info", "用户不存在");
            path = "/error.jsp";
        } else if (!passWord_enter.equals(user.getPassWord())) {
            request.setAttribute("info", "密码错误");
            path = "/error.jsp";
        } else {
            if ("on".equals(loginradio)) {
                Cookie passwordcookie = new Cookie("password", user.getPassWord());
                Cookie usernamecookie=new Cookie("username",useName_enter);
                passwordcookie.setMaxAge(7 * 24 * 60 * 60);
                usernamecookie.setMaxAge(7*24*60*60);
                response.addCookie(passwordcookie);
                response.addCookie(usernamecookie);
            }
            session.setAttribute("current", user);
            path = "/main.jsp";
        }
        request.getRequestDispatcher(path).forward(request,response);

        Cookie[] cookies=request.getCookies();
        System.out.println(cookies+"hhh");
        String cookieusername="";
        String cookiepassword="";
        User cookieuser=null;
        if (cookies!=null){
            for(Cookie cookie1:cookies){
                String cookie1name=cookie1.getName();
                String cookie1Value=cookie1.getValue();
                if (cookie1name.equals("username")){
                    cookieusername=cookie1Value;
                }else if (cookie1name.equals("password"))
                    cookiepassword=cookie1Value;
            }
            try {
                cookieuser=userDao.getUser(cookieusername);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (cookieuser!=null&&cookiepassword.equals(cookieuser.getPassWord()))
            request.getRequestDispatcher("/main.jsp").forward(request,response);
        }else
            request.getRequestDispatcher("/DengLu.html").forward(request,response);
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
