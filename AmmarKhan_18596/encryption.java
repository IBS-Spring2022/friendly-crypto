import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;
import java.security.Signature;
import javax.crypto.BadPaddingException;


public class encryption {

	private static final String RSA = "RSA";
	
	public static KeyPair generateRSAKeyPair() 
		throws Exception {
		
		SecureRandom secureRandom = new SecureRandom();
		
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
		
		keyPairGenerator.initialize(2048, secureRandom);
		
		return keyPairGenerator.generateKeyPair();
		
	}
	
	public static String getHash(byte[] inputBytes, String algorithm) {
		String hashValue = " ";
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			digest.update(inputBytes);
			byte[] digestedBytes = digest.digest();
			hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();

		}
		catch (Exception e) {}
		return hashValue;
	}
	
//	byte [] encodedhash = digest.digest(
//			originalString.getBytes(StanderedCharsets.UTF_8));
	
	
	public static void encryptRSA() throws Exception {
		String name = "Ammar";
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(2048);
		KeyPair kp = kpg.generateKeyPair();
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, kp.getPublic());
		byte[] input = name.getBytes();
		cipher.update(input);
		byte[] cipherText = cipher.doFinal();
		
		cipher.init(Cipher.DECRYPT_MODE, kp.getPrivate());
		byte[] decryptedText = cipher.doFinal(cipherText);
		
		
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < cipherText.length; i++) {
			hexString.append(Integer.toHexString(0xff & cipherText[i]));
		}
		
		System.out.println("\nThe name is encrypted using RSA and coverted to Hex String:\n" + hexString.toString());
		//System.out.println(new String (decryptedText));
	}
	
	public static void createSignature() throws Exception {
		String name = "Ammar";
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(2048);
		KeyPair kp = kpg.generateKeyPair();
		PrivateKey pk = kp.getPrivate();
		Signature sign = Signature.getInstance("SHA256withRSA");
		sign.initSign(pk);
		byte[] bytes = name.getBytes();
		sign.update(bytes);
		byte[] signature = sign.sign();
		
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < signature.length; i++) {
			hexString.append(Integer.toHexString(0xff & signature[i]));
		}
		
		System.out.println("\nDigital signature for name:\n" + hexString.toString());
		
		sign.initVerify(kp.getPublic());
		sign.update(bytes);
		boolean v = sign.verify(signature);
		
		if(v) 
			System.out.println("Signature is correct and verified");
		else
			System.out.println("Signature incorrect and unverifiable");
	}
	
	
	
	public static void main(String[]args)
		throws Exception {
			KeyPair keyPair = generateRSAKeyPair();
			
			//System.out.println("Public key is: " + DatatypeConverter.printHexBinary(keyPair.getPublic().getEncoded()));
			System.out.println("Public key is:\n" + Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
			String name = "Ammar";
			System.out.println("\nThis is Hashed and Hex Encoded by SHA-256:\n" + getHash(name.getBytes(), "SHA-256"));
			System.out.println("\nThis is Hashed and Hex Encoded by SHA-512:\n" + getHash(name.getBytes(), "SHA-512"));
			encryptRSA();
			createSignature();
		}
		
	}