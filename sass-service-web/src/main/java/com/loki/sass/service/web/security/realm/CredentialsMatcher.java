package com.loki.sass.service.web.security.realm;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * created by lokizero00 on 2019-02-21
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken utoken = (UsernamePasswordToken) token;
        //获得用户输入的密码
        String inPassword = new String(utoken.getPassword());
        //获得数据库中的密码
        String dbPassword = (String) info.getCredentials();
        //进行密码的比对
        boolean checkRes = BCrypt.checkpw(inPassword, dbPassword);
//        if(info instanceof SaltedAuthenticationInfo){
//            SaltedAuthenticationInfo saltedAuthenticationInfo = (SaltedAuthenticationInfo) info;
//            String salt = saltedAuthenticationInfo.getCredentialsSalt().toString();
//            String hash = BCrypt.hashpw(inPassword,saltedAuthenticationInfo.getCredentialsSalt().toString());
//            checkRes = BCrypt.checkpw(dbPassword,hash);
//        }
        if(checkRes) {
            UsernamePasswordToken userToken = (UsernamePasswordToken) token;
            String mobile = userToken.getUsername();
            //删除用户redis中权限缓存
            stringRedisTemplate.delete("" + mobile);
        }
        return checkRes;
    }
}
