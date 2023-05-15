package cn.edu.guet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class TicketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("余票查询");

        /*
        WebServer: Tomcat,JBOSS,WebLogic,WebPhere
        后台获取网页中提交的数据：
        网页中的input数据在点击的时候都被web server封装在 HttpServletRequest 对象中
        request对象是Tomcat容器创建的
        */
        request.setCharacterEncoding("UTF-8");//修改字符编码

        String fromStation = request.getParameter("startStation");
        String toStation = request.getParameter("endStation");
        String departureDate = request.getParameter("departureDate");

        System.out.println(fromStation+"\n"+toStation+"\n"+departureDate);

        String allTickets=TicketSearch.search(fromStation,toStation,departureDate);

        //设置响应类型
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(allTickets);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
