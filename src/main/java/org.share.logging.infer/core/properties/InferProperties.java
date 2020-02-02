package org.share.logging.infer.core.properties;


import org.share.logging.infer.core.listener.FileRefresh;
import org.share.logging.infer.core.listener.Refreshable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;


@ConfigurationProperties("logging.infer")
public class InferProperties {






    private String basePackage;
    private FileRefresh fileRefresh;






    private InferProperties() { }


    public FileRefresh getFileRefresh() {
        return fileRefresh;
    }

    public void setFileRefresh(FileRefresh fileRefresh) {
        this.fileRefresh = fileRefresh;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }


    public Refreshable getRefreshable(){
        if (fileRefresh!=null)
            return fileRefresh;

        return null;
    }
}
