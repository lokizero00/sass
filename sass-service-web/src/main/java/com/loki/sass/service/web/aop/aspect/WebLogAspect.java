package com.loki.sass.service.web.aop.aspect;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.loki.sass.domain.model.Admin;
import com.loki.sass.domain.model.SysOperatLog;
import com.loki.sass.service.web.aop.bind.Function;
import com.loki.sass.service.web.aop.bind.Operate;
import com.loki.sass.service.web.api.SysOperatLogService;
import com.loki.sass.service.web.security.realm.ShiroAdmin;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * 操作日志拦截记录
 * @author lokizero00
 *
 */
@Slf4j
@Aspect
@Component
public class WebLogAspect {

	@Autowired
	private SysOperatLogService sysOperatLogService;

	@Pointcut("execution(public * com.loki.sass.service.web.controller..*.*(..)) && @annotation(com.loki.sass.service.web.aop.bind.Operate)")
	public void webLog(){}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		try {
			// 接收到请求，记录请求内容
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = attributes.getRequest();
			MethodSignature joinPointObject = (MethodSignature) joinPoint.getSignature();
			Class<? extends Object> classD = joinPoint.getTarget().getClass();
			Method method = joinPointObject.getMethod();
			String className = classD.getName();
			String methodName = method.getName();

			SysOperatLog sysOperatLog = new SysOperatLog();
			sysOperatLog.setIpAddr(request.getRemoteAddr());
			sysOperatLog.setActionUrl(request.getRequestURI());
			ShiroAdmin shiroAdmin = (ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
			if (shiroAdmin != null) {
				sysOperatLog.setUserId(shiroAdmin.getId());
				sysOperatLog.setUserName(shiroAdmin.getRealName());
				sysOperatLog.setUserMobile(shiroAdmin.getMobile());
			}

			Function fd = null;
			Operate md = null;
			if (classD.isAnnotationPresent(Function.class)) {
				fd = classD.getAnnotation(Function.class);
			}
			if (method.isAnnotationPresent(Operate.class)) {
				md = method.getAnnotation(Operate.class);
			}
			if (fd != null) {
				sysOperatLog.setMean(fd.value());
				sysOperatLog.setModule(fd.moduleName());
				sysOperatLog.setSubModule(fd.subModuleName());
			}
			if (md != null) {
				sysOperatLog.setFunction(md.value());
			}

			String params = parseParames(joinPoint.getArgs());

			if (params == null || "".equals(params)) {
				Map<String, String[]> mapP = request.getParameterMap();
				params = "{";
				for (String key : mapP.keySet()) {
					String[] values = mapP.get(key);
					for (int i = 0; i < values.length; i++) {
						String value = values[i];
						params += key + ":" + value + ",";
					}
				}
				params += "}";
			}
			if (!md.isLogParams()) {
				params = "";
			}
			if (params.length() > 4000) {
				params = params.substring(0, 4000);
			}
			sysOperatLog.setParamData(params);
			sysOperatLog.setCreateTime(new Date());

			//如果是登录操作，则需特殊处理
			if ("/web/login/adminLogin".equals(sysOperatLog.getActionUrl()) && joinPoint.getArgs().length > 0) {
				Admin admin = (Admin) joinPoint.getArgs()[0];
				if (admin != null && admin.getMobile() != null) {
					sysOperatLog.setUserMobile(admin.getMobile());
					sysOperatLog.setParamData("");
				}
			}
			sysOperatLogService.insertSysOperatLog(sysOperatLog);
			log.debug("业务处理" + ",模块：[" + (fd != null ? fd.moduleName() : "") + "],操作：[" + (md != null ? md.value() : "") + "]，调用类名称：[" + className + "]，调用方法名称：[" + methodName + "]，参数为：" + params);
		}catch (Exception e){
			log.error("记录系统操作日志失败");
			e.printStackTrace();
		}
	}

	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Throwable {
		log.debug("RESPONSE : " + ret);
	}

	/**
	 * 解析方法参数
	 * @param parames 方法参数
	 * @return 解析后的方法参数
	 */
	private static final  String NO_JSON_TYPE="CommonsMultipartFile";
	private String parseParames(Object[] parames) {
		StringBuffer sb = new StringBuffer();
		try {
			for (int i = 0; i < parames.length; i++) {
				if(NO_JSON_TYPE.indexOf(parames[i].getClass().getSimpleName())!=-1){
					MultipartFile file =  (MultipartFile)parames[i];
					sb.append("上传文件");
					continue;
				}
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(parames[i]);

				if (i == parames.length - 1) {
					sb.append(json.toString());
				} else {
					sb.append(json.toString() + ",");
				}

			}
			String params = sb.toString();
			params = params.replaceAll("(\"\\w+\":\"\",)", "");
			params = params.replaceAll("(,\"\\w+\":\"\")", "");
			return params;
		} catch (Exception e) {
			return "";
		}
	}

}
