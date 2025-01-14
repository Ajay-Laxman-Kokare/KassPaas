package com.kaas.kaasPass.ServiceImpl;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.zxing.common.StringUtils;
import com.kaas.kaasPass.CommonUtils.CommonUtil;
import com.kaas.kaasPass.CommonUtils.QrUtils;
import com.kaas.kaasPass.Model.Tourist;
import com.kaas.kaasPass.Repository.TouristRepository;
import com.kaas.kaasPass.Service.KasService;

@Service
public class KasServiceImpl implements KasService {
	
	@Autowired
	TouristRepository repository;
	
	public static String priKey = null;

	@Override
	public String save(Tourist tourist) throws Exception {
		
		KeyPair keyPair = CommonUtil.generateKeyPair();
		String publicKeyPath = "/Users/ajaykokare/Documents/KasProject/storeKeys/publicKey.txt";
		String privateKeyPath = "/Users/ajaykokare/Documents/KasProject/storeKeys/privateKey.txt";
		
		CommonUtil.savePublicKey(keyPair.getPublic(), publicKeyPath);
		CommonUtil.savePrivateKey(keyPair.getPrivate(), privateKeyPath);
	
		//load keys from file
		
		PublicKey publicKey = CommonUtil.loadPublicKey(publicKeyPath);
        
		
        String encryptedData = CommonUtil.encrypt(tourist.toString(),publicKey);
        
        System.out.println(encryptedData);
        tourist.setEncryptedString(encryptedData);
        
		repository.save(tourist);
		
		String path = "/Users/ajaykokare/Documents/KasProject/QrCodes/"+tourist.getContactNumber()+".png";
		
		String logoPath = "/Users/ajaykokare/Documents/KasProject/bharatConnect.png";
		
		QrUtils.generateQr(encryptedData, path,logoPath);
		
		return "QrCode Stored at: "+ path;//repository.save(tourist);
	}

	@Override
	public String retriveTourist(String filePath) throws Exception {
		String sealedString = QrUtils.readQr(filePath);
		String privateKeyPath = "/Users/ajaykokare/Documents/KasProject/storeKeys/privateKey.txt";

		PrivateKey privateKey = CommonUtil.loadPrivateKey(privateKeyPath);

		String unsealed = CommonUtil.decrypt(sealedString, privateKey);
		//Tourist t = repository.findBy()
		System.out.println(unsealed);
		
		return unsealed;
	}

}
