package com.example.ims.service;

import com.example.ims.config.IMSProperties;
import com.example.ims.model.Customer;
import com.example.ims.model.Order;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImsService {
  private final DataSource ds;
  private final String pcb;

  public ImsService(DataSource ds, IMSProperties props) {
    this.ds = ds;
    this.pcb = props.getPcbName() != null ? props.getPcbName().trim() : "";
  }

  private String tCustomer() { return pcb.isEmpty() ? "CUSTOMER" : pcb + ".CUSTOMER"; }
  private String tOrder()    { return pcb.isEmpty() ? ""ORDER"" : pcb + "."ORDER""; }

  public Customer getCustomer(String custId) throws SQLException {
    String sql = "SELECT CUSTID, CUSTNAME, ADDRESS FROM " + tCustomer() + " WHERE CUSTID = ?";
    try (Connection c = ds.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
      ps.setString(1, custId);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          Customer cust = new Customer();
          cust.setCustId(rs.getString("CUSTID"));
          cust.setCustName(rs.getString("CUSTNAME"));
          cust.setAddress(rs.getString("ADDRESS"));
          return cust;
        }
        return null;
      }
    }
  }

  public List<Order> getOrdersForCustomer(String custId) throws SQLException {
    String sql = "SELECT ORDID, ORDDATE, AMOUNT, STATUS FROM " + tOrder() +
                 " WHERE CUSTOMER_CUSTID = ? ORDER BY ORDID";
    List<Order> out = new ArrayList<>();
    try (Connection c = ds.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
      ps.setString(1, custId);
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          Order o = new Order();
          o.setOrdId(rs.getString("ORDID"));
          o.setOrdDate(rs.getString("ORDDATE"));
          o.setAmount(rs.getBigDecimal("AMOUNT"));
          o.setStatus(rs.getString("STATUS"));
          out.add(o);
        }
      }
    }
    return out;
  }

  public int updateCustomer(String custId, String name, String address) throws SQLException {
    String sql = "UPDATE " + tCustomer() + " SET CUSTNAME = ?, ADDRESS = ? WHERE CUSTID = ?";
    try (Connection c = ds.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
      ps.setString(1, name);
      ps.setString(2, address);
      ps.setString(3, custId);
      return ps.executeUpdate();
    }
  }

  public int updateOrderStatus(String custId, String ordId, String newStatus) throws SQLException {
    String sql = "UPDATE " + tOrder() + " SET STATUS = ? WHERE CUSTOMER_CUSTID = ? AND ORDID = ?";
    try (Connection c = ds.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
      ps.setString(1, newStatus);
      ps.setString(2, custId);
      ps.setString(3, ordId);
      return ps.executeUpdate();
    }
  }
}
