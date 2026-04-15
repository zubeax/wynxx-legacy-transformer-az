package com.example.ims.web;

import com.example.ims.model.Customer;
import com.example.ims.model.Order;
import com.example.ims.service.ImsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ImsController {
  private final ImsService ims;
  public ImsController(ImsService ims) { this.ims = ims; }

  @GetMapping("/customers/{custId}")
  public ResponseEntity<?> getCustomer(@PathVariable String custId) throws SQLException {
    Customer c = ims.getCustomer(custId);
    return (c == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(c);
  }

  @GetMapping("/customers/{custId}/orders")
  public List<Order> getOrders(@PathVariable String custId) throws SQLException {
    return ims.getOrdersForCustomer(custId);
  }

  @PutMapping("/customers/{custId}")
  public Map<String,Object> updateCustomer(@PathVariable String custId, @RequestBody Map<String,String> body)
      throws SQLException {
    int rc = ims.updateCustomer(custId, body.get("name"), body.get("address"));
    return Map.of("updatedRows", rc);
  }

  @PutMapping("/customers/{custId}/orders/{ordId}/status")
  public Map<String,Object> updateOrderStatus(@PathVariable String custId, @PathVariable String ordId,
                                              @RequestBody Map<String,String> body) throws SQLException {
    int rc = ims.updateOrderStatus(custId, ordId, body.get("status"));
    return Map.of("updatedRows", rc);
  }
}
