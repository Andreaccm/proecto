package helpers;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import org.apache.commons.codec.binary.Base64;

public class md5 {
	
    String cadena=null;
    private static String secretKey = "ProYecto1DeWebII";
    String cadenaEncriptada;
    
	 public String ecnode(String pwd1) {
	        String encriptacion = "";
	        try {
	            MessageDigest md5 = MessageDigest.getInstance("MD5");
	            byte[] llavePassword = md5.digest(secretKey.getBytes("utf-8"));
	            byte[] BytesKey = Arrays.copyOf(llavePassword, 24);
	            SecretKey key = new SecretKeySpec(BytesKey, "DESede");
	            Cipher cifrado = Cipher.getInstance("DESede");
	            cifrado.init(Cipher.ENCRYPT_MODE, key);
	            byte[] plainTextBytes = pwd1.getBytes("utf-8");
	            byte[] buf = cifrado.doFinal(plainTextBytes);
	            byte[] base64Bytes = Base64.encodeBase64(buf);
	            encriptacion = new String(base64Bytes);
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(null, "ERROR");
	        }
	        System.out.println(encriptacion);
	        return encriptacion;
	    }
  

  public String deecnode(String pwd1) {
 	 String cadena2="";
	        try {
	            byte[] message = Base64.decodeBase64(pwd1.getBytes("utf-8"));
	            MessageDigest md5 = MessageDigest.getInstance("MD5");
	            byte[] digestOfPassword = md5.digest(secretKey.getBytes("utf-8"));
	            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
	            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
	            Cipher decipher = Cipher.getInstance("DESede");
	            decipher.init(Cipher.DECRYPT_MODE, key);
	            byte[] plainText = decipher.doFinal(message);
	            cadena2 = new String(plainText, "UTF-8");

	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(null, "ERROR");
	        }
	        System.out.println(cadena2);
	        return cadena2;
	    }

}
