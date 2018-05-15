package com.springboot.provider;

import java.util.Arrays;

import org.apache.http.HttpRequest;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.TargetRequestFilter;
import au.com.dius.pact.provider.junit.VerificationReports;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;

@RunWith(PactRunner.class)
@PactFolder("../acceptedPacts")
@Provider("studentservice")
@VerificationReports({ "console", "markdown", "json" })
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE

)

public class ProviderApplicationTests {

	private int PORT = 8977;

	@TestTarget
	public final Target target = new HttpTarget("http", "localhost", PORT);

	private static ConfigurableWebApplicationContext application;

	@BeforeClass
	public static void start() {
		// System.out.println(todoServiceMock);
		application = (ConfigurableWebApplicationContext) SpringApplication.run(ProviderApplication.class);

	}

	@TargetRequestFilter
	public void printTheRequestHeaders(HttpRequest request) {
		Arrays.asList(request.getAllHeaders())
				.forEach(header -> System.out.println(header.getName() + "->" + header.getValue()));
	}

}
