package org.share.logging.infer.api;


import org.junit.Test;
import org.share.logging.infer.core.api.InferOperator;
import org.share.logging.infer.core.metrics.Watcher;

/**
 * Auth: Alexander Lo
 * Date: 2020-01-16
 * Description:
 */
public class InferOperatorTest  {


    @Test
    public void createTest(){
        Watcher watcher = Watcher.getInstance();
        watcher.init("org.share.logging");

        InferOperator inferOperator = new InferOperator();
        inferOperator.setWatcher(watcher);




    }


}
