package com.brigada.booking_service.config;

import com.netflix.client.config.CommonClientConfigKey;
import com.netflix.client.config.IClientConfig;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClientName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

import static com.netflix.client.config.DefaultClientConfigImpl.DEFAULT_CONNECT_TIMEOUT;
import static com.netflix.client.config.DefaultClientConfigImpl.DEFAULT_READ_TIMEOUT;

@Configuration
public class RibbonConfiguration {

    @RibbonClientName
    private String name = "tickets-service";

    @Bean
    @ConditionalOnMissingBean
    public IClientConfig ribbonClientConfig() {
        RibbonClientConfig config = new RibbonClientConfig();
        config.loadProperties(this.name);
        config.set(CommonClientConfigKey.ConnectTimeout, DEFAULT_CONNECT_TIMEOUT);
        config.set(CommonClientConfigKey.ReadTimeout, DEFAULT_READ_TIMEOUT);
        config.set(CommonClientConfigKey.GZipPayload, false);
        config.set(CommonClientConfigKey.ServerListRefreshInterval, 5000);
        config.set(CommonClientConfigKey.NIWSServerListClassName, ConsulServerList.class.getName());
        return config;
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        try {
            SSLContext sslContext = new SSLContextBuilder()
                    .loadTrustMaterial((chain, authType) -> true).build();
            SSLConnectionSocketFactory socketFactory =
                    new SSLConnectionSocketFactory(sslContext);
            HttpClient httpClient = HttpClients.custom()
                    .setSSLSocketFactory(socketFactory).build();
            HttpComponentsClientHttpRequestFactory factory =
                    new HttpComponentsClientHttpRequestFactory(httpClient);
            factory.setConnectTimeout(6000);
            factory.setConnectionRequestTimeout(6000);
            return new RestTemplate(factory);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
