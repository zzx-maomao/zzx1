package controller;

import dao.CreatVcodeImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet(name = "CreatVcodeImageServlet",urlPatterns = "/CreatVcodeImageServlet")
public class CreatVcodeImageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CreatVcodeImage creatVcodeImage = new CreatVcodeImage();
        String vcodeString = creatVcodeImage.creatCode();
        HttpSession session = request.getSession();
        session.setAttribute("vcode", vcodeString);
        response.setContentType("img/jpg");
        response.setHeader("pragma", "No-cache");
        response.setHeader("cache-control", "no-cache");
        response.setDateHeader("expirse", 0L);
        BufferedImage image = creatVcodeImage.creatimage(vcodeString);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        outputStream.flush();
        outputStream.close();
    }

}
