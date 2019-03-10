package com.loki.sass.service.resident.api;


import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.UserDetailDTO;
import com.loki.sass.common.dto.UserDoorDTO;
import com.loki.sass.common.dto.UserRegionDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.vo.UserDetailQueryVO;
import com.loki.sass.common.vo.UserDoorQueryVO;
import com.loki.sass.common.vo.UserRegionQueryVO;

public interface UserService {

    PageInfo<UserDetailDTO> findUserDetailByPage(UserDetailQueryVO userDetailQueryVO) throws BizException;

    PageInfo<UserDoorDTO> findUserDoorByPage(UserDoorQueryVO userDoorQueryVO) throws BizException;

    PageInfo<UserRegionDTO> findUserRegionByPage(UserRegionQueryVO userRegionQueryVO) throws BizException;

}
