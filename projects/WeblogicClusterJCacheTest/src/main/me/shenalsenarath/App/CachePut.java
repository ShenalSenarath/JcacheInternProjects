package me.shenalsenarath.App;

import me.shenalsenarath.JCache.JCache;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.OptionalFeature;
import javax.cache.spi.CachingProvider;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.Properties;

/**
 * Created by shselk on 11/24/2014.
 */

@WebServlet("/test/cacheput.do")
public class CachePut extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int key = Integer.parseInt(request.getParameter("key"));

        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();



        Cache<Integer, String> cache = cacheManager.getCache("test1Cache",Integer.class,String.class);
        cache.put(key, "Val " + key);

        String val = cache.get(key);


        out.print("<html>" +
                "<body>" +
                "<h2> " + key + " has being added successfully to the cache" + "</h2>" +val+
                "</body>" +
                "</html>");


    }
}
