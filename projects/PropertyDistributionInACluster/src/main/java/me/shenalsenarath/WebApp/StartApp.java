package me.shenalsenarath.WebApp;

import me.shenalsenarath.AppStarter.AppStarter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * Created by shselk on 12/1/2014.
 *
 */



@WebServlet("/start.do")
public class StartApp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();


        AppStarter appStarter = new AppStarter();
        String[] propertiesNames = {"name", "age"};

        Properties properties = null;
        try {
            properties = appStarter.getProperties(propertiesNames);
        } catch (Exception e) {
            e.printStackTrace();
        }

        out.println(properties.toString());


    }
}
