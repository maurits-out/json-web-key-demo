package com.mout.jwkdemo;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.List;

import static com.nimbusds.jose.jwk.JWK.parse;
import static java.nio.file.Files.readString;

@SpringBootApplication
public class JWKDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JWKDemoApplication.class, args);
	}

	@Bean
	JWKSet jwkSet() throws IOException, ParseException {
		return new JWKSet(List.of(readJWK("/ec-key-material.json"), readJWK("/rsa-key-material.json")));
	}

	private JWK readJWK(String path) throws ParseException, IOException {
		Resource resource = new ClassPathResource(path);
		return parse(readString(Path.of(resource.getURI())));
	}
}
