package com.mout.jwkdemo;

import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.JWKSet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;

import static com.nimbusds.jose.jwk.ECKey.parse;
import static java.nio.file.Files.readString;

@SpringBootApplication
public class JWKDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JWKDemoApplication.class, args);
	}

	@Bean
	JWKSet ecKey() throws IOException, ParseException {
		Resource resource = new ClassPathResource("/ec-key-material.json");
		ECKey key = parse(readString(Path.of(resource.getURI())));
		return new JWKSet(key);
	}
}
