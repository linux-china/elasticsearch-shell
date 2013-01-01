package org.mvnsearch.elasticsearch.spring.shell.service.impl;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.mvnsearch.elasticsearch.spring.shell.service.ESService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * ES service implementation
 *
 * @author linux_china
 */
@Component("eSService")
public class ESServiceImpl implements ESService {
    /**
     * es client
     */
    private TransportClient esClient;

    @PostConstruct
    public void init() {

    }

    /**
     * connect
     *
     * @param host host
     * @param port port
     */
    public void connect(String host, Integer port) {
        esClient = new TransportClient();
        esClient.addTransportAddress(new InetSocketTransportAddress(host, port));
    }

    /**
     * get source
     *
     * @param index index name
     * @param type  type name
     * @param id    id
     * @return source
     */
    public Map<String,Object> getSource(String index, String type, String id) {
        GetResponse response = esClient.prepareGet(index, type, id)
                .execute()
                .actionGet();
        return response.getSource();
    }
}
