package org.share.logging.infer.core.api;

import ch.qos.logback.classic.turbo.TurboFilter;
import com.alibaba.fastjson.JSON;
import org.share.logging.infer.core.metrics.IWatch;
import org.share.logging.infer.core.metrics.Watcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Auth: Alexander Lo
 * Date: 2020-01-16
 * Description:
 */
public class InferOperator implements IWatch<String> {

    private Watcher watcher;
    private List<TurboFilter> turboFilters;





    public InferOperator(){
        this.turboFilters = new ArrayList<>();
    }







    public void strat(){
        turboFilters.forEach(TurboFilter::start);
    }
    public void stop(){
        turboFilters.forEach(TurboFilter::stop);
    }


    public Watcher getWatcher() {
        return watcher;
    }

    public void setWatcher(Watcher watcher) {
        this.watcher = watcher;
    }

    public List<TurboFilter> getTurboFilters() {
        return turboFilters;
    }

    public void setTurboFilters(List<TurboFilter> turboFilters) {
        this.turboFilters = turboFilters;
    }

    public void addTurboFilter(TurboFilter turboFilter){
        this.turboFilters.add(turboFilter);
    }



    public int size(){
        return watcher.size();
    }

    @Override
    public void unWatch(String s) {
        watcher.unWatch(s);
    }

    @Override
    public void watching(String s) {
        watcher.watching(s);
    }

    @Override
    public boolean isWatching(String s) {
        return watcher.isWatching(s);
    }


    public String getJsonData(){
        return JSON.toJSONString(watcher.getMetrics());
    }
}
