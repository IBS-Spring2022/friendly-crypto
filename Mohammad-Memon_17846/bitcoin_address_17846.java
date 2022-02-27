import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.InputMismatchException;
import java.util.Scanner;
// import javax.xml.bind.DatatypeConverter;
// import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
// import javax.crypto.IllegalBlockSizeException;
// import javax.crypto.NoSuchPaddingException;

import java.io.File;
// import java.io.FileOutputStream;
import java.io.FileWriter;
// import java.io.IOException;
import java.io.Writer;
// import java.nio.InvalidMarkException;
import java.nio.charset.StandardCharsets;

public class bitcoin_address_17846 {

    // private static Object pubKey;
    // private static Object pvtKey;
    public static KeyPair RSA_Pair;
    public static byte[] encrypted;
    // public static KeyPair kp = null;
    public static void main(String[] args) throws Exception {
        System.out.println("Bitcoin Wallet Address Generation Assignment - Mohammd Memon 17846.\n" +
        "Please enter the index numbers to perform the specified tasks.\n");

        Scanner scan = new Scanner(System.in);
        MessageDigest sha = null;
        byte[] s1 = null;
        byte[] r2 = null;
        byte[] s3 = null;
        int input = -1;
        while (input != 0) {
            System.out.println("Enter 0 at any stage to close the program. For other possible inputs are: \n"+
            "<1> Generate new RSA Key-pair (Will override old values).\n"+
            "<2> Print existing RSA Keys (Entered in Google doc file).\n"+
            "<3> Print SHA-256 Hash.\n"+
            "<4> Print Ripemd-160 and SHA-512 Hash.\n"+
            "<5> Print Encryption output.\n"+
            "<6> Print Signature.\n"+
            "<7> Print final Bitcoin address.\n");

            try {
                input = scan.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Input mismatch caught: Please enter integer values.");
                input = -1;
                // System.out.println(input);
                // continue;
                break;
            }
            
            if (input < 0) {
                System.out.println("The code does not accept negative integer values. Please enter values from 0 - 6.");
                continue;
            }
            
            else if (input == 1) {
                // System.out.println(input);
                RSA_Pair = RSAKeyGen();

            }

            else if (input == 2) {
                System.out.println("\nPublic key:");
                File file = new File("public.pub");
                Scanner FR = null;
                // char checker = ' ';
                // String output = "";
                try {
                    FR = new Scanner(file);
                    while (FR.hasNextLine()) {
                        // output = output + FR.nextLine();
                        System.out.println("\n" + FR.nextLine());
                    }
                    System.out.println();
                } catch (Exception e) {
                    System.err.println("Error caught while reading file.");
                }
            }

            else if (input == 3) {
                // System.out.println(input);
                String name = "Mohammad Memon";
                // MessageDigest sha = null;
                
                try {
                    sha = MessageDigest.getInstance("SHA-256");
                } catch (Exception e) {
                    System.err.println("Error caught during SHA-256 hashing.");
                }
                sha.update(name.getBytes());
                s1 = sha.digest();

                // StringBuffer hex = new StringBuffer();
                // for (int i = 0; i < s1.length; i++) {
                //     hex.append(Integer.toHexString(0xFF & s1[1]));
                // }

                System.out.println("SHA: " + bytesToHex(s1) + "\n");
            }

            else if (input == 4) {
                // System.out.println(input);
                // MessageDigest ripe = null;
                try {
                    // ripe = MessageDigest.getInstance("RipeMD160", "BC");
                    // System.out.println(ripe);
                    // byte[] r1 = ripe.digest(s1);
                    byte[] r1 = Ripemd160.getHash(s1);
                    r2 = new byte[r1.length + 1];
                    r2[0] = 0;
                    for (int i = 0; i < r1.length; i++) {
                        r2[i+1] = r1[i];
                    }
                    String RMD = "RMD: " + bytesToHex(r2) + "\n";
                    printerFunction(RMD);

                    byte[] s2 = sha.digest(r2);
                    s3 = sha.digest(s2);

                    String SHA512 = "SHA-512: " + bytesToHex(s3) + "\n";
                    printerFunction(SHA512);
                } catch (Exception e) {
                    System.err.println("Error caught in RipeMD-160 hash.");
                }
                
            }

            else if (input == 5) {
                // System.out.println(input);
                String plainText = "Mohammad Memon";

                String sirPub = "";
                File sirPubFile = new File("SirPub.txt");
                Scanner FR = null;
                try {
                    FR = new Scanner(sirPubFile);
                    while (FR.hasNextLine()) {
                        // output = output + FR.nextLine();
                        sirPub = sirPub + FR.nextLine();
                    }
                    System.out.println();
                } catch (Exception e) {
                    System.err.println("Error caught while reading Public file.");
                }

                PublicKey SirPubKey = getPublicKey(sirPub);

                String sirPvt = "";
                File sirPvtFile = new File("SirPvt.txt");
                Scanner PVTFR = null;
                try {
                    PVTFR = new Scanner(sirPvtFile);
                    while (PVTFR.hasNextLine()) {
                        // output = output + FR.nextLine();
                        sirPvt = sirPvt + PVTFR.nextLine();
                    }
                    System.out.println();
                } catch (Exception e) {
                    System.err.println("Error caught while reading Private file.");
                }

                PrivateKey SirPvtKey = getPrivateKey(sirPvt);

                byte[] cipherTextENC
                = do_RSAEncryption(
                    plainText,
                    RSA_Pair.getPrivate());

                System.out.print("The Encrypted Text is: ");

                System.out.println(
                    bytesToHex(cipherTextENC) + "\n");

                    encrypted = cipherTextENC;

                String decryptedText = do_RSADecryption(
                    cipherTextENC,
                    RSA_Pair.getPublic());

                System.out.println(
                "The decrypted text is: "
                + decryptedText + "\n");
            }

            else if (input == 6) {
                // System.out.println(input);
                String enc = new String(encrypted, StandardCharsets.UTF_8);
                String signature = sign(enc, RSA_Pair.getPrivate());

                System.out.println("Signature calculated: " + signature + "\n");

                //Let's check the signature
                boolean isCorrect = verify(enc, signature, RSA_Pair.getPublic());
                System.out.println("Signature correct: " + isCorrect);   
            }

            else if (input == 7) {
                // System.out.println(input);
                byte[] a1 = new byte[25];
                for (int i = 0; i < r2.length; i++) {
                    a1[i] = r2[i];
                }
                for (int i = 0; i < 5; i++) {
                    a1[20 + i] = s3[i];
                }
                System.out.println("ADDR: " + Base58.encode(a1) + "\n");
            }

            else if (input == 0) {
                System.out.println("Exiting program. Have a nice day!");
                scan.close();

            }

            else {
                System.err.println("Input value is out of range.");

            }
        }

    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte hashByte  : bytes) {
            int intVal = 0xff & hashByte;
            if (intVal < 0x10) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(intVal));
        }
        return sb.toString();
    }

    public static void printerFunction(String x) {
        System.out.println(x);
    }
    
    public static KeyPair RSAKeyGen() {
        KeyPairGenerator kpg = null;
        try {
            kpg = KeyPairGenerator.getInstance("RSA");
        } catch (Exception e) {
            System.err.print("Error generating key-pair.");
        }
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();

        //kp contains the pulic key and the private key. 

        Key pub = kp.getPublic();
        Key pvt = kp.getPrivate();

        // System.out.println("PUBLIC: " + pub.hashCode());
        // System.out.println("PVT:" + pvt.hashCode());

        Base64.Encoder encoder = Base64.getEncoder();
        File filePub = null;
        File filePvt = null;
        // FileOutputStream output = null;
        Writer output = null;
        try {
            filePub = new File("public");
            filePvt = new File("private");

            output = new FileWriter(filePvt + ".key");
            output.write("-----BEGIN RSA PRIVATE KEY-----\n");
            output.write(encoder.encodeToString(pvt.getEncoded()));
            // pvtKey = encoder.encodeToString(pvt.getEncoded());
            output.write("\n-----END RSA PRIVATE KEY-----\n");
            output.close();

            output = new FileWriter(filePub + ".pub");
            output.write("-----BEGIN RSA PUBLIC KEY-----\n");
            output.write(encoder.encodeToString(pub.getEncoded()));
            // pubKey = encoder.encodeToString(pub.getEncoded());
            output.write("\n-----END RSA PUBLIC KEY-----\n");
            output.close();

        } catch (Exception e) {
            System.err.println("Null pointer. Key writing operation unsuccessful.");
        }
        return kp;
        
        // Base64.Encoder encoder = Base64.getEncoder();
    }

    public static byte[] do_RSAEncryption(
        String plainText,
        PrivateKey privateKey)
        throws Exception
    {
        Cipher cipher
            = Cipher.getInstance("RSA");
 
        cipher.init(
            Cipher.ENCRYPT_MODE, privateKey);
 
        return cipher.doFinal(
            plainText.getBytes());
    }
 
    // Decryption function which converts
    // the ciphertext back to the
    // original plaintext.
    public static String do_RSADecryption(
        byte[] cipherText,
        PublicKey publicKey)
        throws Exception
    {
        Cipher cipher
            = Cipher.getInstance("RSA");
 
        cipher.init(Cipher.DECRYPT_MODE,
                    publicKey);
        byte[] result
            = cipher.doFinal(cipherText);
 
        return new String(result);
    }

    public static String sign(String plainText, PrivateKey privateKey) throws Exception {
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(plainText.getBytes());
    
        byte[] signature = privateSignature.sign();
    
        return Base64.getEncoder().encodeToString(signature);
    }

    public static boolean verify(String plainText, String signature, PublicKey publicKey) throws Exception {
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update(plainText.getBytes());
    
        byte[] signatureBytes = Base64.getDecoder().decode(signature);
    
        return publicSignature.verify(signatureBytes);
    }

    public static PublicKey getPublicKey(String base64PublicKey) throws Exception{
        PublicKey publicKey = null;
        try{
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return publicKey;
    }

    public static PrivateKey getPrivateKey(String base64PrivateKey){
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return privateKey;
    }
}