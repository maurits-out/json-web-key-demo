package com.mout.jwkdemo.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.factories.DefaultJWSSignerFactory;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.OK;

@Component
@Slf4j
public class SignedResponseEntityFactory {

    private final JWSSigner signer;
    private final String kid;
    private final ObjectMapper mapper = new ObjectMapper();

    public SignedResponseEntityFactory(JWKSet jwkSet, @Value("${signing-kid}") String kid) throws JOSEException {
        this.kid = kid;
        this.signer = createJwsSigner(jwkSet, kid);
    }

    public ResponseEntity<byte[]> create(Object object) {
        return create(object, OK);
    }

    private ResponseEntity<byte[]> create(Object object, HttpStatus httpStatus) {
        try {
            byte[] bytes = mapper.writeValueAsBytes(object);

            String detachedJws = createDetachedJws(bytes);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("x-jws-signature", detachedJws);
            responseHeaders.setContentType(MediaType.APPLICATION_JSON);

            return ResponseEntity.status(httpStatus)
                    .headers(responseHeaders)
                    .body(bytes);
        } catch (JsonProcessingException | JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    private String createDetachedJws(byte[] bytes) throws JOSEException {
        JWSObject jwsObject = new JWSObject(
                new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(kid).build(),
                new Payload(bytes));
        jwsObject.sign(signer);
        return jwsObject.serialize(true);
    }

    private JWSSigner createJwsSigner(JWKSet jwkSet, String kid) throws JOSEException {
        JWK signingKey = jwkSet.getKeyByKeyId(kid);
        DefaultJWSSignerFactory factory = new DefaultJWSSignerFactory();
        return factory.createJWSSigner(signingKey);
    }
}
