package dev.srinathg.spring_security_example;


import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

public class JwtKeyMakerTest {

    @Test
    public void generateKey(){

        SecretKey key = Jwts.SIG.HS256.key().build();
        String encodedKey = DatatypeConverter.printHexBinary(key.getEncoded());
        System.out.println(encodedKey);
    }
}
