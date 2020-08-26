package com.sfac.java_spring_boot.models.account.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.sfac.java_spring_boot.models.account.entity.Role;
import com.sfac.java_spring_boot.models.common.vo.Result;
import com.sfac.java_spring_boot.models.common.vo.SearchVo;


public interface RoleService {

	List<Role> getRoles();
	
	Result<Role> editRole(Role role);
	
	Result<Role> deleteRole(int roleId);
	
	PageInfo<Role> getRoles(SearchVo searchVo);
	
	List<Role> getRolesByUserId(int userId);
	
	List<Role> getRolesByResourceId(int resourceId);
	
	Role getRoleById(int roleId);
}
