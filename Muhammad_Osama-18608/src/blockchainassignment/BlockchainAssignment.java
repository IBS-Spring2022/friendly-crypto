/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchainassignment;

import java.math.BigInteger;
import java.security.*;
import java.util.Base64;
import javax.crypto.Cipher;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

//import com.chilkatsoft.*;

/**
 *
 * @author usamaasif
 */
public class BlockchainAssignment {

    static String name = "MUHAMMAD OSAMA";
    static PrivateKey privat;
    static PublicKey pub1;

    static String s = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyv01PwQGnzSdKVCMOLPIqVGYGkcKPlJi4GSFZnz0bFyjVWuDCcf/JAIjDkPILK3mNzOqxDIy5aKsP5SJLGvHH6j9f1pqbgLDoiILyDnGTLV1ozV9mOV8cllw5dvYoIMxghlNou/8BgP3Z2pVXzzcG/UoGEXb5TbfxRrzUkTmzLDJtjneaSx1oe6RzuRuO87wztQKFLMM4pRmo4XrtkrnRwfLxS8dHgiaxysujJaxGJIIgtUmrpZAeBymsutOvjf5XVp5rtv8rdxMuw8Jct6SrxVETdpK1A60Iws4azQf+qhhsWG1KMfl+APwbsZIb/GTKcxi879Z/MgCfevD2rOGjQIDAQAB";
    static KeyFactory keyFact;
    static X509EncodedKeySpec x509KeySpec;

    public static void signature()
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidKeySpecException {

        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(privat);
        byte[] bytes = name.getBytes();
        sign.update(bytes);
        byte[] signature = sign.sign();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < signature.length; i++) {
            hexString.append(Integer.toHexString(0xFF & signature[i]));
        }
        System.out.println("");

        System.out.println("Signature hex format of a name : " + hexString.toString());

    }

    public static void encrypt() throws Exception {
        keyFact = KeyFactory.getInstance("RSA");
        x509KeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode((s).getBytes()));
        PublicKey publicKey = keyFact.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance("RSA");

        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        cipher.update(name.getBytes());
        byte[] result = cipher.doFinal();

        // String s = new String(result);
        System.out.println("");
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            hexString.append(Integer.toHexString(0xFF & result[i]));
        }
        System.out.println("Encrypted Hex format of a name : " + hexString.toString());

    }

    public static void hashFunction(String hashalgo) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(hashalgo);
        md.update(name.getBytes());
        byte[] digest = md.digest();

        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < digest.length; i++) {
            hexString.append(Integer.toHexString(0xFF & digest[i]));
        }
        System.out.println("hash function of " + hashalgo + " format : " + hexString.toString());

    }

    public static void generateRSAKkeyPair() throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

        keyPairGenerator.initialize(2048, secureRandom);
        KeyPair pair = keyPairGenerator.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();
        // System.out.println(pub);
        pub1 = pub;
        privat = priv;
        // converting to hex
        String pubkey = Base64.getEncoder().encodeToString(pub.getEncoded());
        String prvkey = Base64.getEncoder().encodeToString(priv.getEncoded());
        System.out.println("This is private key: " + prvkey);
        System.out.println("");
        System.out.println("This is public key: " + pubkey);

    }

    public static void main(String[] args) throws Exception {
        System.out.println("");
        // Q1 Creating public and private keys through RSA
        generateRSAKkeyPair();
        System.out.println("");
        // hash functions
        hashFunction("SHA-256");
        System.out.println("");
        hashFunction("SHA-512");
        System.out.println("");
        // Encrypt
        encrypt();
        System.out.println("");
        // signature
        signature();

    }

}
