package br.com.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

@Configuration
public class LogFilterConfiguration {

  private final Logger log = LoggerFactory.getLogger(LogFilterConfiguration.class);

  @Bean
  public GlobalFilter filter() {
    return (exchange, chain) -> chain.filter(exchange)
        .then(Mono.fromRunnable(() -> {
          ServerHttpRequest request = exchange.getRequest();
          ServerHttpResponse response = exchange.getResponse();
          log.info("request_path:{}", request.getPath());
          log.info("request_address:{}", request.getLocalAddress());
          log.info("response_status_code:{}", response.getStatusCode());
        }));
  }
}
