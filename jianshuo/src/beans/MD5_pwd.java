package beans;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


import sun.misc.BASE64Encoder;

public class MD5_pwd {
	public String MD5(String oldStr){
		byte[] oldBytes = oldStr.getBytes();
		MessageDigest md;
		try{
			md = MessageDigest.getInstance("MD5");
			byte[] newBytes = md.digest(oldBytes);
			BASE64Encoder encoder = new BASE64Encoder();
			String newStr = encoder.encode(newBytes);
			return newStr;
		}catch(NoSuchAlgorithmException e){
			return null;
		}
		
	}
}
