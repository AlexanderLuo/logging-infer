package org.share.logging.infer.core.filter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;
import org.share.logging.infer.core.metrics.Watcher;
import org.slf4j.Marker;

/**
 * Auth: Alexander Lo
 * Date: 2020-01-16
 * Description:
 */
public class ClassInfer extends TurboFilter {


    private Watcher watcher =  Watcher.getInstance();



    @Override
    public FilterReply decide(Marker marker, Logger logger, Level level, String s, Object[] objects, Throwable throwable) {


        if (level != Level.INFO)
            return FilterReply.NEUTRAL;



        // 反正我只对 info 感兴趣
        if (watcher.isWatching(logger.getName()))
            return FilterReply.ACCEPT;


        //不再关注的东西 直接k 掉
        return FilterReply.DENY;
    }


}
