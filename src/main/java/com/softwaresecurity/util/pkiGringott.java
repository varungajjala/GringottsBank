package com.softwaresecurity.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Date;

import javax.crypto.Cipher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DefaultSignatureAlgorithmIdentifierFinder;
import org.bouncycastle.operator.bc.BcRSAContentSignerBuilder;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softwaresecurity.gringotts.ExternalUserController;


public class pkiGringott {
	
	private static final Logger logger = LoggerFactory.getLogger(ExternalUserController.class);
	
public static void generateSignedX509Certificate(String userId, HttpSession session){
		
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		
		KeyPairGenerator myGenerator = null;
		X509Certificate x509certificate = null;
		try
		{
			myGenerator = KeyPairGenerator.getInstance("RSA", "BC");
			myGenerator.initialize(1024, new SecureRandom());
			KeyPair keyPair = myGenerator.generateKeyPair();
			
			// setting the start date
			Date BeginDate = new Date(System.currentTimeMillis() - 24L * 60L * 60L * 1000L);
			
			// expiry in 2 years
			Date EndDate = new Date(System.currentTimeMillis() + (2L * 365L * 24L * 60L * 60L * 1000L));
			
			X509v3CertificateBuilder certBuilder = new X509v3CertificateBuilder(new X500Name("CN=" + userId),
																				BigInteger.valueOf(new SecureRandom().nextLong()), 
																				BeginDate,
																				EndDate,
																				new X500Name("CN=" + userId), 
																				SubjectPublicKeyInfo.getInstance(keyPair.getPublic().getEncoded()));
			
			
			AlgorithmIdentifier signatureAlgorithmId = new DefaultSignatureAlgorithmIdentifierFinder().find("SHA256withRSA");
	        AlgorithmIdentifier digestAlgorithmId = new DefaultDigestAlgorithmIdentifierFinder().find(signatureAlgorithmId);
	        AsymmetricKeyParameter privateKey = PrivateKeyFactory.createKey(keyPair.getPrivate().getEncoded());

			X509CertificateHolder holder =  certBuilder.build(new BcRSAContentSignerBuilder(signatureAlgorithmId, digestAlgorithmId).build(privateKey));
			
			Certificate certificate = holder.toASN1Structure();
			
			InputStream myInputStream = new ByteArrayInputStream(certificate.getEncoded());
	        
			try{
				x509certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(myInputStream);
			}
			finally
			{
				myInputStream.close();
			}
			
			System.out.println("Cretificate" + x509certificate.toString());
			
			ServletContext context = session.getServletContext();
            String realContextPath = context.getRealPath("/");
            
            File file_dir = new File(realContextPath+"/certificates");
              if (!file_dir.exists())
            	  file_dir.mkdirs();
            
            String certpath = realContextPath+"/certificates/"+userId+"_cert.pem";
		 
            //String certpath = "/"+userId+"_cert.pem";
			PemWriter pemWriter = new PemWriter(new FileWriter(new File(certpath)));
			pemWriter.writeObject(new PemObject(x509certificate.getType(), x509certificate.getEncoded()));
			pemWriter.flush();
			pemWriter.close();
			 
			//discard private key after certificate generation
			//save public key in vault
			Key publicKey = keyPair.getPublic();
			
			KeyFactory factory = KeyFactory.getInstance("RSA");
			RSAPublicKeySpec publicKeySpec = factory.getKeySpec(publicKey, RSAPublicKeySpec.class);
	        
	        File public_file_dir = new File(realContextPath+"/publickeys");
	        if (!public_file_dir.exists())
	        	public_file_dir.mkdirs();
	        
	        String path = realContextPath+"/publickeys/"+userId+"_public.key";
			//String path = "/"+userId+"_public.key";
			File publickey = new File(path);
			System.out.println(publickey.getAbsolutePath());
			FileOutputStream fout = new FileOutputStream(publickey);
			ObjectOutputStream outputStream = new ObjectOutputStream(fout);
			outputStream.writeObject(publicKeySpec.getModulus());
			outputStream.writeObject(publicKeySpec.getPublicExponent());
			outputStream.close();
			
			// Save private key for sending it in email
			Key private_Key = keyPair.getPrivate();
			
			RSAPrivateKeySpec privateKeySpec = factory.getKeySpec(private_Key, RSAPrivateKeySpec.class);
	        
	        File private_file_dir = new File(realContextPath+"/privatekeys");
	        if (!private_file_dir.exists())
	        	private_file_dir.mkdirs();
	        
	        path = realContextPath+"/privatekeys/"+userId+"_private.key";
			//String path = "/"+userId+"_public.key";
			File privatekey = new File(path);
			System.out.println(privatekey.getAbsolutePath());
			FileOutputStream f_out = new FileOutputStream(privatekey);
			ObjectOutputStream outputStream_p = new ObjectOutputStream(f_out);
			outputStream_p.writeObject(privateKeySpec.getModulus());
			outputStream_p.writeObject(privateKeySpec.getPrivateExponent());
			outputStream_p.close();
	        
			return;
		} catch (Exception e){
			e.printStackTrace();
		}
		
		
		// GENERATE THE PUBLIC/PRIVATE RSA KEY PAIR
		
		
		
		/*
		byte[] certBytes = certBuilder.build(new JCESigner(privateKey, signatureAlgorithm)).getEncoded();
		CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
		X509Certificate certificate = (X509Certificate)certificateFactory.generateCertificate(new ByteArrayInputStream(certBytes));
		*/
	}

