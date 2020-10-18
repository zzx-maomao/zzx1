package controller;

import dao.DownloadDao;
import vo.Download;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

@WebServlet(name = "DownloadServlet",urlPatterns = "/DownloadServlet")
public class DownlodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        DownloadDao dao=new DownloadDao();
        Download download=null;
        try {
            download=dao.getDownload(Integer.parseInt(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(download);
        String path=request.getServletContext().getRealPath("/"+download.getPath());
        String fileName=path.substring(path.lastIndexOf("\\")+1);
        response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));
        InputStream inputStream=new FileInputStream(path);
        int len=0;

        byte[] buffer=new byte[1024];
        ServletOutputStream servletOutputStream=response.getOutputStream();
        while ((len=inputStream.read(buffer))!=-1){
            servletOutputStream.write(buffer,0,len);
        }
        inputStream.close();
        servletOutputStream.close();
    }
}
