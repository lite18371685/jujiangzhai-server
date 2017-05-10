package cn.jujiangzhai.util;

import java.util.UUID;


public class TokenProcessor {

	
	public static String makeToken() {
		

		
		String token = UUID.randomUUID().toString()+"-"+System.currentTimeMillis();
		
		return 	token;
	}
	public static void main(String[] args) {
		System.out.println(makeToken());
	}
	
}
