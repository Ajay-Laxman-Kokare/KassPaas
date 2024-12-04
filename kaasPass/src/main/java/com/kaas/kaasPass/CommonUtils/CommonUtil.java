package com.kaas.kaasPass.CommonUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
public class CommonUtil {
	public static final String ALGORITHM = "RSA";
	
	public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }
	
	 // Save the public key to a file
    public static void savePublicKey(PublicKey publicKey, String filePath) throws IOException {
        byte[] keyBytes = publicKey.getEncoded();
        String keyString = Base64.getEncoder().encodeToString(keyBytes);
        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write(keyString);
        }
    }

 // Save the private key to a file
    public static void savePrivateKey(PrivateKey privateKey, String filePath) throws IOException {
        byte[] keyBytes = privateKey.getEncoded();
        String keyString = Base64.getEncoder().encodeToString(keyBytes);
        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write(keyString);
        }
    }
    // Load the public key from a file
    public static PublicKey loadPublicKey(String filePath) throws Exception {
        String keyString = new String(java.nio.file.Files.readAllBytes(java.nio.file.Path.of(filePath)));
        byte[] keyBytes = Base64.getDecoder().decode(keyString);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(spec);
    }

    // Load the private key from a file
    public static PrivateKey loadPrivateKey(String filePath) throws Exception {
        String keyString = new String(java.nio.file.Files.readAllBytes(java.nio.file.Path.of(filePath)));
        byte[] keyBytes = Base64.getDecoder().decode(keyString);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(spec);
    }   
    
    
    // Encrypt the message
    public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Decrypt the message
    public static String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }
	
}
