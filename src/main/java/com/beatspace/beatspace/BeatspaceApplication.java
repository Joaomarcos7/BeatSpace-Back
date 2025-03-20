package com.beatspace.beatspace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching

public class BeatspaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeatspaceApplication.class, args);
	}



}
