package org.share.logging.infer.core.listener;

import org.share.logging.infer.core.api.InferOperator;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Auth: Alexander Lo
 * Date: 2020-01-17
 * Description:
 */
public class FileRefresh implements Refreshable {

    private String path;
    private boolean run;
    private Timer timer;

    public FileRefresh(){ }



    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }



    @Override
    public void subscribe(InferOperator inferOperator) {
        if (!run){
            synchronized (this){
                if (!run){
                    run = true;
                    timer = new Timer();

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            String data =  inferOperator.getJsonData();
                            try {
                                FileWriter fileWriter = new FileWriter(new File(getPath(),"logging_infer.json"));
                                fileWriter.write(data);
                                fileWriter.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                        }
                    }, 1000);

                }
            }
        }

    }

    @Override
    public void unSubscribe(InferOperator inferOperator) {
        run = false;
        timer.cancel();
    }
}
