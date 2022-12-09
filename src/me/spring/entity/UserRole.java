package me.spring.entity;

public class UserRole extends IdEntity {
    private static final long serialVersionUID = 1L;
    
    private String username;
    private String nickname;
    private String role;
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public UserRole() {
		super();
	}
	public UserRole(String username) {
		super();
		this.username = username;
	}
	public UserRole(String username, String role) {
		super();
		this.username = username;
		this.role = role;
	}
	public UserRole(String username, String nickname, String role) {
		super();
		this.username = username;
		this.nickname = nickname;
		this.role = role;
	}
	@Override
	public String toString() {
		return "UserRole [username=" + username + ", nickname=" + nickname + ", role=" + role + "]";
	}
}
