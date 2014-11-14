package me.shenalsenarath;


import javax.cache.Cache;

/**
 * Hello world!
 */
public class App {


    public static void main(String[] args) throws InterruptedException {
        JCache test = new JCache();

        Cache testCache=test.getCache("test1");

        //Inserting to cache
        for (int i = 0; i <20; i++) {
            testCache.put("Key: " + i,i);

        }


        //getting values from the cache

        for (int i = 0; i < 20; i++) {
            String TempKey="Key: "+i;
            System.out.println(TempKey+" Value : "+ testCache.get(TempKey));
        }

        Thread.sleep(10 * 1000);// Wait for ten seconds

        //getting values from the cache,  this will return null values as the cache will expire in tes seconds

        for (int i = 0; i < 20; i++) {
            String TempKey="Key: "+i;
            System.out.println(TempKey+" Value : "+ testCache.get(TempKey));
        }

        test.shutdown();




    }


}
