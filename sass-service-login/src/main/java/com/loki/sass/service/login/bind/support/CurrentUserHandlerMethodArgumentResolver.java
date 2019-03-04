package com.loki.sass.service.login.bind.support;

import com.loki.sass.common.constant.Constants;
import com.loki.sass.common.dto.CurrentUserInfo;
import com.loki.sass.service.login.bind.annotation.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by lokizero00
 * date 2018-03-20
 */
@Slf4j
public class CurrentUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        if(webRequest.getNativeRequest() instanceof HttpServletRequest){
            final HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
            try {
                CurrentUserInfo currentUserInfo= (CurrentUserInfo) request.getAttribute(Constants.APP_USER);
                if(currentUserInfo!=null){
                    return currentUserInfo;
                }
            } catch (Exception ule){
                log.error("user is null");
            }
        }
        return  WebArgumentResolver.UNRESOLVED;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterAnnotation(CurrentUser.class) != null;
    }
}
