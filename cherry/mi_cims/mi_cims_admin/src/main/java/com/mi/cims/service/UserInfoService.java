package com.mi.cims.service;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageInfo;
import com.mi.cims.bean.bo.userInfo.AddUserInfoBo;
import com.mi.cims.bean.bo.userInfo.GetUserInfoBo;
import com.mi.cims.bean.bo.userInfo.UpdateUserInfoBo;
import com.mi.cims.bean.vo.UserInfoVo;

public interface UserInfoService {
	
    /**   
     * @Title: getUserInfo
     * @Description: 分页查询用户信息
     * @author: 孙忠飞 
     * @date:   2020年12月10日 下午1:23:06
     * @param:  getUserInfoBo 查询条件
     * @param:  pageNum		       当前页码
     * @param:  request		       请求信息
     * @return: PageInfo 	       分页列表
     * @throws: Exception
     */
	public PageInfo<?> getUserInfo(GetUserInfoBo getUserInfoBo, int pageNum, HttpServletRequest request) throws Exception;
	
    /**   
     * @Title: addUserInfo
     * @Description: 新建用户信息
     * @author: 孙忠飞 
     * @date:   2020年12月10日 下午1:23:06
     * @param:  getUserInfoBo 新建信息
     * @param:  request		       请求信息
     * @throws: Exception
     */
	public void addUserInfo(AddUserInfoBo addUserInfoBo, HttpServletRequest request) throws Exception;
	
    /**   
     * @Title: selectUserInfo
     * @Description: 根据用户ID查询用户信息
     * @author: 孙忠飞 
     * @date:   2020年12月10日 下午1:23:06
     * @param:  userId 		用户ID
     * @return: UserInfoVo  用户详细信息
     * @throws: Exception
     */
	public UserInfoVo selectUserInfoByUserId(Integer userId) throws Exception;

    /**   
     * @Title: updateUserInfo
     * @Description: 更新用户信息
     * @author: 孙忠飞 
     * @date:   2020年12月10日 下午1:23:06
     * @param:  UpdateUserInfoBo 更新用户信息
     * @return: SuccessInfo 成功标识
     * @throws: Exception
     */
	public void updateUserInfo(UpdateUserInfoBo updateUserInfoBo, HttpServletRequest request) throws Exception;
	
    /**   
     * @Title: resetUserInfoPwd
     * @Description: 重置用户密码
     * @author: 孙忠飞 
     * @date:   2020年12月10日 下午1:23:06
     * @param:  userId 		用户ID
     * @return: SuccessInfo 成功标识
     * @throws: Exception
     */
	public void resetUserInfoPwd(Integer userId) throws Exception;
	
    /**   
     * @Title: deleteUserInfo
     * @Description: 根据用户ID删除用户信息
     * @author: 孙忠飞 
     * @date:   2020年12月10日 下午1:23:06
     * @param:  userId 		用户ID
     * @return: SuccessInfo 成功标识
     * @throws: Exception
     */
	public void deleteUserInfoByUserId(Integer userId) throws Exception;
	
}