	public static boolean verifyCertificate(String userId,HttpSession session)
	{
		String filename = userId+"_public.key";
		try {
			
			ServletContext context = session.getServletContext();
			String realContextPath = context.getRealPath("/");
	        
	        FileInputStream filein = new FileInputStream(realContextPath+"/publickeys/"+filename);
			ObjectInputStream ois = new ObjectInputStream(filein);
			
			BigInteger modulus=(BigInteger) ois.readObject();
			BigInteger exponent = (BigInteger)ois.readObject();
			ois.close();
			
			RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, exponent);
			
			KeyFactory factory = KeyFactory.getInstance("RSA");
			PublicKey publickey = factory.generatePublic(keySpec);
			
			ServletContext cert_context = session.getServletContext();
			String cert_realContextPath = cert_context.getRealPath("/");
	        String certpath = cert_realContextPath+"/certificates/"+userId+"_cert.pem";
			
	        CertificateFactory cf = CertificateFactory.getInstance("X.509");
			FileInputStream inputStream = new FileInputStream(new File(certpath));
			X509Certificate certificate = (X509Certificate) cf.generateCertificate(inputStream);
			
			inputStream.close();
			certificate.checkValidity(new Date());
			certificate.verify(publickey);
			return true;
			
		} catch (Exception e) {
			
			
			e.printStackTrace();
		}
		return false;
	}
	
	public static void deleteCertificateAndPrivateKeyFile(String userId, HttpSession session)
	{
		
		ServletContext context = session.getServletContext();
        String realContextPath = context.getRealPath("/");
		
		String certificateFilepath = realContextPath+"/certificates/"+ userId +"_cert.pem";
        String privateKeyFilepath = realContextPath + "/privatekeys/" + userId + "_private.key";
        
        try{
        	File file_c = new File(certificateFilepath); 
            file_c.delete();
        }catch(Exception e){
        	e.printStackTrace();;
        }
        
        try{
        	File file_p = new File(privateKeyFilepath); 
            file_p.delete();
        }catch(Exception e){
        	e.printStackTrace();;
        }
        return;
	}
	
	public static boolean verifyPrivateKey(String userId, HttpSession session){
		
		try{
			
			ServletContext context = session.getServletContext();
	        String realContextPath = context.getRealPath("/");
			
	        String publicKeyFilepath = realContextPath+ "/publickeys/" + userId +"_public.key";
	        String privateKeyFilepath = realContextPath + "/privatekeys/" + userId + "_private.key";
	        
	        FileInputStream pbfile = new FileInputStream(publicKeyFilepath);
			ObjectInputStream pbois = new ObjectInputStream(pbfile);
			
			BigInteger modulus=(BigInteger) pbois.readObject();
			BigInteger exponent = (BigInteger)pbois.readObject();
			pbois.close();
			
			RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, exponent);
			
			KeyFactory factory = KeyFactory.getInstance("RSA");
			PublicKey publickey = factory.generatePublic(keySpec);
			
			////////////////////
			
			FileInputStream prfile = new FileInputStream(privateKeyFilepath);
			ObjectInputStream prois = new ObjectInputStream(prfile);
			
			BigInteger modulus_1=(BigInteger) prois.readObject();
			BigInteger exponent_1 = (BigInteger)prois.readObject();
			prois.close();
			
			RSAPrivateKeySpec P_keySpec = new RSAPrivateKeySpec(modulus_1, exponent_1);
						
			KeyFactory P_factory = KeyFactory.getInstance("RSA");
			PrivateKey privatekey = P_factory.generatePrivate(P_keySpec);
			
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publickey);
			byte[] cipherData = cipher.doFinal(userId.getBytes());
			
			cipher.init(Cipher.DECRYPT_MODE, privatekey);
			
			byte[] encipherData = cipher.doFinal(cipherData);
			
			logger.info("--1--");
			
			String en_str = new String(encipherData);
			
			logger.info("--1--");
			if(userId.equals(en_str)){
				logger.info(userId);
				logger.info(en_str);
				logger.info("--2--");
			}else{
				logger.info(userId);
				logger.info(en_str);
				logger.info("--4--");
				return false;
			}
			
			logger.info("--3--");
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}

}
