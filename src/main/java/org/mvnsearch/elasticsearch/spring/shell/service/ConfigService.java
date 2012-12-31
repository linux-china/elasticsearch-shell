package org.mvnsearch.elasticsearch.spring.shell.service;

/**
 * config service
 *
 * @author linux_china
 */
public interface ConfigService {
    /**
     * available
     *
     * @return available indicat
     */
    public boolean available();

    /**
     * set node
     *
     * @param node node
     */
    public void setNode(String node);

    /**
     * get node
     *
     * @return node
     */
    public String getNode();

    /**
     * set index
     *
     * @param index index name
     */
    public void setIndex(String index);

    /**
     * get index name
     *
     * @return index name
     */
    public String getIndex();

    /**
     * set type
     *
     * @param type type name
     */
    public void setType(String type);

    /**
     * get type
     *
     * @return type
     */
    public String getType();

}
