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
                .setAudience(name)                                // ������
                .setClaims(null)                                // �Զ�������
                .setSubject("")                                 // ����
                .setIssuer("")                                  // ǩ����
                .setIssuedAt(now)                        // ǩ��ʱ��
//                .setNotBefore(new Date())                       // ʧЧʱ��
//                .setExpiration(long)                                // ����ʱ��
                .signWith(signatureAlgorithm, "mytoken");           // ǩ���㷨�Լ��ܳ�
        return builder.compact();
	}
}
