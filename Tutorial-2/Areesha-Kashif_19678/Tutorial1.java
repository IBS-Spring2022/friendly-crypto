//package com.company;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Tutorial1 {

    static String message ="Areesha Kashif";
    static String myPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDALdWE1WGsdVKzkrmSi+3snaSqe4PlrNn4SF09HRAnu+ZEQCVcsVY/8/0fxVWoaFtywfMsKUMZbKxSsQM9zzeMsuDP5A3NTw0y8iZqzyK8aeRhv3fSNa2Oaf29xrBL4vbOXMZzNVLGxCpOVhbaer+M10I7VwkZZjxiDXu0WaXscgobJGAtX1QZAGTpxxRXfF6khQChZqjIqLVmjiX4KGB2eV1GnqE9i5g8D6JrWdohmRqsHvtR65gR1Yfd4AWXN6xHWTnUr7fKqsV+CEij1a/lqGFsOF1sjlC70FkfVmtNPzIbZ78/q4qmzFNhaWSzR+qJ6g3D9XoySFsEltGdnFNvAgMBAAECggEACv5UOY4PUVNA0N7y2KxChweZlNsN94SEz4wsMUPJWcyI5pyYxo2qdmwZrciXFtQpbw4jQAJ4QSsTTIOtwMpqzhSpgZr1IyqDgceSZQfGX+ra0rCmRRMnA3+SsVl9Wkn4fmOWQp7pRk+Qb/zCv+rHrGHf+w8GiTps99r3maCLOlROTTHQvjMu1Cxp2INtXFY1WBtJdw6qo9zl88U+G26DKXALGegzZbl8e5jNHCUvgs5xqQJoNe4ap7WZikaH7Fw2LtZ3W1PIlFeVontTDPKsED+vFPVMER+CIK2NOI1mtiQpZF9V2TCCOthjLSQkQoHNmBbTbMLHa96Ad4SrCxISqQKBgQDrtekecnyIqT/lHYIuKIjDpD9+aj9UUZhW2LfO7LEYfsoHhzv9zcBai8ag0j7DMZchiPXNEAaxhMsXIa2vi7yoh12OgmO83fry7zSh5tqJzLavFS2fxdXYFdYA94z3g0rTKRniRGLynO7fjLgeZJXawjCHMoV44Ko1lfSDhguwBQKBgQDQuKroI6PVhLLowbwQmrwLGnMm9p2NPfDnFAD1SHhmxHUNGItzte4wTJHi35+46Oi+hJlKGDgIF2/TjUeyGaywXufr7t1L+Fox4QtnWesrXFvJ7Dfxllx5dH5HE7yqPkCb0fSrB4zrHfeJKvucB2bfgpd8FJlOGFT9e/C53Qlz4wKBgGK2XhiILas15jJXd0tJYm08ffK9ICR5v2ivfdrEuS3e+1DHy9Hwttu0qcx0ACDSxc4fg4ELcyIo0LyxOAHKbYnOltm1tXtFI3FVyB79hQwslV0ha2f7/CBwlJz3Dywg6dcg4Vwm86FTI56vWfQwf6mLpbMY71i+Dco/MJVcL8zZAoGBALH1ohLHwHre58Q42ua1Z7t309LW1sKEm5AKqD56id3lh3+g+9WuuuWHg6OS05ZPT5KRcDmSqMONaBVSEl0yMuE2Kjwpr5mEqTJ6FMUMD80qtEBYHeLoNvR/dOOZWnU1GtVAkIH6sBYPuOzFLz57uSS9LffngEbrzBtMv5VZdyPNAoGBANqJ+bvaeY9+siM1tLnmi+9GrU02c4Q2JNBTdEHJAzmfeWwUV4U6V+rUAJBmzOPDx3W90FyEm6x1AvmuXXOoUD3nz5RpMeHz7eIIEmD3BCJgOGH2Ipuac1+jY6p9scoZQEASgwH31WxSN1J226jl1VmuIkAMV2aPLvLleSpgyF0U";
    static String myPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwC3VhNVhrHVSs5K5kovt7J2kqnuD5azZ+EhdPR0QJ7vmREAlXLFWP/P9H8VVqGhbcsHzLClDGWysUrEDPc83jLLgz+QNzU8NMvImas8ivGnkYb930jWtjmn9vcawS+L2zlzGczVSxsQqTlYW2nq/jNdCO1cJGWY8Yg17tFml7HIKGyRgLV9UGQBk6ccUV3xepIUAoWaoyKi1Zo4l+ChgdnldRp6hPYuYPA+ia1naIZkarB77UeuYEdWH3eAFlzesR1k51K+3yqrFfghIo9Wv5ahhbDhdbI5Qu9BZH1ZrTT8yG2e/P6uKpsxTYWlks0fqieoNw/V6MkhbBJbRnZxTbwIDAQAB";
    static String sirKeyReplace = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAg7ocIWR6KNDbRPYA3AIKvsqHOJU4r7u1bxI/liMdQKEyDhgUKS5Arugwey4UDjU7x7i79Bd5vSqBvwJQFp1RQgOSLeEa6UrLpgVpg4TH6FtI83KDOoNTP0a8HL0QLBqIjvijpu36CGWNcrKih1zgLQvMmBX7TrDdmUISA+dv7yPpyHNdN7UNLYMYT8775MulkQfnetNZODwD/AO0+2T7V+WZyohBe0J5kdu+PSpMC5gxHTwikusfzo/MwIrBBtb4F55c0TluuyYfG2znL9kemwwsajgzBA7R2pBQGFr6a+ciWxoJHLss5TargoAGkmXxx7QCyRkfY03VRGnU89hAywIDAQAB";

    public static void key()
    {
        KeyPairGenerator keyPairGen = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //Initializing the KeyPairGenerator
        keyPairGen.initialize(2048);

        //Generating the pair of keys
        KeyPair pair = keyPairGen.generateKeyPair();

        //Getting the private & public key from the key pair
        PrivateKey privKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();

        Base64.Encoder encoder = Base64.getEncoder();
        System.out.println("\n\nPublic Key:\n" + encoder.encodeToString(publicKey.getEncoded()));
        System.out.println("\nPrivate Key:\n" + encoder.encodeToString(privKey.getEncoded()));
    }

    public static void hash() throws NoSuchAlgorithmException
    {
        //Creating the MessageDigest object
        MessageDigest md1 = MessageDigest.getInstance("SHA-256");
        MessageDigest md2 = MessageDigest.getInstance("SHA-512");


        //Passing data to the created MessageDigest Object
        md1.update(message.getBytes());
        md2.update(message.getBytes());

        //Compute the message digest
        byte[] digest = md1.digest();

        //Converting the byte array in to HexString format
        StringBuffer hexString = new StringBuffer();

        for (int i=0;i<digest.length;i++) {
            hexString.append(Integer.toHexString(0xFF & digest[i]));
        }
        System.out.println("\n\nName Hash - Hex Format - SHA-256 : " + hexString.toString());

        digest = md1.digest();
        hexString = new StringBuffer();

        for (int i=0;i<digest.length;i++) {
            hexString.append(Integer.toHexString(0xFF & digest[i]));
        }
        System.out.println("\n\nName Hash - Hex Format - SHA-512 : " + hexString.toString());
    }

    public static void encryptSign() throws Exception {
        KeyFactory RKeyFact = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keyD = new X509EncodedKeySpec(Base64.getDecoder().decode((sirKeyReplace).getBytes()));
        PublicKey sirPublicKey = RKeyFact.generatePublic(keyD);

        //Creating a Cipher object
        Cipher cipher = Cipher.getInstance("RSA");

        //Initializing a Cipher object
        cipher.init(Cipher.ENCRYPT_MODE, sirPublicKey);

        //Adding data to the cipher
        byte[] input = message.getBytes();
        cipher.update(input);

        //encrypting the data
        byte[] cipherText = cipher.doFinal();
        StringBuffer nameHex = new StringBuffer();

        for (int i=0;i<cipherText.length;i++) {
            nameHex.append(Integer.toHexString(0xFF & cipherText[i]));
        }
        System.out.println("\n\nEncrypted text:\n" + nameHex.toString());


        PrivateKey myDecodedPrivateKey = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(myPrivateKey.getBytes()));          //decode private key
        myDecodedPrivateKey = RKeyFact.generatePrivate(keySpec);

        //Creating a Signature object
        Signature sign = Signature.getInstance("SHA256withRSA");

        //Initialize the signature
        sign.initSign(myDecodedPrivateKey);

        byte[] bytes = "msg".getBytes();
        //Adding data to the signature
        sign.update(bytes);

        //Calculating the signature
        byte[] signature = sign.sign();
        StringBuffer signHex = new StringBuffer();

        //Printing the signature
        for (int i=0;i<signature.length;i++) {
            signHex.append(Integer.toHexString(0xFF & signature[i]));
        }
        System.out.println("\nSignature:\n"+ signHex.toString());
    }


    public static void main(String[] args) throws Exception {
	// write your code here
        key();
        hash();
        encryptSign();
    }
}
