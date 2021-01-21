package am.learning.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Value("${security.oauth2.client.id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;

    @Value("${security.oauth2.client.access-token-validity-seconds}")
    private int accessTokenValiditySeconds;

    @Value("${security.oauth2.client.authorized-grant-types}")
    private String[] grantTypes;

    @Value("${security.oauth2.client.scope}")
    private String[] scope;


    @Autowired
    private TokenEnhancer customTokenEnhancer;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public TokenStore tokenStore;

    @Autowired
    private UserDetailsService userDetailsServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(clientId)
                .secret(passwordEncoder.encode(clientSecret))
                .scopes(scope)
                .accessTokenValiditySeconds(accessTokenValiditySeconds)
                .authorizedGrantTypes(grantTypes);
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenStore(tokenStore)
                .tokenEnhancer(customTokenEnhancer)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsServiceImpl);
    }

}
