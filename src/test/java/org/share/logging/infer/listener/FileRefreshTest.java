package org.share.logging.infer.listener;


import org.junit.Test;
import org.share.logging.infer.core.api.InferOperator;
import org.share.logging.infer.core.listener.FileRefresh;
import org.share.logging.infer.core.metrics.Watcher;

import java.util.concurrent.locks.LockSupport;

/**
 * Auth: Alexander Lo
 * Date: 2020-01-17
 * Description:
 */
public class FileRefreshTest {


    @Test
    public void task(){
        Watcher watcher = Watcher.getInstance();
        watcher.init("org.share.logging");

        InferOperator inferOperator = new InferOperator();
        inferOperator.setWatcher(watcher);



        FileRefresh fileRefresh = new FileRefresh();
        fileRefresh.setPath("/Users/luohao/Diablo/nec/logging-infer/src/test/java/org/share/logging/infer/");


        fileRefresh.subscribe(inferOperator);


        LockSupport.park();



//        fileRefresh.subscribe();



    }




}
