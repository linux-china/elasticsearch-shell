package org.mvnsearch.elasticsearch.spring.shell.commands;

import org.mvnsearch.elasticsearch.spring.shell.service.ConfigService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultPromptProvider;
import org.springframework.shell.support.util.OsUtils;
import org.springframework.stereotype.Component;

/**
 * ES shell prompt provider
 *
 * @author linux_china
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
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
     * config service
     */
    private ConfigService configService;

    /**
     * inject config service
     *
     * @param configService config service
     */
    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }

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