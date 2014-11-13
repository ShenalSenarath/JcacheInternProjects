package me.shenalsenarath;


import javax.cache.Cache;

/**
 * Hello world!
 */
public class App {


    public static void main(String[] args) {
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

        test.shutdown();




    }


}
