/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryption;

/**
 *
 * @author Vaneeza Rana
 */


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Encryption {

    private static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAm1PDKxXxUU48Kmi3p1g+FqJ/WBY0WeQVITS91VRMejw8s7IMxDd0pBARdMKDKy+BsM4lqR+MdfwcWp7x3TfPVHo/7b5N5kghsRSSYj9GOKb0p11N0IdfuNE2pxduRnyu9FrGIbbId4hEmOhKFQdbLf/2qb/MOW4K9hWFAfXgqxezun8w3Yg2+tfL7z/2wSkAbor21qP9Omp7mjYO4G5pETVqbbh9A98OuE87XPgCskxbbZh382ZTgcbmQ8GkG9+8VGnPCS4oZCv816VKg1eCGOq62aMSmbQSe2L5q58YQDYWsPwrwCVBioOyMHxhBE6hPMOLV95zSy+KiNFS8tr+owIDAQAB";
    

    public static PublicKey getPublicKey(String base64PublicKey){
        PublicKey publicKey = null;
        try{
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }

   public static byte[] encrypt(String data, String publicKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
        return cipher.doFinal(data.getBytes());
    }

   
  public static void main(String[] args) throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException {
        try {
            String encryptedString = Base64.getEncoder().encodeToString(encrypt("Vaneeza Rana", publicKey));
            System.out.println(encryptedString);
            
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        }

    }
}
    


