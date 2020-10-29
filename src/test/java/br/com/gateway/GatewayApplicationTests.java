package br.com.gateway;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 8090)
class GatewayApplicationTests {

  @Autowired
  private WebTestClient webClient;

  @Test
  void contextLoads() {
    stubFor(WireMock.get(urlEqualTo("/get"))
        .willReturn(aResponse()
            .withBody("{\"msg\":\"Hello Gateway\"}")
            .withHeader("Content-Type", "application/json")));

    webClient
        .get().uri("/get")
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.msg").isEqualTo("Hello Gateway");
  }
}
