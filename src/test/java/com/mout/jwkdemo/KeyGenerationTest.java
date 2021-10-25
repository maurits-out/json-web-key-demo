package com.mout.jwkdemo;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.JWKGenerator;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class KeyGenerationTest {

    @Test
    void generateRSAKeyPair() throws JOSEException {
        JWKGenerator<RSAKey> generator = new RSAKeyGenerator(2048)
                .keyUse(KeyUse.SIGNATURE)
                .keyIDFromThumbprint(true);
        RSAKey key = generator.generate();
        System.out.println(key);
    }
}
