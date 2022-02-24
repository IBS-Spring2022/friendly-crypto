import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;


public class EcryptionAssignment {
	private static String MypublicKey;
	private static String MyprivateKey;
	private static String name="Shaheryar Raza";
	public static void main(String[]args) throws Exception {
	generatingkeys();
    System.out.println("Encrypted Text of My Name is: "+bytesToString((RSAEncryption(MypublicKey,name))));
    System.out.println();
	System.out.println("Decrypted Text of My Name: "+RSADecryption(RSAEncryption(MypublicKey,name),MyprivateKey));
	System.out.println();
	digitalSignature(MyprivateKey,name);	
	bitcoinAddress();
	 }
	
	
	public static byte[] RSAEncryption(String publicKey,String message) throws Exception {

        KeyFactory kf = KeyFactory.getInstance("RSA");
    	X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode((publicKey).getBytes()));
        RSAPublicKey pubKey = (RSAPublicKey) kf.generatePublic(keySpecX509);

		 Cipher cipher= Cipher.getInstance("RSA");
    		 
	 cipher.init(Cipher.ENCRYPT_MODE,pubKey);
		 
	 
	 byte[]digest=cipher.doFinal(message.getBytes());
	 
	 return digest;
	}
	
	
	public static String bytesToString(byte[]text)
	{
		StringBuffer hexString = new StringBuffer();
		 for (int i=0;i<text.length;i++) {
		 hexString.append(Integer.toHexString(0xFF & text[i]));
		 }
		 return hexString.toString();
	}
	
	
	public static String RSADecryption(byte[]cipherText,String privateKey)throws Exception
		{
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode((privateKey).getBytes()));
     
		PrivateKey privKey =  kf.generatePrivate(keySpec);
		
		Cipher cipher= Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE,privKey);
		
	    byte[] result= cipher.doFinal(cipherText);

			return new String(result);
		}
	public static void digitalSignature (String privateKey,String name) throws Exception {
		
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode((privateKey).getBytes()));
        PrivateKey privKey =  kf.generatePrivate(keySpec);
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(privKey);
        
        byte[] bytes = name.getBytes();
        sign.update(bytes);
        byte[] signature = sign.sign();
        System.out.println("Digital signature for given text: "+bytesToString(signature)+"\n");
	}
	
	
    public static void EncryptingAlgorithms(String message)  throws Exception{
		
		// Using SHA-256
		//MessageDigest md = MessageDigest.getInstance("SHA-256");
		//Using SHA-512
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(message.getBytes());
		byte[] digest = md.digest();
		StringBuffer hexString = new StringBuffer();
		for (int i=0;i<digest.length;i++) {
			 hexString.append(Integer.toHexString(0xFF & digest[i]));
			 }
			 System.out.println("Hex format : " + hexString.toString());
			 
	}
	public static void generatingkeys()  throws Exception{
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		keyPairGen.initialize(2048);
		KeyPair pair = keyPairGen.generateKeyPair();
		
		MyprivateKey= new String(Base64.getEncoder().encode(pair.getPrivate().getEncoded()));
		System.out.println("Private Key: "+MyprivateKey);
		System.out.println();
		MypublicKey= new String(Base64.getEncoder().encode(pair.getPublic().getEncoded()));
		System.out.println("Public Key: "+MypublicKey);
		System.out.println();
	}
		
	public static void bitcoinAddress() throws Exception
	{
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		keyPairGen.initialize(2048);
		KeyPair pair = keyPairGen.generateKeyPair();
		String hexPrivateKey= new String(Base64.getEncoder().encode(pair.getPrivate().getEncoded()));
		String hexPublicKey= new String(Base64.getEncoder().encode(pair.getPublic().getEncoded()));
		MessageDigest sha = MessageDigest.getInstance("SHA-256");
		byte[] s1 = sha.digest(hexPublicKey.getBytes("UTF-8"));
		StringBuffer hexString = new StringBuffer();
		for (int i=0;i<s1.length;i++) {
			 hexString.append(Integer.toHexString(0xFF & s1[i]));
			 }
		System.out.println("sha after first hashing:" + hexString.toString().toUpperCase()+"\n");
		
		// Hash through RIPEMD160 DONE IN PYTHON
		String ripemd="97338a827ecc2527663eff811ffeaf7e83fc625a".toUpperCase();
		byte[]ripemdbytes=ripemd.getBytes();
		
		//double Hashing
		byte[] s2 = sha.digest(ripemd.getBytes());
		for (int i=0;i<s2.length;i++) {
			 hexString.append(Integer.toHexString(0xFF & s2[i]));
			 }
		System.out.println("sha after second hashing:" + hexString.toString().toUpperCase()+"\n");
		
		byte[] s3 = sha.digest(s2);
		for (int i=0;i<s3.length;i++) {
			 hexString.append(Integer.toHexString(0xFF & s3[i]));
			 }
		System.out.println("sha after third hashing:" + hexString.toString().toUpperCase()+"\n");
		byte[] a1 = new byte[ripemdbytes.length];
		for (int i = 0 ; i < ripemdbytes.length ; i++) a1[i] = ripemdbytes[i];
		for (int i = 0 ; i < 5 ; i++) a1[20 + i] = s3[i];
		
		System.out.println("Bitcoin wallet Address: " + Base58encode(a1));
	}
	
	

	 public static String Base58encode(byte[] input) {
		 char[] ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".toCharArray();
		 char ENCODED_ZERO = ALPHABET[0];
		 int[] INDEXES = new int[128];
	        if (input.length == 0) {
	            return "";
	        }       
	        // Count leading zeros.
	        int zeros = 0;
	        while (zeros < input.length && input[zeros] == 0) {
	            ++zeros;
	        }
	        // Convert base-256 digits to base-58 digits (plus conversion to ASCII characters)
	        input = Arrays.copyOf(input, input.length); // since we modify it in-place
	        char[] encoded = new char[input.length * 2]; // upper bound
	        int outputStart = encoded.length;
	        for (int inputStart = zeros; inputStart < input.length; ) {
	            encoded[--outputStart] = ALPHABET[divmod(input, inputStart, 256, 58)];
	            if (input[inputStart] == 0) {
	                ++inputStart; // optimization - skip leading zeros
	            }
	        }
	        // Preserve exactly as many leading encoded zeros in output as there were leading zeros in input.
	        while (outputStart < encoded.length && encoded[outputStart] == ENCODED_ZERO) {
	            ++outputStart;
	        }
	        while (--zeros >= 0) {
	            encoded[--outputStart] = ENCODED_ZERO;
	        }
	        // Return encoded string (including encoded leading zeros).
	        return new String(encoded, outputStart, encoded.length - outputStart);
	    }


	private static int divmod(byte[] number, int firstDigit, int base, int divisor) {
		int remainder = 0;
        for (int i = firstDigit; i < number.length; i++) {
            int digit = (int) number[i] & 0xFF;
            int temp = remainder * base + digit;
            number[i] = (byte) (temp / divisor);
            remainder = temp % divisor;
        }
        return (byte) remainder;
		
	}


	private static String adjustTo64(String s) {
		switch(s.length()) {
	    case 62: return "00" + s;
	    case 63: return "0" + s;
	    case 64: return s;
	    default:
	        throw new IllegalArgumentException("not a valid key: " + s);
	    }
		
	}
}
