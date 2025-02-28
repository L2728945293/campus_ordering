package cn.edu.jlu.config;

import cn.edu.jlu.interceptor.JwtTokenShopInterceptor;
import cn.edu.jlu.interceptor.JwtTokenUserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
public class WebMvcConfiguration {
    @Autowired
    private JwtTokenShopInterceptor jwtTokenShopInterceptor;
    @Autowired
    private JwtTokenUserInterceptor jwtTokenUserInterceptor;
    /**
     * 注册自定义拦截器
     *
     * @param registry
     */
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtTokenShopInterceptor)
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/register");
        registry.addInterceptor(jwtTokenUserInterceptor)
                .addPathPatterns("/shop/**")
                .excludePathPatterns("/shop/shop/login")
                .excludePathPatterns("/shop/shop/register");
    }


}
