package org.mvnsearch.elasticsearch.spring.shell.commands;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.fusesource.jansi.Ansi;
import org.mvnsearch.elasticsearch.spring.shell.service.ConfigService;
import org.mvnsearch.elasticsearch.spring.shell.service.ESConstants;
import org.mvnsearch.elasticsearch.spring.shell.service.ESService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * ES commands
 *
 * @author linux_china
 */
@Component
public class ESCommands implements CommandMarker {
    /**
     * log
     */
    private Logger log = LoggerFactory.getLogger(ESCommands.class);
    /**
     * The platform-specific line separator.
     */
    public static final String LINE_SEPARATOR = SystemUtils.LINE_SEPARATOR;
    /**
     * config service
     */
    private ConfigService configService;
    /**
     * ES service
     */
    private ESService esService;

    /**
     * inject config service
     *
     * @param configService config service
     */
    @Autowired
    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }

    /**
     * inject ES service
     *
     * @param esService ES service
     */
    @Autowired
    public void setEsService(ESService esService) {
        this.esService = esService;
    }

    /**
     * init method: load current bucket
     */
    @PostConstruct
    public void init() {
        String node = configService.getNode();
        if (StringUtils.isNotEmpty(node)) {
            String[] parts = node.split(":");
            esService.connect(parts[0], Integer.valueOf(parts[1]));
        }
    }

    /**
     * connect ES
     *
     * @param node node
     * @return result
     */
    @CliCommand(value = "connect", help = "connect ES")
    public String connect(@CliOption(key = {""}, mandatory = true, help = "Node") String node) {
        configService.setNode(node);
        String[] parts = node.split(":");
        esService.connect(parts[0], Integer.valueOf(parts[1]));
        return "Connected to " + node;
    }

    /**
     * switch index
     *
     * @param index index name
     * @return result
     */
    @CliCommand(value = "index", help = "use a index")
    public String index(@CliOption(key = {""}, mandatory = true, help = "index name") String index) {
        configService.setIndex(index);
        return "Index switched to " + index;
    }

    /**
     * switch type
     *
     * @param type type
     * @return result
     */
    @CliCommand(value = "type", help = "use a type")
    public String type(@CliOption(key = {""}, mandatory = true, help = "type name") String type) {
        configService.setType(type);
        return "Type switched to " + type;
    }

    /**
     * get source detail
     *
     * @param id source id
     * @return result
     */
    @CliCommand(value = "get", help = "Get source detail")
    public String get(@CliOption(key = {""}, mandatory = true, help = "source id") String id) {
        String source = esService.getSource(ESConstants.index, ESConstants.type, id);
        if (StringUtils.isEmpty(source)) {
            return wrappedAsRed("No source found with id:" + id + " !");
        }
        return source;
    }


    /**
     * wrapped as red with Jansi
     *
     * @param text text
     * @return wrapped text
     */
    private String wrappedAsRed(String text) {
        return Ansi.ansi().fg(Ansi.Color.RED).a(text).toString();
    }


    /**
     * wrapped as yellow with Jansi
     *
     * @param text text
     * @return wrapped text
     */
    private String wrappedAsYellow(String text) {
        return Ansi.ansi().fg(Ansi.Color.YELLOW).a(text).toString();
    }

}
