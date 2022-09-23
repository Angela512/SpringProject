package kr.spring.util;

import java.math.BigInteger;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.InitializingBean;

public class CipherTemplate implements InitializingBean{

   private String secretKey;
   private String algorithm;

   private SecretKeySpec keySpec;
   private Cipher cipher;

   public void setSecretKey(String secretKey) {
      this.secretKey = secretKey;
   }

   public void setAlgorithm(String algorithm) {
      this.algorithm = algorithm;
   }

   //스프링 컨텍스트의 빈 프로퍼티가 초기화된 이후에 호출되는 라이프사이클 제어용 메서드
   @Override
   public void afterPropertiesSet(){
      try{
         byte[] raw = stringToBytes(secretKey);
         keySpec = new SecretKeySpec(raw, algorithm);
         cipher = Cipher.getInstance(algorithm);
      }catch(Exception e){
         throw new RuntimeException("CipherTemplate initialization is failed", e);
      }
    }
   //암호화
   public String encrypt(String str){
      try{
         byte[] encrypted = null;
         synchronized(cipher){
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            encrypted = cipher.doFinal(str.getBytes());
         }
         return new String(Hex.encodeHex(encrypted));
      }catch(Exception e){
         throw new RuntimeException("CipherTemplate encryption is failed", e);
      }
   }
   //복호화
   public String decrypt(String encStr){
      try{
         byte[] encrypted = null;
         encrypted = Hex.decodeHex(encStr.toCharArray());

         byte[] decrypted = null;
         synchronized(cipher){
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            decrypted = cipher.doFinal(encrypted);
         }
         return new String(decrypted);
      }catch(Exception e){
         throw new RuntimeException("CipherTemplate decryption is failed", e);
      }
   }

   public static String generateRandomSecretKey(String algorithm) throws Exception{
      KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
      keyGen.init(128);
      SecretKey key = keyGen.generateKey();
      byte[] raw = key.getEncoded();
      return bytesToString(raw);
   }

   private static String bytesToString(byte[] bytes){
      byte[] b2 = new byte[bytes.length + 1];
      b2[0] = 1;
      System.arraycopy(bytes, 0, b2, 1, bytes.length);
      return new BigInteger(b2).toString(Character.MAX_RADIX);
   }

   private static byte[] stringToBytes(String str) {
      byte[] bytes = new BigInteger(str, Character.MAX_RADIX).toByteArray();
      return Arrays.copyOfRange(bytes, 1, bytes.length);
   }

   /*명령 프롬프트상에서 암호화 키를 생성하는 기능 제공
       최초의 한 번 실행해서 key를 생성한 후 생성한 키를 servlet-context.xml 설정시 생성한 키 명시
     ex)
     <beans:bean id="cipherAES" class="kr.spring.util.CipherTemplate">
		<beans:property name="algorithm" value="AES"/>
		                                              -> 생성한 키 기재
		<beans:property name="secretKey" value="qd6x1vtjck7nfq1rp053xv4jn"/>
	</beans:bean>
     */
   public static void main(String... args) throws Exception{
      if((args == null)||(args.length == 0)){
         System.out.println("One Parameter(Cipher algorithm name) is required - (Example) AES");
         return;
      }
      System.out.println("Generated Key : "+generateRandomSecretKey(args[0]));
   }
}