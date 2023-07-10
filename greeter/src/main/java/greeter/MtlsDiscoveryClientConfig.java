package greeter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.configuration.SSLContextFactory;
import org.springframework.cloud.configuration.TlsProperties;
import org.springframework.cloud.netflix.eureka.http.EurekaClientHttpRequestFactorySupplier;
import org.springframework.cloud.netflix.eureka.http.RestTemplateDiscoveryClientOptionalArgs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Configuration
public class MtlsDiscoveryClientConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(MtlsDiscoveryClientConfig.class);

    @Bean
    public RestTemplateDiscoveryClientOptionalArgs restTemplateDiscoveryClientOptionalArgs(TlsProperties tlsProperties, EurekaClientHttpRequestFactorySupplier eurekaClientHttpRequestFactorySupplier) throws GeneralSecurityException, IOException {
        LOGGER.info("Tls Configuration: enabled - {}, keystore - {}, truststore - {}", tlsProperties.isEnabled(), tlsProperties.getKeyStore(), tlsProperties.getTrustStore());

        var args = new RestTemplateDiscoveryClientOptionalArgs(eurekaClientHttpRequestFactorySupplier);
        if (tlsProperties.isEnabled()) {
            SSLContextFactory factory = new SSLContextFactory(tlsProperties);
            args.setSSLContext(factory.createSSLContext());
        }

        return args;
    }
}
