package cn.jujiangzhai.entity;

public class TokenInfo {

	/**
	 * 状态码
	 * 1:正常,登陆成功,返回token;
	 * 0:错误,登陆失败.
	 */
	private int status;
	
	private String token;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public TokenInfo() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public TokenInfo(int status, String token) {
		super();
		this.status = status;
		this.token = token;
	}
	
	
	
}
