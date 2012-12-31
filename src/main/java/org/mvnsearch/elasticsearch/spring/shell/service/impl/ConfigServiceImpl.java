package org.mvnsearch.elasticsearch.spring.shell.service.impl;

import org.jetbrains.annotations.Nullable;
import org.mvnsearch.elasticsearch.spring.shell.service.ConfigService;
import org.mvnsearch.elasticsearch.spring.shell.service.ESConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * config service implementation
 *
 * @author linux_china
 */
@Component("configService")
public class ConfigServiceImpl implements ConfigService {
    /**
     * log
     */
    private Logger log = LoggerFactory.getLogger(ConfigServiceImpl.class);
    /**
     * ES config file name
     */
    private String cfgFileName = ".es.cfg";
    /**
     * global properties
     */
    private Properties properties;

    /**
     * post construct
     */
    @PostConstruct
    public void init() {
        try {
            properties = new Properties();
            File cfgFile = new File(new File(System.getProperty("user.home")), cfgFileName);
            if (cfgFile.exists()) {
                properties.load(new FileInputStream(cfgFile));
            }
            ESConstants.node = getNode();
            ESConstants.index = getIndex();
            ESConstants.type = getType();
        } catch (Exception ignore) {

        }
    }

    /**
     * available info
     *
     * @return available infor
     */
    public boolean available() {
        return properties.containsKey("NODE");
    }

    /**
     * set node
     *
     * @param node node
     */
    public void setNode(String node) {
        ESConstants.node = node;
        setProperty("node", node);
    }

    /**
     * get node
     *
     * @return node
     */
    public String getNode() {
        return getProperty("node");
    }

    /**
     * set index
     *
     * @param index index name
     */
    public void setIndex(String index) {
        ESConstants.index = index;
        setProperty("index", index);
    }

    /**
     * get index name
     *
     * @return index name
     */
    public String getIndex() {
        return getProperty("index");
    }

    /**
     * set type
     *
     * @param type type name
     */
    public void setType(String type) {
        ESConstants.type = type;
        setProperty("type", type);
    }

    /**
     * get type
     *
     * @return type
     */
    public String getType() {
        return getProperty("type");
    }

    /**
     * get configuration
     *
     * @param key key
     * @return value
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * update config
     *
     * @param key   key
     * @param value value
     */
    public void setProperty(String key, @Nullable String value) {
        try {
            File cfgFile = new File(new File(System.getProperty("user.home")), cfgFileName);
            if (value == null) {
                properties.remove(key);
            } else {
                properties.setProperty(key, value);
            }
            properties.store(new FileOutputStream(cfgFile), null);
        } catch (Exception ignore) {

        }
    }

}