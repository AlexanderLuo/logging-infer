package org.share.logging.infer;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.turbo.TurboFilter;
import org.share.logging.infer.core.api.InferOperator;
import org.share.logging.infer.core.filter.ClassInfer;
import org.share.logging.infer.core.metrics.Watcher;
import org.share.logging.infer.core.properties.InferProperties;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@ConditionalOnProperty({"logging.infer.base-package"})
@EnableConfigurationProperties({InferProperties.class})
@Configuration
public class InferConfigurationStarter implements InitializingBean {

    @Autowired
    private InferProperties inferProperties;



    public InferConfigurationStarter(){


    }



    @Override
    public void afterPropertiesSet() throws Exception {
        inferProperties.getRefreshable().subscribe(inferOperator());
    }


    @Bean
    public InferOperator inferOperator(){
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        Watcher watcher = Watcher.getInstance();
        watcher.init(inferProperties.getBasePackage());

        InferOperator inferOperator = new InferOperator();
        inferOperator.setWatcher(watcher);

        TurboFilter classInfer =  new ClassInfer();
        inferOperator.addTurboFilter(classInfer);


        inferOperator.getTurboFilters().forEach(lc::addTurboFilter);
        inferOperator.strat();
        return inferOperator;
    }
}
