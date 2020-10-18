package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    private String notcheckedpath;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        notcheckedpath= filterConfig.getInitParameter("notcheckedpath");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String path = request.getServletPath();
        if (notcheckedpath.indexOf(path) == -1) {
            HttpSession session = request.getSession();
            if (session.getAttribute("current") == null) {
                request.setAttribute("info", "未登陆无法访问");
                request.getRequestDispatcher("/error.jsp").forward(request, servletResponse);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
            }else{
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }


    @Override
    public void destroy() {

    }
}
