package me.shenalsenarath;


import me.shenalsenarath.JCache.JCache;

import javax.cache.Cache;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/test/testservelet.do")
public class TestServelet extends HttpServlet {
    public TestServelet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        out.println("<html><body><h1>Hello this is the servelet</h1>");


        JCache test = new JCache();

        Cache testCache = test.getCache("test1");

        //Inserting to cache
        for (int i = 0; i < 20; i++) {
            testCache.put("Key: " + i, i);

        }


        //getting values from the cache

        for (int i = 0; i < 20; i++) {
            String TempKey = "Key: " + i;
            out.println(TempKey + " Value : " + testCache.get(TempKey));
        }

        try {
            Thread.sleep(10 * 1000);// Wait for ten seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //getting values from the cache,  this will return null values as the cache will expire in tes seconds

        for (int i = 0; i < 20; i++) {
            String TempKey = "Key: " + i;
            out.println(TempKey + " Value : " + testCache.get(TempKey));
        }

        test.shutdown();
        out.print("</body></html>");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}