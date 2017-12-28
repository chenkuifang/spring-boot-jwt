package com.example.demo.util;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @Description: Jwt工具类token包括：1. header (base64后的,一般做声明作用)2.payload (base64后的)
 *               3.secret(第三部分是签证，包含私钥，必须保存在服务器)
 * @author QuiFar
 * @date 2017年9月26日 下午6:05:10
 * @version V1.0
 */
public class JwtUtil {

	/**
	 * 私钥
	 */
	public final static String SESCRET_STRING = "quifar";
	/**
	 * 过期时间(毫秒)60秒
	 */
	public final static long EXP_TIME = 60*1000;

	/**
	 * 解密JWT
	 * 
	 * @param jsonWebToken
	 *            接收客户端的token
	 * @param base64Security
	 *            私钥
	 * @return 载荷,如果解密不成功返回null
	 */
	public static Claims parseJWT(String jsonWebToken, String base64Security) {
		try {
			// 私钥base64加密
			byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
			Claims claims = Jwts.parser().setSigningKey(apiKeySecretBytes).parseClaimsJws(jsonWebToken).getBody();
			return claims;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 创建JWT
	 * 
	 * @param webContent
	 *            载荷(包括一些不敏感的信息)
	 * @param audience
	 *            接收jwt者
	 * @param issuer
	 *            签发者
	 * @param ttlMillis
	 *            过期时间
	 * @param base64Security
	 *            私钥
	 * @return
	 */
	public static String createJWT(Map<String, String> webContent, String audience, String issuer, long ttlMillis,
			String base64Security) {
		// 1.声明加密的算法 通常直接使用 HA256
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		// 2.生成签名私钥(对私钥进行base64加密,那么解密同样需要相同的加密方式)
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
		Key secret = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		// 添加构成JWT的参数
		JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")// header1.声明类型，2.声明加密的算法
				.setHeaderParam("alg", "HS256").claim("webContent", webContent)// payload 载荷
				.setIssuer(issuer) // 签发者
				.setAudience(audience)// 接收jwt的一方
				.signWith(signatureAlgorithm, secret);

		// 添加Token过期时间
		if (ttlMillis >= 0) {
			long nowMillis = System.currentTimeMillis();
			Date now = new Date(nowMillis);
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp).setNotBefore(now);
		}

		// 生成JWT
		return builder.compact();
	}

	public static void main(String[] args) {
//		Map<String, String> webContent = new HashMap<>();
//		webContent.put("name", "kinyang");
//		webContent.put("roleId", "10001");
//		webContent.put("userId", "10058");
//		String token = JwtUtil.createJWT(webContent, "10058", "server", JwtUtil.EXP_TIME, JwtUtil.SESCRET_STRING);
//		System.err.println(token);
		String aaString="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ3ZWJDb250ZW50Ijp7InJvbGVJZCI6IjEwMDAxIiwibmFtZSI6ImtpbnlhbmciLCJ1c2VySWQiOiIxMDA1OCJ9LCJpc3MiOiJzZXJ2ZXIiLCJhdWQiOiIxMDA1OCIsImV4cCI6MTUxNDQ3MTI2NCwibmJmIjoxNTE0NDcxMjA0fQ.rwUY2OM94QQE34aJ_osLV8fKTf81LjO1Cd7oY7pkqdo";

		
		// 解密
		Claims claims = JwtUtil.parseJWT(aaString, "quifar");

		Map<String, String> data = (Map<String, String>) claims.get("webContent");
		System.err.println("name:" + data.get("name"));
	}

}
