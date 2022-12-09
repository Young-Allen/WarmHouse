package me.spring.entity;

public class User extends IdEntity {
    private static final long serialVersionUID = 1L;
    private String username;
    private	String nickname;
    private String password;
    private String phone;
    private String email;
    
    
    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public User() {
		super();
	}
	
	public User(String username) {
		super();
		this.username = username;
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public User(String username,String nickname, String phone) {
		super();
		this.username = username;
		this.nickname = nickname;
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", nickname=" + nickname + ", password=" + password + ", phone=" + phone
				+ ", email=" + email + ", id=" + id + "]";
	}
	
}