package controller;

import com.google.gson.Gson;
import dao.GetProvinceAndCity;
import vo.City;
import vo.Province;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GetProvinceAndCityServlet",urlPatterns = "/GetProvinceAndCityServlet")
public class GetProvinceAndCityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String province=request.getParameter("province");
         String jsonstr="";
        GetProvinceAndCity getProvinceAndCity=new GetProvinceAndCity();
        List<Province> provincelist=new ArrayList<>();
        List<City>cityList=new ArrayList<>();
        if (province==null){
            try {
                provincelist= (ArrayList<Province>) getProvinceAndCity.GetProvince();
            } catch (Exception e) {
                e.printStackTrace();
            }
            jsonstr=new Gson().toJson(provincelist);
        }else{
            try {
                cityList=getProvinceAndCity.GetCity(province);
            } catch (Exception e) {
                e.printStackTrace();
            }
            jsonstr=new Gson().toJson(cityList);
        }
        PrintWriter out = response.getWriter();
        out.print(jsonstr);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
