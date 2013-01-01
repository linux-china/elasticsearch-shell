package org.mvnsearch.elasticsearch.spring.shell.service;

import java.util.Map;

/**
 * ES service
 *
 * @author linux_china
 */
public interface ESService {
    /**
     * connect
     *
     * @param host host
     * @param port port
     */
    public void connect(String host, Integer port);

    /**
     * get source
     *
     * @param index index name
     * @param type  type name
     * @param id    id
     * @return source
     */
    public Map<String,Object> getSource(String index, String type, String id);
}
