package servlet;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by ann_ on 12.02.15.
 */
@WebFilter (filterName = "MyFilter", urlPatterns = {"/*"})
public class Filter implements javax.servlet.Filter {

    public static final Logger logger = Logger.getLogger(Filter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        logger.debug("Filter");
        req.setCharacterEncoding("UTF-8");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
