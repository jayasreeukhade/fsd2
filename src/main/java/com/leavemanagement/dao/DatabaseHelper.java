package com.leavemanagement.dao;

import com.leavemanagement.model.Employee;
import com.leavemanagement.model.LeaveRequest;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class DatabaseHelper implements ServletContextListener {

    // =========================================================================
    //  DATABASE CONFIGURATION - CHANGE YOUR MYSQL PASSWORD & SETTINGS HERE
    // =========================================================================
    public static final boolean USE_MYSQL = true; // Set to true for MySQL, false.
    // for embedded H2

    // MySQL Settings:
    public static final String MYSQL_URL  = "jdbc:mysql://localhost:3306/leave_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true";
    public static final String MYSQL_USER = "root";
    public static final String MYSQL_PASS = "jaya@2297"; // <-- CHANGE YOUR MYSQL PASSWORD HERE!

    // =========================================================================

    private static volatile boolean initialized = false;

    private static String getDbUrl() {
        if (USE_MYSQL) {
            return MYSQL_URL;
        }
        String dbPath = System.getProperty("leave.db.path");
        if (dbPath != null && !dbPath.trim().isEmpty()) {
            return "jdbc:h2:" + dbPath + ";DB_CLOSE_DELAY=-1";
        }
        String userHome = System.getProperty("user.home");
        File dir = new File(userHome, ".leavemanagement");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String path = new File(dir, "leavedb").getAbsolutePath().replace("\\", "/");
        return "jdbc:h2:" + path + ";DB_CLOSE_DELAY=-1";
    }

    private static String getDbUser() {
        return USE_MYSQL ? MYSQL_USER : "sa";
    }

    private static String getDbPass() {
        return USE_MYSQL ? MYSQL_PASS : "";
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        initDatabase();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {}

    public static synchronized void initDatabase() {
        if (initialized) return;
        try {
            if (USE_MYSQL) {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } else {
                Class.forName("org.h2.Driver");
            }
            createTables();
            insertDefaultData();
            initialized = true;
            System.out.println("Leave Management Database initialized successfully! URL: " + getDbUrl());
        } catch (Exception e) {
            System.err.println("Database initialization failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if (!initialized) {
            initDatabase();
        }
        return DriverManager.getConnection(getDbUrl(), getDbUser(), getDbPass());
    }

    private static void createTables() {
        String employeeTable = "CREATE TABLE IF NOT EXISTS employees ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(100) NOT NULL, "
                + "email VARCHAR(100) UNIQUE NOT NULL, "
                + "password VARCHAR(100) NOT NULL, "
                + "department VARCHAR(50) NOT NULL, "
                + "role VARCHAR(20) DEFAULT 'employee')";

        String leaveTable = "CREATE TABLE IF NOT EXISTS leave_requests ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "employee_id INT NOT NULL, "
                + "leave_type VARCHAR(30) NOT NULL, "
                + "start_date DATE NOT NULL, "
                + "end_date DATE NOT NULL, "
                + "reason VARCHAR(500), "
                + "status VARCHAR(20) DEFAULT 'Pending', "
                + "applied_date DATE, "
                + "admin_remarks VARCHAR(300), "
                + "FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE)";

        try (Connection conn = DriverManager.getConnection(getDbUrl(), getDbUser(), getDbPass());
             Statement stmt = conn.createStatement()) {
            stmt.execute(employeeTable);
            stmt.execute(leaveTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertDefaultData() {
        // Default Admin
        String checkAdmin = "SELECT COUNT(*) FROM employees WHERE email = 'admin@company.com'";
        String insertAdmin = "INSERT INTO employees (name, email, password, department, role) VALUES ('System Administrator', 'admin@company.com', 'admin123', 'Management', 'admin')";

        try (Connection conn = DriverManager.getConnection(getDbUrl(), getDbUser(), getDbPass());
             Statement stmt = conn.createStatement()) {

            ResultSet rsAdmin = stmt.executeQuery(checkAdmin);
            if (rsAdmin.next() && rsAdmin.getInt(1) == 0) {
                stmt.execute(insertAdmin);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---- Employee Operations ----

    public static Employee authenticate(String email, String password) {
        String sql = "SELECT * FROM employees WHERE email = ? AND password = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapEmployee(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean registerEmployee(String name, String email, String password, String department) {
        String sql = "INSERT INTO employees (name, email, password, department, role) VALUES (?, ?, ?, ?, 'employee')";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, department);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees ORDER BY CASE WHEN role = 'admin' THEN 0 ELSE 1 END, name ASC";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapEmployee(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ---- Leave Operations ----

    public static boolean applyLeave(int employeeId, String leaveType, String startDate, String endDate, String reason) {
        String sql = "INSERT INTO leave_requests (employee_id, leave_type, start_date, end_date, reason, applied_date) VALUES (?, ?, ?, ?, ?, CURRENT_DATE)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, employeeId);
            ps.setString(2, leaveType);
            ps.setDate(3, java.sql.Date.valueOf(startDate));
            ps.setDate(4, java.sql.Date.valueOf(endDate));
            ps.setString(5, reason);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<LeaveRequest> getLeavesByEmployee(int employeeId) {
        List<LeaveRequest> list = new ArrayList<>();
        String sql = "SELECT lr.*, e.name AS emp_name, e.department AS emp_dept "
                + "FROM leave_requests lr JOIN employees e ON lr.employee_id = e.id "
                + "WHERE lr.employee_id = ? ORDER BY lr.id DESC";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapLeaveRequest(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<LeaveRequest> getAllLeaves() {
        List<LeaveRequest> list = new ArrayList<>();
        String sql = "SELECT lr.*, e.name AS emp_name, e.department AS emp_dept "
                + "FROM leave_requests lr JOIN employees e ON lr.employee_id = e.id "
                + "ORDER BY CASE WHEN lr.status = 'Pending' THEN 0 ELSE 1 END, lr.id DESC";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapLeaveRequest(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<LeaveRequest> getPendingLeaves() {
        List<LeaveRequest> list = new ArrayList<>();
        String sql = "SELECT lr.*, e.name AS emp_name, e.department AS emp_dept "
                + "FROM leave_requests lr JOIN employees e ON lr.employee_id = e.id "
                + "WHERE lr.status = 'Pending' ORDER BY lr.id ASC";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapLeaveRequest(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean updateLeaveStatus(int leaveId, String status, String remarks) {
        String sql = "UPDATE leave_requests SET status = ?, admin_remarks = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setString(2, remarks);
            ps.setInt(3, leaveId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean cancelLeave(int leaveId, int employeeId) {
        String sql = "DELETE FROM leave_requests WHERE id = ? AND employee_id = ? AND status = 'Pending'";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, leaveId);
            ps.setInt(2, employeeId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int countByStatus(String status) {
        String sql = "SELECT COUNT(*) FROM leave_requests WHERE status = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int countByStatusForEmployee(int employeeId, String status) {
        String sql = "SELECT COUNT(*) FROM leave_requests WHERE employee_id = ? AND status = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, employeeId);
            ps.setString(2, status);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // ---- Mappers ----

    private static Employee mapEmployee(ResultSet rs) throws SQLException {
        Employee e = new Employee();
        e.setId(rs.getInt("id"));
        e.setName(rs.getString("name"));
        e.setEmail(rs.getString("email"));
        e.setPassword(rs.getString("password"));
        e.setDepartment(rs.getString("department"));
        e.setRole(rs.getString("role"));
        return e;
    }

    private static LeaveRequest mapLeaveRequest(ResultSet rs) throws SQLException {
        LeaveRequest lr = new LeaveRequest();
        lr.setId(rs.getInt("id"));
        lr.setEmployeeId(rs.getInt("employee_id"));
        lr.setEmployeeName(rs.getString("emp_name"));
        lr.setDepartment(rs.getString("emp_dept"));
        lr.setLeaveType(rs.getString("leave_type"));
        lr.setStartDate(String.valueOf(rs.getDate("start_date")));
        lr.setEndDate(String.valueOf(rs.getDate("end_date")));
        lr.setReason(rs.getString("reason"));
        lr.setStatus(rs.getString("status"));
        Date appDate = rs.getDate("applied_date");
        lr.setAppliedDate(appDate != null ? String.valueOf(appDate) : "-");
        lr.setAdminRemarks(rs.getString("admin_remarks"));
        return lr;
    }
}
