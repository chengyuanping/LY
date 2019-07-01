package com.leyou.test;

import com.leyou.auth.entity.UserInfo;
import com.leyou.auth.utils.JwtUtils;
import com.leyou.auth.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

public class JwtTest {

     private  static  final  String pubKeyPath="D:/tmp/rsa/rsa.pub";
     private  static  final  String priKeyPath="D:/tmp/rsa/rsa.pri";

     private PublicKey publicKey;
     private PrivateKey privateKey;

    /*@Test
    public void testRsa() throws Exception {

       RsaUtils.generateKey(pubKeyPath,priKeyPath,"1234");
    }*/
    @Before
    public  void testGetRsa() throws Exception {
        publicKey= RsaUtils.getPublicKey(pubKeyPath);
        privateKey= RsaUtils.getPrivateKey(priKeyPath);

    }
   @Test
    public  void  testGenerateToken() throws Exception {
        String token = JwtUtils.generateToken(new UserInfo(666L, "tom"), privateKey, 5);
        System.out.println(token);
        //eyJhbGciOiJSUzI1NiJ9.eyJpZCI6NjY2LCJ1c2VybmFtZSI6InRvbSIsImV4cCI6MTU2MTk2MzcxNX0.jUrmYfHYXKZwP-mAHi3MtlqpjCeNRrQQ-Co0_2WoK8fFqundRmKL7I3N9bAl8vPfq7dKm7WvA_Y50QlMLWBU0oOewPjmVMmygulFcyHbVi7I6FWEaWCRjy2g586fe0YPdXIydkZzKTzzkPbHoDE6pSQpfyBPbscko1f6Syqwumc
    }

    @Test
    public  void testParseToken() throws Exception {
          String token="eyJhbGciOiJSUzI1NiJ9.eyJpZCI6NjY2LCJ1c2VybmFtZSI6InRvbSIsImV4cCI6MTU2MTk2MzcxNX0.jUrmYfHYXKZwP-mAHi3MtlqpjCeNRrQQ-Co0_2WoK8fFqundRmKL7I3N9bAl8vPfq7dKm7WvA_Y50QlMLWBU0oOewPjmVMmygulFcyHbVi7I6FWEaWCRjy2g586fe0YPdXIydkZzKTzzkPbHoDE6pSQpfyBPbscko1f6Syqwumc";
          UserInfo userInfo = JwtUtils.getInfoFromToken(token, publicKey);
          System.out.println(userInfo.getUsername());
    }

}
