package com.airwxtx.user.action.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.airwxtx.authority.entity.Authority;
import com.airwxtx.authority.service.AuthorityService;
import com.airwxtx.user.action.UserAction;
import com.airwxtx.user.entity.Role;
import com.airwxtx.user.entity.User;
import com.airwxtx.user.service.UserService;
import com.airwxtx.utils.Constants;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Controller("userAction")
@Scope("prototype")
public class UserActionImpl extends ActionSupport implements UserAction {

	private User user;

	private String username;

	private String role;

	private int page;

	// Ȩ��
	private Set<Long> authorityNumbers;

	private List<User> users;

	private boolean create;

	private Map<String, Object> jsonResult = new HashMap<>();

	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityService authorityService;

	@Override
	public String preCreateUser() throws Exception {
		// TODO Auto-generated method stub
		create = true;
		return INPUT;
	}

	public void validateCreateUser() throws Exception {
		if (userService.hasUsername(user.getUsername())) {
			create = true;
			this.addFieldError("user.username", "�û����ѱ�ʹ��");
		}
	}

	@Override
	public String createUser() throws Exception {
		// TODO Auto-generated method stub
		userService.createUser(user, authorityNumbers);
		return PROFILE;
	}

	@Override
	public String preEditUser() throws Exception {
		// TODO Auto-generated method stub
		create = false;
		user = userService.findUserByName(user.getUsername());
		authorityNumbers = authorityService.resolveAuthority(user.getAuthority());
		return EDIT;
	}

	@Override
	public String editUser() throws Exception {
		// TODO Auto-generated method stub
		userService.editUser(user, authorityNumbers);
		return PROFILE;
	}

	@Override
	public String resetPassword() throws Exception {
		userService.resetPasswordByName(user.getUsername());
		jsonResult.put("resultInfo", "�������óɹ�");
		return SUCCESS;
	}

	@Override
	public String searchUser() throws Exception {
		// TODO Auto-generated method stub
		users = userService.findUserByNameOrRoleWithPage(username, role, page, Constants.PAGE_SIZE);
		return LIST;
	}

	public int getMaxPage() {
		int count = userService.countUserWithNameOrRole(username, role);
		return (count - 1) / Constants.PAGE_SIZE + 1;
	}

	public List<String> getAllRoles() throws IllegalArgumentException, IllegalAccessException {
		List<String> roles = new ArrayList<String>();
		Field[] fields = Role.class.getFields();
		for (Field field : fields) {
			roles.add((String) field.get(null));
		}
		return roles;
	}

	public List<Authority> getAllAuthorities() {
		return authorityService.loadAllAuthorities();
	}

	public List<String> getDisplayAuthorities() {
		return authorityService.changeToDisplayAuthorities(authorityNumbers);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Set<Long> getAuthorityNumbers() {
		return authorityNumbers;
	}

	public void setAuthorityNumbers(Set<Long> authorities) {
		this.authorityNumbers = authorities;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public boolean isCreate() {
		return create;
	}

	public void setCreate(boolean create) {
		this.create = create;
	}

	public Map<String, Object> getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(Map<String, Object> jsonResult) {
		this.jsonResult = jsonResult;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public AuthorityService getAuthorityService() {
		return authorityService;
	}

	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	private static final String LIST = "list";
	private static final String EDIT = "edit";
	private static final String PROFILE = "profile";

}