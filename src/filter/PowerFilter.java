package filter;

import dao.GetPower;
import vo.Resource;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PowerFilter implements Filter {
    private String notcheckedpath;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        notcheckedpath= filterConfig.getInitParameter("notcheckedpath");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session=request.getSession();
        GetPower getPower= null;
        List<Resource> resources=null;
        try {
            getPower = new GetPower();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            resources=getPower.getresource((String) session.getAttribute("username"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String>url=new ArrayList<>();
        if (notcheckedpath.indexOf(request.getServletPath()) == -1) {
            for (int i = 0; i < resources.size(); i++) {
                url.add("/"+resources.get(i).getUrl());
            }
            if (!url.contains(request.getServletPath())) {
                request.setAttribute("info", "权限不够无法访问");
                request.getRequestDispatcher("/error.jsp").forward(request, servletResponse);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
        else filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
