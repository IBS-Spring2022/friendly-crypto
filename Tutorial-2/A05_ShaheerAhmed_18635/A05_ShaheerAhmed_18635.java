import java.io.*;
import java.security.*;
import java.security.spec.*;
import java.util.*;
import javax.crypto.Cipher;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;


public class A05_ShaheerAhmed_18635 {
    static String pubKeyString;
    static String pvtKeyString;
    static PublicKey publicKey;
    static PrivateKey privateKey;
    static byte[] encryptedText;
    static byte[] signature;

    static PublicKey bitPublicKey;
    static PrivateKey bitPrivateKey;
    static String bitPubKeyString;
    static String bitPvtKeyString;


    public static void keyGen() throws Exception {

        SecureRandom secRandom = new SecureRandom();
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        KeyPair kp;
        Key pubKey;
        Key pvtKey;

        String pvtFile = "private";
        String pubFile = "public";
        Writer out;

        Base64.Encoder encoder = Base64.getEncoder();

        kpg.initialize(2048, secRandom);
        kp = kpg.generateKeyPair();

        pubKey = kp.getPublic();
        pvtKey = kp.getPrivate();

        out = new FileWriter(pvtFile + ".key");
        out.write("-----BEGIN RSA PRIVATE KEY-----\n");
        out.write(encoder.encodeToString(pvtKey.getEncoded()));
        out.write("\n-----END RSA PRIVATE KEY-----\n");
        out.close();

        out = new FileWriter(pubFile + ".pub");
        out.write("-----BEGIN RSA PUBLIC KEY-----\n");
        out.write(encoder.encodeToString(kp.getPublic().getEncoded()));
        out.write("\n-----END RSA PUBLIC KEY-----\n");
        out.close();

        // getKeys();
        System.out.print("Key generation successful!");
        System.out.println("Private Key: " + pvtKey.getEncoded());
        System.out.println("Public Key: " + pubKey.getEncoded());
    }

    public static void getKeys() throws InvalidKeySpecException, NoSuchAlgorithmException {
        try {
            File pvtFile = new File("private.key");
            File pubFile = new File("public.pub");
            Scanner pvtsc = new Scanner(pvtFile);
            Scanner pubsc = new Scanner(pubFile);

            int lineNumber = 0;
            String line;

            while (pubsc.hasNextLine()) {
                line = pubsc.nextLine();
                if (lineNumber == 1) {
                    pubKeyString = line;
                    byte[] publicBytes = Base64.getDecoder().decode(line);
                    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
                    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                    publicKey = keyFactory.generatePublic(keySpec);
                    lineNumber++;
                } else {
                    lineNumber++;
                }
            }

            lineNumber = 0;
            while (pvtsc.hasNextLine()) {
                line = pvtsc.nextLine();
                if (lineNumber == 1) {
                    pvtKeyString = line;
                    byte[] privateBytes = Base64.getDecoder().decode(line);
                    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateBytes);
                    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                    privateKey = keyFactory.generatePrivate(keySpec);
                    lineNumber++;
                } else {
                    lineNumber++;
                }
            }
            pvtsc.close();
            pubsc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
        }
    }

    public static void hashSHA(String msg, String type) throws Exception {
        MessageDigest digest = MessageDigest.getInstance(type);
        byte[] hash = digest.digest(msg.getBytes(StandardCharsets.UTF_8));

        BigInteger number = new BigInteger(1, hash);

        StringBuilder hexString = new StringBuilder(number.toString(16));

        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }
        System.out.println(msg + " hashed with " + type + ": ");
        System.out.println(hexString.toString());

    }

    public static void createSignature(String msg) throws Exception {

        Signature sign = Signature.getInstance("SHA256withRSA");

        sign.initSign(privateKey);

        byte[] signatureData = msg.getBytes();
        sign.update(signatureData);

        signature = sign.sign();

        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < signature.length; i++) {
            hexString.append(Integer.toHexString(0xFF & signature[i]));
        }
        System.out.println("\n");
        System.out.println("Signature for " + msg + " is: ");
        System.out.println(hexString);
        verifySignature(msg);
    }

    public static void verifySignature(String msg) throws Exception {
        Signature sign = Signature.getInstance("SHA256withRSA");

        sign.initVerify(publicKey);

        sign.update(msg.getBytes());

        boolean flag = sign.verify(signature);

        if (flag) {
            System.out.println("\nSignature Verified");
        } else {
            System.out.println("\nSignature Failed");
        }
    }

    public static void encryptRSA(String msg) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");

        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        cipher.update(msg.getBytes());

        encryptedText = cipher.doFinal();

        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < encryptedText.length; i++) {
            hexString.append(Integer.toHexString(0xFF & encryptedText[i]));
        }

        System.out.println("Encrypted Text: ");
        System.out.println(hexString);

    }

    public static void decryptRSA() throws Exception {

        Cipher cipher = Cipher.getInstance("RSA");

        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] decryptedText = cipher.doFinal(encryptedText);

        System.out.println("Decrypted Text: ");
        System.out.println(new String(decryptedText));
    }

    public static void main(String[] args) throws Exception {
        File privateFile = new File("private.key");
        File publicFile = new File("public.pub");
        boolean isExit = false;
        String text;
        String type;

        Scanner sc = new Scanner(System.in);
        String option;

        System.out.println("Welcome to the Encryption Demo\n");

        while (!isExit) {
        if (!privateFile.exists() || !publicFile.exists()) {
        System.out.println("Do you want to generate a new key pair? (Y/n): ");
        option = sc.nextLine();
        if (option.equals("Y") || option.equals("y")) {
        keyGen();
        getKeys();
        } else if (option.equals("N") || option.equals("n")) {
        isExit = true;
        break;
        }

        } else {
        getKeys();
        System.out.println("Please enter choose an option:");
        System.out.println("1: Hash Text (SHA-1)");
        System.out.println("2: Encrypt Text");
        System.out.println("3: Decrypt Text");
        System.out.println("4: Encrypt Text with Signature");
        System.out.println("5: Exit");

        System.out.println(" ");
        System.out.print("Enter option: ");

        option = sc.nextLine();
        System.out.println("\n");

        switch (option.charAt(0)) {
        case '1':
        System.out.println("Which hashing algorithm would you like to choose (SHA-256/SHA-512):");
        type = sc.nextLine();
        if (type.equals("SHA-256")) {
        System.out.println("Please enter text to hash using SHA-256: ");
        text = sc.nextLine();
        System.out.println("\n");
        hashSHA(text, type);
        }
        if (type.equals("SHA-512")) {
        System.out.println("Please enter text to hash using SHA-512: ");
        text = sc.nextLine();
        System.out.println("\n");
        hashSHA(text, type);
        }
        break;

        case '2':
        System.out.println("Please enter text encrypt using assymetric RSA public key: ");
        text = sc.nextLine();
        encryptRSA(text);
        break;
        case '3':
        decryptRSA();
        break;
        case '4':
        System.out.println("Please enter text encrypt using digital signature: ");
        text = sc.nextLine();
        createSignature(text);
        break;
        case '5':
        isExit = true;
        break;
        }
        System.out.println("\n");
        }
        }
        sc.close();
    }

}
