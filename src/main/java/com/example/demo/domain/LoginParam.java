package com.example.demo.domain;

/**
 * @Description: 登陆获取Token时，需要认证的信息类
 * @author QuiFar
 * @date 2017年9月26日 下午6:02:34
 * @version V1.0
 */
public class LoginParam {
	private String clientId;
	private String userName;
	private String password;
	private String captchaCode;
	private String captchaValue;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCaptchaCode() {
		return captchaCode;
	}

	public void setCaptchaCode(String captchaCode) {
		this.captchaCode = captchaCode;
	}

	public String getCaptchaValue() {
		return captchaValue;
	}

	public void setCaptchaValue(String captchaValue) {
		this.captchaValue = captchaValue;
	}

	@Override
	public String toString() {
		return "LoginParam [clientId=" + clientId + ", userName=" + userName + ", password=" + password
				+ ", captchaCode=" + captchaCode + ", captchaValue=" + captchaValue + "]";
	}

}
