package com.hisense.hitv.utils;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.IOException;
import java.util.Properties;

/**
 * @author wangheping
 */
public class MyPropertyPlaceholderConfigurerUtil extends
    PropertyPlaceholderConfigurer {

    /**
     * @return 属性map集合
     */
    public Properties getProperties() {
        try {
            return mergeProperties();
        } catch (IOException e) {
            return null;
        }
    }
}
