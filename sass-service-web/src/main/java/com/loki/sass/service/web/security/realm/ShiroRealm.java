package com.loki.sass.service.web.security.realm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loki.sass.common.constant.Constants;
import com.loki.sass.common.dto.AdminDTO;
import com.loki.sass.common.dto.PermissionDTO;
import com.loki.sass.common.dto.RoleDTO;
import com.loki.sass.domain.model.Admin;
import com.loki.sass.domain.model.Permission;
import com.loki.sass.domain.model.Role;
import com.loki.sass.feignclient.feignService.FeignAdminService;
import com.loki.sass.feignclient.feignService.FeignPermissionService;
import com.loki.sass.feignclient.feignService.FeignRoleService;
import com.loki.sass.service.web.dto.AdminPermsDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * created by lokizero00 on 2019-02-21
 */
@Slf4j
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    FeignAdminService feignAdminService;

    @Autowired
    FeignRoleService feignRoleService;

    @Autowired
    FeignPermissionService feignPermissionService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * Shiro登录认证(原理：用户提交 用户名和密码  --- shiro 封装令牌 ---- realm 通过用户名将密码查询返回 ---- shiro 自动去比较查询出密码和用户输入密码是否一致---- 进行登陆控制 )
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.debug("Shiro开始数据库登录认证");
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        String mobile = userToken.getUsername();
        AdminDTO adminDTO = feignAdminService.selectByMobile(mobile);
        if (adminDTO==null){
            log.info("Admin："+mobile+"登录失败，用户不存在");
            return null;
        }
        if (!adminDTO.getState().equals(1)){
            log.info("Admin："+mobile+"登录失败，用户账号已停用");
            return null;
        }

        ShiroAdmin shiroAdmin = new ShiroAdmin();
        BeanUtils.copyProperties(adminDTO,shiroAdmin);

        //清除缓存
        stringRedisTemplate.delete(Constants.ADMIN_PERMS + shiroAdmin.getMobile());

        shiroAdmin = initRolesAndPermissions(shiroAdmin);
        // 认证缓存信息
        return new SimpleAuthenticationInfo(shiroAdmin, adminDTO.getPassword(), getName());
    }

    /**
     * Shiro访问链接权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("Shiro访问链接权限认证");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        ShiroAdmin shiroAdmin = (ShiroAdmin) principals.getPrimaryPrincipal();
//        shiroAdmin = initRolesAndPermissions(shiroAdmin);
        Set<String> roleSet = shiroAdmin.getRoleSet();
        if(roleSet != null && roleSet.size() > 0){
            info.addRoles(shiroAdmin.getRoleSet());
        }
        Set<String> permissions = shiroAdmin.getPermissionSet();
        if(permissions != null && permissions.size() > 0){
            info.addStringPermissions(shiroAdmin.getPermissionSet());
        }
        return info;
    }

    private ShiroAdmin initRolesAndPermissions(ShiroAdmin shiroAdmin){
        ObjectMapper mapper = new ObjectMapper();
        AdminPermsDTO adminPermsDTO = new AdminPermsDTO();
        Set<String> roleSet = new HashSet<>();
        Set<String> permissions = new HashSet<>();

        try {
            //判断redis中是否有当前用户权限
            ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
            String json  = valueOperations.get(Constants.ADMIN_PERMS + shiroAdmin.getMobile());

            if(StringUtils.isNotBlank(json)) {
                adminPermsDTO = mapper.readValue(json,AdminPermsDTO.class);
                roleSet = adminPermsDTO.getRoleSet();
                permissions = adminPermsDTO.getPermissions();
            } else {
                //没有则从mysql中获取
                List<RoleDTO> roleDTOList = feignRoleService.selectByUserId(shiroAdmin.getId());
                for (RoleDTO roleDTO : roleDTOList) {
                    roleSet.add(roleDTO.getId().toString());
                    if(roleDTO != null){
                        List<PermissionDTO> _permissions = feignPermissionService.selectByRoleId(roleDTO.getId());
                        for(PermissionDTO permissionDTO : _permissions){
                            if(permissionDTO != null && StringUtils.isNotEmpty(permissionDTO.getUrl())){
                                permissions.add(permissionDTO.getPermission());
                            }
                        }
                    }
                }
                adminPermsDTO.setRoleSet(roleSet);
                adminPermsDTO.setPermissions(permissions);
                valueOperations.set(Constants.ADMIN_PERMS + shiroAdmin.getMobile(), mapper.writeValueAsString(adminPermsDTO));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        if(roleSet != null && roleSet.size() > 0){
            shiroAdmin.setRoleSet(roleSet);
        }
        if(permissions != null && permissions.size() > 0){
            shiroAdmin.setPermissionSet(permissions);
        }
        return shiroAdmin;
    }

    /***
     * 授权所用配置
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /***
     * 使授权注解起作用不如不想配置可以在pom文件中加入
     * <dependency>
     *<groupId>org.springframework.boot</groupId>
     *<artifactId>spring-boot-starter-aop</artifactId>
     *</dependency>
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * Shiro生命周期处理器
     * 此方法需要用static作为修饰词，否则无法通过@Value()注解的方式获取配置文件的值
     *
     */
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

}