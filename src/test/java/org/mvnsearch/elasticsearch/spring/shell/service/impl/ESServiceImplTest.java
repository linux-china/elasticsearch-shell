package org.mvnsearch.elasticsearch.spring.shell.service.impl;

import junit.framework.TestCase;
import org.mvnsearch.elasticsearch.spring.shell.utils.JSonUtils;

/**
 * ES service implemenation test
 *
 * @author linux_china
 */
public class ESServiceImplTest extends TestCase {
    /**
     * ES service
     */
    private ESServiceImpl esService;

    /**
     * Sets up the fixture, for example, open a network connection.
     * This method is called before a test is executed.
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        esService = new ESServiceImpl();
        esService.init();
        esService.connect("localhost", 9300);
    }

    /**
     * test to get source
     */
    public void testGet() {
        String index = "2";
        String type = "item";
        String id = "4";
        System.out.println(JSonUtils.toJson(esService.getSource(index, type, id), true));
    }
}
