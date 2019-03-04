package com.loki.sass.service.login.interceptor;

/**
 * Created by lokizero00
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loki.sass.common.constant.Constants;
import com.loki.sass.common.dto.CurrentUserInfo;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.exception.UnknownLoginException;
import com.loki.sass.feignclient.feignService.FeignTokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class ExplorerInterceptor extends WebMvcConfigurerAdapter {

    /**
     * 配置拦截器
     * @author steven
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(encodeHandlerInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(userAuthHandlerInterceptor()).addPathPatterns("/**/authUApi/**");
//        registry.addInterceptor(sAuthHandlerInterceptor()).addPathPatterns("/**/authSApi/**");
//        registry.addInterceptor(pAuthHandlerInterceptor()).addPathPatterns("/**/authPApi/**");
//        registry.addInterceptor(rsAuthHandlerInterceptor()).addPathPatterns("/**/authRsApi/**");
//        registry.addInterceptor(ruAuthHandlerInterceptor()).addPathPatterns("/**/authRuApi/**");
    }

    @Bean
    public EncodeHandlerInterceptor encodeHandlerInterceptor() {
        return new EncodeHandlerInterceptor();
    }

    @Bean
    public UserAuthHandlerInterceptor userAuthHandlerInterceptor() {
        return new UserAuthHandlerInterceptor();
    }

//    @Bean
//    public SAuthHandlerInterceptor sAuthHandlerInterceptor() {
//        return new SAuthHandlerInterceptor();
//    }
//
//    @Bean
//    public PAuthHandlerInterceptor pAuthHandlerInterceptor() {
//        return new PAuthHandlerInterceptor();
//    }
//
//    @Bean
//    public RsAuthHandlerInterceptor rsAuthHandlerInterceptor() {
//        return new RsAuthHandlerInterceptor();
//    }
//
//    @Bean
//    public RuAuthHandlerInterceptor ruAuthHandlerInterceptor() {
//        return new RuAuthHandlerInterceptor();
//    }

}

@Slf4j
class UserAuthHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    FeignTokenService feignTokenService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        if (method.equals("OPTIONS")){
            return true;
        }
        String token = request.getHeader("token");

        if(StringUtils.isBlank(token)){
            onLoginFail(response,"无效凭证，请重新登录！");
            return false;
        }
        log.debug("auth is :"+token);

        if(!feignTokenService.verifyToken(token)){
            onLoginFail(response,"无效凭证，请重新登录！");
            return false;
        }
        try {
            CurrentUserInfo currentUserInfo = feignTokenService.authLogin(token,Constants.USER_APP_TOKEN);
            request.setAttribute(Constants.APP_USER,currentUserInfo);
        } catch (UnknownLoginException ule){
            onLoginFail(response,ule.getMessage());
            return false;
        }
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    private void onLoginFail(HttpServletResponse response, String msg) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ResultDTO<?> result = new ResultDTO<>();
        result.setSuccess(false);
        result.setResultCode("-99");
        result.setErrorMessage(msg);
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(result));
    }


    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}

//@Slf4j
//class SAuthHandlerInterceptor implements HandlerInterceptor {
//    @Autowired
//    AuthTokenService authTokenService;
//
//    @Autowired
//    SiteInfoMapper siteInfoMapper;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request,
//                             HttpServletResponse response, Object handler) throws Exception {
//        String method = request.getMethod();
//        if (method.equals("OPTIONS")){
//            return true;
//        }
//        String token = request.getHeader("sToken");
//        if(StringUtils.isBlank(token)){
//            onLoginFail(response,"无效凭证，请重新登录！");
//            return false;
//        }
//        log.debug("s auth is :"+token);
//
//        if(!authTokenService.verifyToken(token)){
//            onLoginFail(response,"无效凭证，请重新登录！");
//            return false;
//        }
//        try {
//            CurrentUserInfo currentFUserInfo = authTokenService.authLogin(token,Constants.S_APP_TOKEN);
//            SiteInfo siteInfo = siteInfoMapper.selectByMobile(currentFUserInfo.getMobile());
//            if(siteInfo != null){
//                if(siteInfo.getState() == RegistConst.SiteRegistState.WAIT_CHECK.getIndex())
//                    throw new BizException("8003","该站点待审核");
//                else if(siteInfo.getState() == RegistConst.SiteRegistState.DISABLE.getIndex())
//                    throw new BizException("8004","该站点已停用");
//            }
//            request.setAttribute(Constants.APP_USER,currentFUserInfo);
//        } catch (UnknownLoginException ule){
//            onLoginFail(response,ule.getMessage());
//            return false;
//        }
//        return true;
//
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request,
//                           HttpServletResponse response, Object handler,
//                           ModelAndView modelAndView) throws Exception {
//    }
//
//    private void onLoginFail(HttpServletResponse response, String msg) throws IOException {
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        ResultDTO result = new ResultDTO<>();
//        result.setSuccess(false);
//        result.setResultCode("-99");
//        result.setErrorMessage(msg);
//        ObjectMapper convertor = new ObjectMapper();
//        response.getWriter().write(convertor.writeValueAsString(result));
//    }
//
//
//    @Override
//    public void afterCompletion(HttpServletRequest request,
//                                HttpServletResponse response, Object handler, Exception ex)
//            throws Exception {
//
//    }
//
//}
//
//@Slf4j
//class PAuthHandlerInterceptor implements HandlerInterceptor {
//    @Autowired
//    AuthTokenService authTokenService;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request,
//                             HttpServletResponse response, Object handler) throws Exception {
//        String method = request.getMethod();
//        if (method.equals("OPTIONS")){
//            return true;
//        }
//        String token = request.getHeader("pToken");
//        if(StringUtils.isBlank(token)){
//            onLoginFail(response,"无效凭证，请重新登录！");
//            return false;
//        }
//        log.debug("p auth is :"+token);
//
//        if(!authTokenService.verifyToken(token)){
//            onLoginFail(response,"无效凭证，请重新登录！");
//            return false;
//        }
//        try {
//            CurrentUserInfo currentFUserInfo = authTokenService.authLogin(token,Constants.P_APP_TOKEN);
//            request.setAttribute(Constants.APP_USER,currentFUserInfo);
//        } catch (UnknownLoginException ule){
//            onLoginFail(response,ule.getMessage());
//            return false;
//        }
//        return true;
//
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request,
//                           HttpServletResponse response, Object handler,
//                           ModelAndView modelAndView) throws Exception {
//    }
//
//    private void onLoginFail(HttpServletResponse response, String msg) throws IOException {
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        ResultDTO result = new ResultDTO<>();
//        result.setSuccess(false);
//        result.setResultCode("-99");
//        result.setErrorMessage(msg);
//        ObjectMapper convertor = new ObjectMapper();
//        response.getWriter().write(convertor.writeValueAsString(result));
//    }
//
//
//    @Override
//    public void afterCompletion(HttpServletRequest request,
//                                HttpServletResponse response, Object handler, Exception ex)
//            throws Exception {
//
//    }
//
//}
//
//@Slf4j
//class RsAuthHandlerInterceptor implements HandlerInterceptor {
//    @Autowired
//    AuthTokenService authTokenService;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request,
//                             HttpServletResponse response, Object handler) throws Exception {
//        String method = request.getMethod();
//        if (method.equals("OPTIONS")){
//            return true;
//        }
//        String token = request.getHeader("rsToken");
//        if(StringUtils.isBlank(token)){
//            onLoginFail(response,"无效凭证，请重新登录！");
//            return false;
//        }
//        log.debug("rs auth is :"+token);
//
//        if(!authTokenService.verifyToken(token)){
//            onLoginFail(response,"无效凭证，请重新登录！");
//            return false;
//        }
//        try {
//            CurrentUserInfo currentFUserInfo = authTokenService.authLogin(token,Constants.RS_APP_TOKEN);
//            request.setAttribute(Constants.APP_USER,currentFUserInfo);
//        } catch (UnknownLoginException ule){
//            onLoginFail(response,ule.getMessage());
//            return false;
//        }
//        return true;
//
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request,
//                           HttpServletResponse response, Object handler,
//                           ModelAndView modelAndView) throws Exception {
//    }
//
//    private void onLoginFail(HttpServletResponse response, String msg) throws IOException {
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        ResultDTO result = new ResultDTO<>();
//        result.setSuccess(false);
//        result.setResultCode("-99");
//        result.setErrorMessage(msg);
//        ObjectMapper convertor = new ObjectMapper();
//        response.getWriter().write(convertor.writeValueAsString(result));
//    }
//
//
//    @Override
//    public void afterCompletion(HttpServletRequest request,
//                                HttpServletResponse response, Object handler, Exception ex)
//            throws Exception {
//
//    }
//
//}
//
//@Slf4j
//class RuAuthHandlerInterceptor implements HandlerInterceptor {
//    @Autowired
//    AuthTokenService authTokenService;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request,
//                             HttpServletResponse response, Object handler) throws Exception {
//        String method = request.getMethod();
//        if (method.equals("OPTIONS")){
//            return true;
//        }
//        String token = request.getHeader("ruToken");
//        if(StringUtils.isBlank(token)){
//            onLoginFail(response,"无效凭证，请重新登录！");
//            return false;
//        }
//        log.debug("ru auth is :"+token);
//
//        if(!authTokenService.verifyToken(token)){
//            onLoginFail(response,"无效凭证，请重新登录！");
//            return false;
//        }
//        try {
//            CurrentUserInfo currentFUserInfo = authTokenService.authLogin(token,Constants.RU_APP_TOKEN);
//            request.setAttribute(Constants.APP_USER,currentFUserInfo);
//        } catch (UnknownLoginException ule){
//            onLoginFail(response,ule.getMessage());
//            return false;
//        }
//        return true;
//
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request,
//                           HttpServletResponse response, Object handler,
//                           ModelAndView modelAndView) throws Exception {
//    }
//
//    private void onLoginFail(HttpServletResponse response, String msg) throws IOException {
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        ResultDTO result = new ResultDTO<>();
//        result.setSuccess(false);
//        result.setResultCode("-99");
//        result.setErrorMessage(msg);
//        ObjectMapper convertor = new ObjectMapper();
//        response.getWriter().write(convertor.writeValueAsString(result));
//    }
//
//
//    @Override
//    public void afterCompletion(HttpServletRequest request,
//                                HttpServletResponse response, Object handler, Exception ex)
//            throws Exception {
//
//    }
//
//}


@Slf4j
class EncodeHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}
