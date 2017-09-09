package pe.com.cotic.test.util;

import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Base64;

public class Seguridad {
	
	private static final byte[] SALT_BYTES = { -87, -101, -56, 50, 86, 53, -29, 3 };
	
	public static String fn_sEncrypting(String sKeyPhrase, String sStringValue)
			throws NoSuchAlgorithmException {
		Cipher oECipher = null;
		Cipher oDCipher = null;
		try {
			KeySpec oKeySpec = new PBEKeySpec(sKeyPhrase.toCharArray(), SALT_BYTES, 19);
			SecretKey oKey = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(oKeySpec);
			oECipher = Cipher.getInstance(oKey.getAlgorithm());
			oDCipher = Cipher.getInstance(oKey.getAlgorithm());
			AlgorithmParameterSpec oaramSpec = new PBEParameterSpec(SALT_BYTES, 19);
			oECipher.init(1, oKey, oaramSpec);
			oDCipher.init(2, oKey, oaramSpec);
			byte[] oUTF8 = sStringValue.getBytes("UTF8");
			byte[] oEnc = oECipher.doFinal(oUTF8);
			//return new BASE64Encoder().encode(oEnc);
			return Base64.encodeBase64String(oEnc);
			
		} catch (Exception oE1) {
			oE1.printStackTrace();
		}
		return null;
	}
	
}
