package controller;

import dao.DownloadDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetDownloadListServlet",urlPatterns = "/GetDownloadListServlet")
public class GetDownloadListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DownloadDao downloadDao=new DownloadDao();
        try {
            request.setAttribute("list",downloadDao.getDownLoadlist());
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/download.jsp");
        requestDispatcher.forward(request, response);
    }
}
