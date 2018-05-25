package info.flyingdust.model;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.IOException;
import java.util.Properties;


/**
 * flyingdust.config.properties 키 값을 가지고 있는 객체
 */
public class ConfigFile extends PropertyPlaceholderConfigurer {

    private static ConfigFile configFile = null;

    private Properties properties = new Properties();

    public ConfigFile(){

    }

    protected void loadProperties(Properties props){
        try{
            super.loadProperties(props);
        }catch (IOException e){
            e.printStackTrace();
        }
        Object[] keySet = props.keySet().toArray();
        Object[] keys = keySet;
        int keyLength = keySet.length;
        Object key;
        String val;
        for(int index=0; index < keyLength; ++index){
            key = keys[index];
            val = props.getProperty(key.toString());
            this.properties.setProperty(key.toString(), val);
        }
        configFile = this;
    }

    public static ConfigFile getInstance() {
        return configFile;
    }

    public static String getString(String key){
        return configFile != null ? configFile.properties.getProperty(key) : null;
    }

    public static String get(String key){
        return getString(key);
    }

    public static void clearProperties(){
        configFile.properties = new Properties();
    }

}
