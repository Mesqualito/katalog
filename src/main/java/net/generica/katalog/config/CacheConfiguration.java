package net.generica.katalog.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(net.generica.katalog.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(net.generica.katalog.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(net.generica.katalog.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(net.generica.katalog.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(net.generica.katalog.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(net.generica.katalog.domain.Sprache.class.getName(), jcacheConfiguration);
            cm.createCache(net.generica.katalog.domain.Gruppe.class.getName(), jcacheConfiguration);
            cm.createCache(net.generica.katalog.domain.Gruppe.class.getName() + ".singles", jcacheConfiguration);
            cm.createCache(net.generica.katalog.domain.Gruppe.class.getName() + ".bezeichnungs", jcacheConfiguration);
            cm.createCache(net.generica.katalog.domain.Gruppe.class.getName() + ".ausdrucks", jcacheConfiguration);
            cm.createCache(net.generica.katalog.domain.Wort.class.getName(), jcacheConfiguration);
            cm.createCache(net.generica.katalog.domain.Wort.class.getName() + ".einzelworts", jcacheConfiguration);
            cm.createCache(net.generica.katalog.domain.Wort.class.getName() + ".worts", jcacheConfiguration);
            cm.createCache(net.generica.katalog.domain.Wort.class.getName() + ".bezeichnungs", jcacheConfiguration);
            cm.createCache(net.generica.katalog.domain.Wort.class.getName() + ".ausdrucks", jcacheConfiguration);
            cm.createCache(net.generica.katalog.domain.Bezeichnung.class.getName(), jcacheConfiguration);
            cm.createCache(net.generica.katalog.domain.Bezeichnung.class.getName() + ".einzelworts", jcacheConfiguration);
            cm.createCache(net.generica.katalog.domain.Ausdruck.class.getName(), jcacheConfiguration);
            cm.createCache(net.generica.katalog.domain.Ausdruck.class.getName() + ".einzelworts", jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
