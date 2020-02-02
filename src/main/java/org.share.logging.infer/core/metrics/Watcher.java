package org.share.logging.infer.core.metrics;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Auth: Alexander Lo
 * Date: 2020-01-16
 * Description:
 */
public class Watcher implements IWatch<String>{



    private static Watcher instance;

    private Map<String,Boolean> metrics  = new ConcurrentHashMap<>(128);


    private Watcher(){ }


    public void init(String locationPattern){
        //"classpath*:org/share/logging/**/*.class"
        locationPattern =  "classpath*:"+locationPattern.replaceAll("\\.","/")+"/**/*.class";

        CachingMetadataReaderFactory cachingMetadataReaderFactory = new CachingMetadataReaderFactory();

        try {
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources(locationPattern);
            for (int i = 0; i < resources.length; i++) {
                MetadataReader metadataReader = cachingMetadataReaderFactory.getMetadataReader(resources[i]);

                metrics.putIfAbsent(metadataReader.getClassMetadata().getClassName(),true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void unWatch(String className) {
        if (isWatching(className))
            metrics.put(className,false);
    }

    @Override
    public void watching(String className) {
        metrics.put(className,true);

    }
    @Override
    public boolean isWatching(String className){
        return metrics.getOrDefault(className,false);
    }

    public int size(){
        return metrics.size();
    }







    public static Watcher getInstance(){
        //性能判断
        if (instance == null) {
            //进来了多个 强锁
            synchronized (Watcher.class) {
                //这一步是什么鬼

                //下面这句代码其实分为三步：
                //1.开辟内存分配给这个对象
                //2.初始化对象
                //3.将内存地址赋给虚拟机栈内存中的doubleLock变量
                //注意上面这三步，第2步和第3步的顺序是随机的，这是计算机指令重排序的问题
                //假设有两个线程，其中一个线程执行下面这行代码，如果第三步先执行了，就会把没有初始化的内存赋值给doubleLock
                //然后恰好这时候有另一个线程执行了第一个判断if(doubleLock == null)，然后就会发现doubleLock指向了一个内存地址
                //这另一个线程就直接返回了这个没有初始化的内存，所以要防止第2步和第3步重排序
                //
                // 本质是  有线程拿到了 没有初始化的单例
                // 有卵用
                if (instance == null) {
                    instance = new Watcher();
                }
            }
        }

        return instance;
    }

    public Map<String, Boolean> getMetrics() {
        return metrics;
    }

    public void setMetrics(Map<String, Boolean> metrics) {
        this.metrics = metrics;
    }
}
