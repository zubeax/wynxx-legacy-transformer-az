package com.example.ims;

import com.ibm.ims.jdbc.IMSDataSource;
import java.sql.*;
import java.util.Properties;

public class ImsJdbcDemo {
    public static void main(String[] args) throws Exception {
        String custId    = (args.length > 0) ? args[0] : "CUST0001";
        String ordId     = (args.length > 1) ? args[1] : "ORD000001";
        String newStatus = (args.length > 2) ? args[2] : "CLOSED";
        try (Connection conn = openConnection()) {
            conn.setAutoCommit(false);
            readCustomer(conn, custId);
            readOrdersForCustomer(conn, custId);
            updateOrderStatus(conn, custId, ordId, newStatus);
            conn.commit();
            System.out.println("Committed updates.");
        }
    }

    private static Connection openConnection() throws SQLException {
        String host      = envOr("IMS_HOST", "imsconnect.example.com");
        int    port      = Integer.parseInt(envOr("IMS_PORT", "8888"));
        String datastore = envOr("IMS_DATASTORE", "IMSDATA");
        String psb       = envOr("IMS_PSB", "CUSTORDPSB");
        String user      = envOr("IMS_USER", "USER001");
        String pass      = envOr("IMS_PASS", "password");

        IMSDataSource ds = new IMSDataSource();
        ds.setDriverType(IMSDataSource.DRIVER_TYPE_4);
        ds.setDatastoreServer(host);
        ds.setPortNumber(port);
        ds.setDatastoreName(datastore);
        ds.setDatabaseName(psb);
        ds.setUser(user);
        ds.setPassword(pass);
        Properties extras = new Properties();
        extras.setProperty("ssaOptimization", "true");
        ds.setProperties(extras);
        return ds.getConnection();
    }

    private static String envOr(String k, String d) {
        String v = System.getenv(k);
        return (v == null || v.isEmpty()) ? d : v;
    }

    private static void readCustomer(Connection conn, String custId) throws SQLException {
        String sql = "SELECT CUSTID, CUSTNAME, ADDRESS FROM CUSTOMER WHERE CUSTID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, custId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.printf("Customer %s → %s, %s%n", rs.getString(1), rs.getString(2), rs.getString(3));
                } else {
                    System.out.println("Customer not found: " + custId);
                }
            }
        }
    }

    private static void readOrdersForCustomer(Connection conn, String custId) throws SQLException {
        String sql = "SELECT ORDID, ORDDATE, AMOUNT, STATUS FROM "ORDER" WHERE CUSTOMER_CUSTID = ? ORDER BY ORDID";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, custId);
            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("Orders for " + custId + ":");
                while (rs.next()) {
                    System.out.printf("  %s  %s  %s  %s%n", rs.getString(1), rs.getString(2), rs.getBigDecimal(3), rs.getString(4));
                }
            }
        }
    }

    private static void updateOrderStatus(Connection conn, String custId, String ordId, String newStatus) throws SQLException {
        String sql = "UPDATE "ORDER" SET STATUS = ? WHERE CUSTOMER_CUSTID = ? AND ORDID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setString(2, custId);
            ps.setString(3, ordId);
            int rows = ps.executeUpdate();
            System.out.println("Updated ORDER rows: " + rows);
        }
    }
}
