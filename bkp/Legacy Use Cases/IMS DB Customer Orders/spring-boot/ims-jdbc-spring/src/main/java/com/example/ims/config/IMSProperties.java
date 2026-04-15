package com.example.ims.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ims")
public class IMSProperties {
  private String host;
  private int port;
  private String datastore;
  private String psb;
  private String user;
  private String password;
  private boolean ssaOptimization = true;
  private String pcbName;
  private Tls tls = new Tls();

  public static class Tls {
    private boolean enabled;
    private String truststore;
    private String truststorePassword;
    private String keystore;
    private String keystorePassword;
    private String keystoreType = "JKS";
    private String protocol;
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    public String getTruststore() { return truststore; }
    public void setTruststore(String truststore) { this.truststore = truststore; }
    public String getTruststorePassword() { return truststorePassword; }
    public void setTruststorePassword(String truststorePassword) { this.truststorePassword = truststorePassword; }
    public String getKeystore() { return keystore; }
    public void setKeystore(String keystore) { this.keystore = keystore; }
    public String getKeystorePassword() { return keystorePassword; }
    public void setKeystorePassword(String keystorePassword) { this.keystorePassword = keystorePassword; }
    public String getKeystoreType() { return keystoreType; }
    public void setKeystoreType(String keystoreType) { this.keystoreType = keystoreType; }
    public String getProtocol() { return protocol; }
    public void setProtocol(String protocol) { this.protocol = protocol; }
  }
  public String getHost() { return host; }
  public void setHost(String host) { this.host = host; }
  public int getPort() { return port; }
  public void setPort(int port) { this.port = port; }
  public String getDatastore() { return datastore; }
  public void setDatastore(String datastore) { this.datastore = datastore; }
  public String getPsb() { return psb; }
  public void setPsb(String psb) { this.psb = psb; }
  public String getUser() { return user; }
  public void setUser(String user) { this.user = user; }
  public String getPassword() { return password; }
  public void setPassword(String password) { this.password = password; }
  public boolean isSsaOptimization() { return ssaOptimization; }
  public void setSsaOptimization(boolean ssaOptimization) { this.ssaOptimization = ssaOptimization; }
  public String getPcbName() { return pcbName; }
  public void setPcbName(String pcbName) { this.pcbName = pcbName; }
  public Tls getTls() { return tls; }
  public void setTls(Tls tls) { this.tls = tls; }
}
