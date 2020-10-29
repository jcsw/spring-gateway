package br.com.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.endpoint.RefreshEndpoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Profile("!default")
@Configuration
@EnableScheduling
public class RefreshContextConfig {

  @Autowired
  private RefreshEndpoint refreshEndpoint;

  @Scheduled(fixedDelay = 300000, initialDelay = 300000)
  public void refreshContextPeriodically() {
    refreshEndpoint.refresh();
  }
}
