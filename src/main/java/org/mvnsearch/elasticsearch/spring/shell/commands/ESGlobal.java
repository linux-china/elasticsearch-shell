package org.mvnsearch.elasticsearch.spring.shell.commands;

/**
 * ES Global
 *
 * @author linux_china
 */
public class ESGlobal {
    /**
     * node, such as localhost:9300
     */
    public static String node;
    /**
     * index
     */
    public static String index;
    /**
     * type
     */
    public static String type;

    public static String getPrompt() {
        return "ES";
    }
}
