package com.example.ims.config;

import com.ibm.ims.jdbc.IMSDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableConfigurationProperties(IMSProperties.class)
public class DataSourceConfig {
  @Bean
  public DataSource dataSource(IMSProperties props) {
    IMSDataSource ims = new IMSDataSource();
    ims.setDriverType(IMSDataSource.DRIVER_TYPE_4);
    ims.setDatastoreServer(props.getHost());
    ims.setPortNumber(props.getPort());
    if (props.getDatastore() != null) ims.setDatastoreName(props.getDatastore());
    ims.setDatabaseName(props.getPsb());
    ims.setUser(props.getUser());
    ims.setPassword(props.getPassword());
    Properties p = new Properties();
    p.setProperty("ssaOptimization", String.valueOf(props.isSsaOptimization()));
    ims.setProperties(p);
    if (props.getTls() != null && props.getTls().isEnabled()) {
      ims.setSslConnection(true);
      if (props.getTls().getTruststore() != null) {
        ims.setSslTrustStoreLocation(props.getTls().getTruststore());
        ims.setSslTrustStorePassword(props.getTls().getTruststorePassword());
      }
      if (props.getTls().getKeystore() != null) {
        ims.setSslKeyStoreLocation(props.getTls().getKeystore());
        ims.setSslKeyStorePassword(props.getTls().getKeystorePassword());
        ims.setSslKeyStoreType(props.getTls().getKeystoreType());
      }
      if (props.getTls().getProtocol() != null) {
        ims.setSslSecureSocketProtocol(props.getTls().getProtocol());
      }
    }
    HikariConfig hc = new HikariConfig();
    hc.setPoolName("ims-hikari-pool");
    hc.setMaximumPoolSize(10);
    hc.setMinimumIdle(2);
    hc.setDataSource(ims);
    return new HikariDataSource(hc);
  }
}
