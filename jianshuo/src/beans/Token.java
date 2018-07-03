package beans;

import java.util.Date;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Token {
	public static String createJWT(String name) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//        SecretKey secretKey = generalKey();
        Date now = new Date(System.currentTimeMillis());
        JwtBuilder builder = Jwts.builder()
//                .setId(id)                                      // JWT_ID
                .setAudience(name)                                // 接受者
                .setClaims(null)                                // 自定义属性
                .setSubject("")                                 // 主题
                .setIssuer("")                                  // 签发者
                .setIssuedAt(now)                        // 签发时间
//                .setNotBefore(new Date())                       // 失效时间
//                .setExpiration(long)                                // 过期时间
                .signWith(signatureAlgorithm, "mytoken");           // 签名算法以及密匙
        return builder.compact();
	}
}
