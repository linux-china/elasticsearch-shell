package org.mvnsearch.elasticsearch.spring.shell.commands;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.plugin.support.DefaultBannerProvider;
import org.springframework.shell.support.util.OsUtils;
import org.springframework.stereotype.Component;

/**
 * elasticsearch Shell banner provider
 *
 * @author linux_china
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ElasticsearchShellBannerProvider extends DefaultBannerProvider implements CommandMarker {
    /**
     * get CLI banner
     *
     * @return banner text
     */
    @SuppressWarnings("StringConcatenationInsideStringBufferAppend")
    public String getBanner() {
        StringBuilder buf = new StringBuilder();
        buf.append("=======================================" + OsUtils.LINE_SEPARATOR);
        buf.append("*                                     *" + OsUtils.LINE_SEPARATOR);
        buf.append("*      Elactisearch Shell             *" + OsUtils.LINE_SEPARATOR);
        buf.append("*                                     *" + OsUtils.LINE_SEPARATOR);
        buf.append("=======================================");
        return buf.toString();
    }

    /**
     * display author information
     *
     * @return author information
     */
    @CliCommand(value = {"author"}, help = "Displays author information")
    public String author() {
        return "Jacky Chan <linux_china@hotmail.com>, Please follow me: http://weibo.com/linux2china";
    }

    /**
     * current version
     *
     * @return version
     */
    public String getVersion() {
        return "1.0.0";
    }

    /**
     * welcome message
     *
     * @return welcome message
     */
    public String getWelcomeMessage() {
        return "Welcome to Elasticsearch Shell! Version: " + getVersion();
    }

    /**
     * commander name
     *
     * @return name
     */
    @Override
    public String name() {
        return "elacticsearch-shell";
    }
}
