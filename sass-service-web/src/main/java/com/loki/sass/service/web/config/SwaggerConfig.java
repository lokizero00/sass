package com.loki.sass.service.web.config;

import com.google.common.base.Predicate;
import com.loki.sass.common.constant.Constants;
import com.loki.sass.common.dto.CurrentUserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.google.common.base.Predicates.or;
import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.builders.PathSelectors.regex;


/**
 * Created by lokizero00
 * date: 2018-03-21
 */
@Configuration
@EnableSwagger2
@ComponentScan("com.loki.sass")
public class SwaggerConfig extends WebMvcConfigurerAdapter{

    @Value("${system.swagger.api-host}")
    private String apiHost;

    @Value("${system.swagger.enable}")
    private boolean enable;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .apiInfo(apiInfo())
                .ignoredParameterTypes(CurrentUserInfo.class)
                .select()
                .paths(paths())
                .build()
                .securitySchemes(security());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("智慧楼宇Web服务接口")
                .description("智慧楼宇Web服务接口")
                .version("1.0")
                .build();
    }

    private Predicate<String> paths() {
        return or(
                regex("/login/adminLogin"),
                regex("/login/adminLoginOut"),
                regex("/admin/.*"),
                regex("/zone/.*"),
                regex("/property/.*"),
                regex("/region/.*"),
                regex("/door/.*"),
                regex("/permission/.*"),
                regex("/role/.*")
        );
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(new String[]{"swagger-ui.html"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/"});
        registry.addResourceHandler(new String[]{"/webjars*"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/webjars/"});
    }

    private List<ApiKey> security() {
        return newArrayList(
                new ApiKey(Constants.TOKEN_HEADER, Constants.TOKEN_HEADER, "header")
        );
    }
}
