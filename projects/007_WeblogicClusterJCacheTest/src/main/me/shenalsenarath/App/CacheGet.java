package me.shenalsenarath.App;

import me.shenalsenarath.JCache.JCache;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by shselk on 11/24/2014.
 */

@WebServlet("/test/cacheget.do")
public class CacheGet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int key=Integer.parseInt(request.getParameter("key"));
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();



        Cache<Integer, String> cache = cacheManager.getCache("test1Cache",Integer.class,String.class);

        String val =cache.get(key);

        if (cache.containsKey(key)) {

            out.print("<html>" +
                    "<body>" +
                    "<h2> " + key + "'s value: " + val + "</h2>" +
                    "</body>" +
                    "</html>");
        }
        else{
            out.print("<html>" +
                    "<body>" +
                    "<h2> " + key + " is not found in the cache"+ "</h2>" +
                    "</body>" +
                    "</html>");
        }
    }
}
