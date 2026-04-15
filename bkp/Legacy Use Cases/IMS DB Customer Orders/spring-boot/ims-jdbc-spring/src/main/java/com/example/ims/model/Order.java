package com.example.ims.model;

import java.math.BigDecimal;

public class Order {
  private String ordId;
  private String ordDate;
  private BigDecimal amount;
  private String status;
  public String getOrdId() { return ordId; }
  public void setOrdId(String ordId) { this.ordId = ordId; }
  public String getOrdDate() { return ordDate; }
  public void setOrdDate(String ordDate) { this.ordDate = ordDate; }
  public BigDecimal getAmount() { return amount; }
  public void setAmount(BigDecimal amount) { this.amount = amount; }
  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }
}
