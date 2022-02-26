import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.security.SecureRandom;
import java.security.Signature;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;







//Muhammad Moosa Hashim
//18642

public class KeyGeneratorJava {
    public static void main(String[] args) throws Exception {
     //   System.out.println("Hello, World!");
         
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
      
      //Initializing the KeyPairGenerator
      keyPairGen.initialize(2048);
      
      //Generating the pair of keys
      KeyPair myKey = keyPairGen.generateKeyPair();
      
      //Getting the private key from the key pair
      PrivateKey myPrivKey = myKey.getPrivate();   
      
      //Getting the public key from the key pair
      PublicKey myPubKey = myKey.getPublic(); 

    System.out.println("The Keys have been generated");
    System.out.println();




    System.out.println("Public key is:\n" + Base64.getEncoder().encodeToString(myKey.getPublic().getEncoded()));
        
     System.out.println();
     System.out.println();

     System.out.println("Private key is:\n" + Base64.getEncoder().encodeToString(myKey.getPrivate().getEncoded()));

     System.out.println();
     System.out.println();
    
    //other way to write the output is 
         
    //System.out.println("Public key is:\n" + Base64.getEncoder().encodeToString(myPubKey.getEncoded()));

     String myName = "Muhammad Moosa Hashim";
     
     System.out.println("The name, " + myName + ", hashed with SHA-256 is: " + "\n" + hashSHA256(myName.getBytes()));
     
     System.out.println();
     
     System.out.println("The name, " + myName + ", hashed with SHA-512 is: " + "\n" + hashSHA512(myName.getBytes()));

     System.out.println();

     System.out.println("The name, " + myName + ", encrypted with RSA Assymetric algorithm is: " + "\n" + encryptionRSA(myPubKey, myName));

     System.out.println();
     System.out.println("The text message, " + myName + ", signed with above private key in RSA algorithm, has signature: " + "\n" + digiSignature(myPrivKey, myName));

     System.out.println();

    }

    static String hashSHA256 ( byte[] nameBytes ) {
        //return name;

        String hashedValue = " ";

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(nameBytes);
            byte[] digestedBytes = digest.digest();
            
            StringBuilder hexString = new StringBuilder ( 2*digestedBytes.length);
            for ( int i = 0;i < digestedBytes.length; i ++) {
                String hex = Integer.toHexString(0xff & digestedBytes[i]);
                if ( hex.length() == 1 ) {
                    hexString.append('0');
                }

                hexString.append(hex);
               
            }
            return hexString.toString();

        } catch (Exception e) {
            //TODO: handle exception
        }
        return " ";
 
    }

    static String hashSHA512 ( byte[] nameBytes ) {
        //return name;

        String hashedValue = " ";

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.update(nameBytes);
            byte[] digestedBytes = digest.digest();
            
            StringBuilder hexString = new StringBuilder ( 2*digestedBytes.length);
            for ( int i = 0;i < digestedBytes.length; i ++) {
                String hex = Integer.toHexString(0xff & digestedBytes[i]);
                if ( hex.length() == 1 ) {
                    hexString.append('0');
                }
                hexString.append(hex);
                
                
            }

            return hexString.toString();
        } catch (Exception e) {
            //TODO: handle exception
        }
        return " ";
 
    }


    public static String encryptionRSA ( PublicKey pubKey, String text ) throws Exception {
        
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE,pubKey);
            byte[] input = text.getBytes();
            cipher.update(input);


            byte[] cipherText = cipher.doFinal();
         //   System.out.println("The text, " + text + " , when encrypted with RSA is as follows" + "\n");
         //  return (new String(cipherText, "UTF8"));
        // return Base64.getEncoder().encodeToString(cipherText);

        StringBuilder hexString = new StringBuilder ( cipherText.length);
         for (int i = 0; i < cipherText.length; i++) {
            hexString.append(Integer.toHexString(0xFF & cipherText[i]));
        }

        return hexString.toString();
           
    }

    public static String digiSignature ( PrivateKey privKey, String text ) throws Exception {
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(privKey);

        byte[] textBytes = text.getBytes();

        sign.update(textBytes);

        byte[] signature = sign.sign();

        StringBuilder hexString = new StringBuilder ( signature.length);
         for (int i = 0; i < signature.length; i++) {
            hexString.append(Integer.toHexString(0xFF & signature[i]));
        }

        return hexString.toString();

    }

}
