package org.share.logging.infer.metrics;


import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.share.logging.infer.core.metrics.Watcher;

/**
 * Auth: Alexander Lo
 * Date: 2020-01-16
 * Description:
 */
public class WatcherTest  {


    @Test
    public void createTest(){
        Watcher watcher = Watcher.getInstance();

        watcher.init("org.share.logging");


        System.err.println(JSON.toJSONString(watcher.getMetrics()));



    }




}
