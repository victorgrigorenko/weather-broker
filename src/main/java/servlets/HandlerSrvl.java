package servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import weather.jms.IMessageSender;
import weather.service.IQueryHandler;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;


@WebServlet(urlPatterns="/HandlerSrvl")
public class HandlerSrvl extends HttpServlet {
    private ApplicationContext ctx;

    private IQueryHandler handler;

    @Override
    public void init() throws ServletException {
        super.init();
        ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        handler = (IQueryHandler)ctx.getBean("handler");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String city = request.getParameter("city");
        String status = (handleRequest(city))?"Прогноз сохранен":"Неудача";
        request.setAttribute("status",status);
        getServletContext().getRequestDispatcher("/WEB-INF/views/Start.jsp").forward(request, response);
    }

    private boolean handleRequest(String city) {
        return handler.handle(city);
    }
}
