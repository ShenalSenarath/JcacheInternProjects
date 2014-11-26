package me.shenalsenarath.App;

import me.shenalsenarath.JCache.JCache;

import javax.cache.Cache;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by shselk on 11/21/2014.
 */
@WebServlet("/test/initcache.do")
public class initCache extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        JCache test = new JCache();
        Cache testCache = test.getCache("test1Cache");
        PrintWriter out = response.getWriter();
        out.print("<html><body><h1>Cache Initialized</h1>");
        test.shutdown();
    }

}
