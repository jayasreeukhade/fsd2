# Employee Leave Management System

A web-based **Employee Leave Management System** built with **pure Java Servlets** (backend Java classes with dynamic HTML response generation via `PrintWriter`), **no JSP**, and **lightweight CSS** (clean, zero-dependency embedded styles without heavy external frameworks).

---

## 🌟 Key Features

### 👤 Employee Portal
- **User Authentication**: Secure registration and login session management.
- **Personal Dashboard**: Overview of leave statistics (Pending, Approved, Rejected, Total).
- **Apply for Leave**:
  - Multiple leave categories: *Casual Leave*, *Sick Leave*, *Earned Leave*, *Maternity Leave*, *Paternity Leave*, *Unpaid Leave*.
  - Date picking with automated start/end date validation.
  - Reason specification.
- **Leave History**:
  - Detailed table showing all past and current applications.
  - Real-time status badges (*Pending*, *Approved*, *Rejected*).
  - Admin feedback/remarks display.
  - Ability to **Cancel** pending applications.

### 🛡️ Admin Portal
- **Admin Dashboard**:
  - High-level metrics: Total Registered Staff, Pending Approvals, Approved, Rejected.
  - One-click approval/rejection panel for pending requests with custom remarks.
- **Manage All Leaves**:
  - Full company-wide leave history view with filtering & actions.
- **Employee Directory**:
  - View all registered staff members, their departments, emails, and leave counts.

---

## 🏗️ Technology Stack

| Component | Technology |
|---|---|
| **Backend** | Java Servlets (Jakarta Servlet API 6.0 / Java EE 10) |
| **View Layer** | Pure Java classes (`HtmlTemplate`, `PrintWriter`), **NO JSP** |
| **Frontend Styling** | Lightweight, clean inline CSS (no heavy CSS/JS frameworks) |
| **Database** | Embedded H2 Database (zero external configuration needed) |
| **Build Tool** | Apache Maven |
| **Web Server** | Apache Tomcat 10.1 |

---

## 📂 Project Architecture

```
fsd2/
├── pom.xml                               # Maven project configuration (Servlet 6.0 & H2)
├── build.bat                             # One-click build script
├── deploy.bat                            # One-click deploy script to Tomcat 10.1
├── README.md                             # Documentation
└── src/
    └── main/
        ├── java/
        │   └── com/leavemanagement/
        │       ├── model/
        │       │   ├── Employee.java     # Employee entity model
        │       │   └── LeaveRequest.java # Leave request entity model
        │       ├── dao/
        │       │   └── DatabaseHelper.java # JDBC DAO & automatic table seeding
        │       ├── util/
        │       │   └── HtmlTemplate.java   # Lightweight UI & HTML generation utility
        │       └── servlet/
        │           ├── LoginServlet.java          # /login
        │           ├── RegisterServlet.java       # /register
        │           ├── LogoutServlet.java         # /logout
        │           ├── DashboardServlet.java      # /dashboard
        │           ├── ApplyLeaveServlet.java     # /apply-leave
        │           ├── LeaveHistoryServlet.java   # /leave-history
        │           ├── AdminDashboardServlet.java # /admin-dashboard
        │           ├── ManageLeavesServlet.java   # /manage-leaves
        │           └── EmployeesServlet.java      # /employees
        └── webapp/
            └── WEB-INF/
                └── web.xml               # Jakarta EE 6.0 deployment descriptor
```

---

## 🔑 Default Login Credentials

The database automatically initializes upon first start with the following pre-configured demo accounts:

| Role | Email | Password | Description |
|---|---|---|---|
| **Administrator** | `admin@company.com` | `admin123` | Full admin privileges (Approve/Reject leaves, view all staff) |
| **Employee** | `john@company.com` | `john123` | IT Department employee account |
| **Employee** | `jane@company.com` | `jane123` | HR Department employee account |

*You can also register brand new employee accounts via the **Register** page.*

---

## 🚀 How to Build and Run

### Option 1: Using the provided batch scripts (Fastest)
1. **Build the WAR file**:
   Double click `build.bat` or run:
   ```cmd
   .\build.bat
   ```
2. **Deploy to Tomcat 10.1**:
   Double click `deploy.bat` or run:
   ```cmd
   .\deploy.bat
   ```
3. Open your browser and visit:
   ```
   http://localhost:8080/leavemanagement/
   ```

---

### Option 2: Using Maven Command Line
```cmd
mvn clean package
```
Copy `target/leavemanagement.war` into your Tomcat `webapps/` folder:
```cmd
copy target\leavemanagement.war "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\"
```
Tomcat automatically expands and deploys the WAR.

