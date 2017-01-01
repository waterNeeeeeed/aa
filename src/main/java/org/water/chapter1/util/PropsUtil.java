package org.water.chapter1.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by 帝 on 2017/1/1.
 */
public final class PropsUtil {
    public static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    public static Properties loadProps(String fileName){
        Properties props = null;
        InputStream is = null;
        try{
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if (is == null){
                throw new FileNotFoundException(fileName + ":file is not found");

            }
            props = new Properties();
            props.load(is);
        }catch (IOException e) {
            LOGGER.error("load properties file failure", e);
        }finally {
            if (is != null){
                try{
                    is.close();
                }catch (IOException e){
                    LOGGER.error("close input stream failure", e);
                }
            }
        }

        return props;
    }

    public static String getString(Properties props, String key){
        return getString(props, key, "");
    }

    public static String getString(Properties props, String key, String defaultValue){
        String value = defaultValue;
        if (props.containsKey(key)){
            value = props.getProperty(key);
        }
        return value;
    }
}

