/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signing;

/**
 *
 * @author Vaneeza Rana
 */
  

import java.security.KeyFactory;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
 
public class Signing {
     
    public static void main(String[] args) throws Exception {
        String msg = "Vaneeza Rana";
        String privkey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCbU8MrFfFRTjwqaLenWD4Won9YFjRZ5BUhNL3VVEx6PDyzsgzEN3SkEBF0woMrL4GwziWpH4x1/BxanvHdN89Uej/tvk3mSCGxFJJiP0Y4pvSnXU3Qh1+40TanF25GfK70WsYhtsh3iESY6EoVB1st//apv8w5bgr2FYUB9eCrF7O6fzDdiDb618vvP/bBKQBuivbWo/06anuaNg7gbmkRNWptuH0D3w64Tztc+AKyTFttmHfzZlOBxuZDwaQb37xUac8JLihkK/zXpUqDV4IY6rrZoxKZtBJ7YvmrnxhANhaw/CvAJUGKg7IwfGEETqE8w4tX3nNLL4qI0VLy2v6jAgMBAAECggEALod4/38jOufkvg+AdHDKqJ0k6I4+QG9CpR/XfgAyqKtCw9GiYWT7W+o0UHW0NUuV3hDi1zbMLMvI8pyKd2owgW3dmKpZS5JwBUvUTfoGXxIkV75rgPwMgKCrboJmVlcjUa+fipnmk2gOpH9xMbtyZOU4TsFVvB5qyizIGz48fwOx7ZAG5983nu/IBaOtt2LgWJn3JF64JLkvZkM+2tQYZU/t5dK/AkmhKNHd/Me1rrP81laOiB7S+pTXM1KyUdMwHnB0tEsPEFJRl0/fugt656EQLcTd9cU1nEN9JlLJk9z+apT8XKUE37Nmr/9Btc25cRD8gIRHuhLlGLiptUvwgQKBgQDmR8KKKY1dHJzgVlOr/2uXxswaO/ynakzj6PenA/IObT+h4ce3pgkjKqKPEWGrKsxUv0BOA9TYu8Grgmb/pMQsv1nKB7J+0xCmKTQJ3tPpgwXY9jLtIzMSVUG9hxBixZ0THvtgh8rNG7eWhT9UuPhmc+LM5/f1t1W6M1tmw/41BQKBgQCsrOv0Nrd3n4q9AAv2HR2y/VyYnAmmTX2DQMC9AtY8Q+CrQx37jApzw0FYBdPbuQezFz7a4P9/CEQBsL4W8vemTX3uaS4BQWOAs249Yjq3bG1wZ3DT9HsCoia+1Nuyrgu8U133A1dOMLk/TUX/Su1LSG3Qxxnqt5klhAQuw/c1hwKBgDqHSKG3F+/vgsveBRw1KhKEe4G7d2bRtMNEhhVUAmS6Ta8GZK1/rNjcUD7OqyVHFnH/vgdWH/YKGppN5YHaTeYVqGuZ8zsayCODW2+ay/+Qxp6zTJRLGwLw2mmid36zPU4L1NZfDSN+hPXj3gnZjYD0uVNdLf+s3kQaI4QGEolFAoGBAJYC2C3ehz8GPHp7muiiXNXN5G60SHHQqjSnwYmy+Dl84qcGdPq+q6nsPE1jKq2dLoI4tKNkirJqlOI/ELVROTKh6cCAYbY3f2ElAKWVO9tz+BOJ2exZXbBo/G4W8BJnv8xJRL2+ik9kZtsGn8Ps7P/VGYoiHnLaM9LywMt79bzrAoGBAJrKvvTchJQ7pKtUzSItq5RTFQGNd2At8SukGn9AonEAuEXHp69w83yfUlwQWGuTPm3WA3gmkDqKSXkEI3YLUbWWMZuluUaLjhM+1/b7P04AJ1qSwSU7oenCyIk/IoMAQ4ElGw5Qg6ZVzDyq9oSjdY/xhPRKF8aR5SEnldfyyr+2";
        String Signature = signSHA256RSA(msg,privkey);
        System.out.println("Signature="+Signature);
    }
 
    private static String signSHA256RSA(String input, String privkey) throws Exception {
         
        String realPK = privkey.replaceAll("-----END PRIVATE KEY-----", "")
                             .replaceAll("-----BEGIN PRIVATE KEY-----", "")
                             .replaceAll("\n", "");
       byte[] x = Base64.getDecoder().decode(realPK);
       PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(x);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(kf.generatePrivate(spec));
        privateSignature.update(input.getBytes("UTF-8"));
        byte[] y = privateSignature.sign();
       
        return Base64.getEncoder().encodeToString(y);
        
    }
}
