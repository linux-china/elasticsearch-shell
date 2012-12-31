package org.mvnsearch.elasticsearch.spring.shell.commands;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.shell.plugin.support.DefaultPromptProvider;
import org.springframework.shell.support.util.OsUtils;

/**
 * ES shell prompt provider
 *
 * @author linux_china
 */
public class ESShellPromptProvider extends DefaultPromptProvider implements InitializingBean {
    /**
     * prompt
     */
    public static String prompt = "ES";
    /**
     * symbol
     */
    public static String symbol = "#";

    /**
     * init method
     *
     * @throws Exception exception
     */
    public void afterPropertiesSet() throws Exception {
        //if Windows OS, adjust symbo to '>'
        if ((OsUtils.isWindows())) {
            symbol = ">";
        }
    }

    /**
     * prompt
     *
     * @return prompt
     */
    @Override
    public String getPrompt() {
        return "[" + prompt + "]" + symbol;
    }

    /**
     * name
     *
     * @return name
     */
    @Override
    public String name() {
        return "ES-Shell-prompt-provider";
    }

}