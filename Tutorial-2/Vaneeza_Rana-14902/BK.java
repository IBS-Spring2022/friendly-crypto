/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bk;

/**
 *
 * @author Vaneeza Rana
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.util.Base64;
import java.security.Signature;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import java.security.KeyPairGenerator;
import javax.crypto.NoSuchPaddingException;

public class BK {

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public BK() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }

    public void writeToFile(String path, byte[] key) throws IOException {
        File f = new File(path);
        f.getParentFile().mkdirs();

        FileOutputStream fos = new FileOutputStream(f);
        fos.write(key);
        fos.flush();
        fos.close();
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, NoSuchPaddingException, InvalidKeyException {
        
        BK keyPairGenerator = new BK();
        keyPairGenerator.writeToFile("RSA/publicKey", keyPairGenerator.getPublicKey().getEncoded());
        keyPairGenerator.writeToFile("RSA/privateKey", keyPairGenerator.getPrivateKey().getEncoded());
        System.out.println(Base64.getEncoder().encodeToString(keyPairGenerator.getPublicKey().getEncoded()));
        System.out.println(Base64.getEncoder().encodeToString(keyPairGenerator.getPrivateKey().getEncoded()));
        
    }
}




