package com.loki.sass.domain.mapper;

import com.loki.sass.domain.model.User;
import com.loki.sass.domain.model.UserExample;
import com.loki.sass.domain.po.UserDetailPO;
import com.loki.sass.domain.po.UserDoorPO;
import com.loki.sass.domain.po.UserRegionPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByMobile(@Param("mobile") String mobile);

    List<UserDetailPO> findUserDetailByParam(@Param("userMobile")String userMobile,
                                             @Param("userNickName")String userNickName,
                                             @Param("userRealName") String userRealName,
                                             @Param("state") Integer state,
                                             @Param("zoneId")Integer zoneId,
                                             @Param("propertyId")Integer propertyId);

    List<UserDoorPO> findUserDoorByParam(@Param("doorName")String doorName,
                                         @Param("userMobile")String userMobile,
                                         @Param("userName")String userName,
                                         @Param("isPermanent")Boolean isPermanent,
                                         @Param("zoneId")Integer zoneId,
                                         @Param("propertyId")Integer propertyId);

    List<UserRegionPO> findUserRegionByParam(@Param("regionName")String regionName,
                                             @Param("userMobile")String userMobile,
                                             @Param("userName")String userName,
                                             @Param("zoneId")Integer zoneId,
                                             @Param("propertyId")Integer propertyId);

}