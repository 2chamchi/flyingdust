package info.flyingdust.config.resources;


import info.flyingdust.model.ConfigFile;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.system.SystemProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;


@Configuration
@EnableWebMvc
public class ResourcesConfig implements WebMvcConfigurer {

    /* IDE 에서 Environment Variable 설정 필요 */
    private String configHomeName = "FLYINGDUST_HOME";

    /* HOME 경로에 위치한 config 파일명  */
    private String configFileName = "flyingdust.config.properties";

    @Bean
    public PropertyPlaceholderConfigurer configurer(){
        PropertyPlaceholderConfigurer configurer = new ConfigFile();
        // configurer.setLocation(new ClassPathResource("config.properties"));
        configurer.setLocation(new FileSystemResource(SystemProperties.get(configHomeName) + File.separator + configFileName));

        return configurer;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        /* swagger-ui용, webjars 경로 변경 */
        registry.addResourceHandler("/swagger/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

    }
}
