/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchainassignment5;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Huda
 */
public class BlockchainAssignment5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, SignatureException {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        KeyPair pair = keyPairGen.generateKeyPair();
        PrivateKey privKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();
        System.out.println("Public Key " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        System.out.println("Private Key " + Base64.getEncoder().encodeToString(privKey.getEncoded()));
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update("Huda".getBytes());
        byte[] digest = md.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < digest.length; i++) {
            hexString.append(Integer.toHexString(0xFF & digest[i]));
        }
        System.out.println("SHA 256 Hashed name : " + hexString.toString());
        
        md = MessageDigest.getInstance("SHA-512");
        md.update("Huda".getBytes());
        digest = md.digest();
        hexString = new StringBuffer();
        for (int i = 0; i < digest.length; i++) {
            hexString.append(Integer.toHexString(0xFF & digest[i]));
        }
        System.out.println("SHA 512 Hashed name : " + hexString.toString());
        keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        pair = keyPairGen.generateKeyPair(); 
        publicKey = pair.getPublic(); 
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] input = "Huda".getBytes(); 
        cipher.update(input);
        byte[] cipherText = cipher.doFinal();
        for (int i = 0; i < cipherText.length; i++) {
            hexString.append(Integer.toHexString(0xFF & cipherText[i]));
        }
        System.out.println( "Encrypt " + hexString);
        cipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());
        byte[] decipheredText = cipher.doFinal(cipherText);
        System.out.println("Decrypt" + new String(decipheredText));
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(pair.getPrivate());
        sign.update(input);
       // System.out.println(DatatypeConverter
         //         .printHexBinary(sign.sign()));
         System.out.println(Base64.getEncoder().encodeToString(sign.sign()));
    }
}
