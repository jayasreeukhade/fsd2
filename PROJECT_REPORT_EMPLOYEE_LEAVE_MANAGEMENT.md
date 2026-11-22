# Employee Leave Management System: Design and Development of a Web-Based Leave Management System Using Java Servlets

**A Skill Oriented Course Web Application / Project Report**  
submitted in partial fulfillment of the requirements for the award of the Degree of  
**BACHELOR OF TECHNOLOGY**  
in  
**COMPUTER SCIENCE AND ENGINEERING**

**Submitted by:**  
**PHANITHAPU VENKATA SAI VISHNU**  
**Roll No: 24761A05V0**

**Under the esteemed guidance of:**  
**Mr. N. SrinivasaRao**  
*Sr. Assistant Professor*

---

**DEPARTMENT OF COMPUTER SCIENCE AND ENGINEERING**  
**LAKIREDDY BALIREDDY COLLEGE OF ENGINEERING (AUTONOMOUS)**  
*L.B. Reddy Nagar, Mylavaram, NTR District, Andhra Pradesh – 521230*  
**2024–2028**

---

### LAKIREDDY BALIREDDY COLLEGE OF ENGINEERING (AUTONOMOUS)
*Accredited by NAAC with ‘A’ Grade & NBA (Under Tier - I)*  
*An ISO 21001:2018, 14001:2015, 50001:2018 Certified Institution*  
*Approved by AICTE, New Delhi and Affiliated to JNTUK, Kakinada*  
*L.B. REDDY NAGAR, MYLAVARAM, NTR DIST., A.P. - 521230*

**Department of Computer Science and Engineering**

---

### CERTIFICATE

This is to certify that the Skill Oriented Course Web Application / Project entitled **“Employee Leave Management System: Design and Development of a Web-Based Leave Management System Using Java Servlets”** is being submitted by:

| Student Name | Roll Number |
| :--- | :--- |
| **PHANITHAPU VENKATA SAI VISHNU** | **24761A05V0** |

in partial fulfillment of the requirements for the award of the degree of **B.Tech in Computer Science and Engineering** from **Jawaharlal Nehru Technological University Kakinada**, and is a record of bonafide work carried out at **Lakireddy Balireddy College of Engineering (Autonomous)**.

The results embodied in this project report have not been submitted to any other university or institute for the award of any degree or diploma.

<br><br>

**INTERNAL EXAMINER** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; **EXTERNAL EXAMINER**

---

### ACKNOWLEDGEMENT

I would like to express my sincere gratitude to **Mr. N. SrinivasaRao**, Sr. Assistant Professor, Department of Computer Science and Engineering, for his constant encouragement, guidance, and constructive advice throughout the design and development of this Skill Oriented Course Web Application / Project.

I express my sincere thanks to **Dr. S. Nagarjuna Reddy**, Professor and Head of the Department of CSE, for his valuable suggestions and continuous cooperation. I also express my heartfelt gratitude to the college management and **Dr. K. Appa Rao**, Principal, for providing the necessary infrastructure, laboratories, and computing resources required to carry out this work successfully.

I am greatly indebted to all teaching and non-teaching staff members of the Department of Computer Science and Engineering for their direct and indirect support. Finally, I express my deepest love and gratitude to my parents and friends for their continuous moral support, understanding, and encouragement.

**PHANITHAPU VENKATA SAI VISHNU**  
Roll No: **24761A05V0**

---

### DECLARATION

I hereby declare that the project entitled **“Employee Leave Management System: Design and Development of a Web-Based Leave Management System Using Java Servlets”** has been carried out by me under the guidance of my supervisor. The work contained in this report is original to the best of my knowledge and has not been submitted to any other institute or university for the award of any degree or diploma.

I have adhered to the institute’s guidelines in preparing this project report. Concepts, methodologies, literature, and software libraries sourced from other works have been duly credited and cited in the references.

<br>

**Signature of the Student:**  
**PHANITHAPU VENKATA SAI VISHNU**  
Roll No: **24761A05V0**

---

### ABSTRACT

The **Employee Leave Management System (ELMS)** is an enterprise web-based application engineered to automate, streamline, and centralize the process of requesting, approving, and tracking employee leave within an organization. In traditional manual or paper-based systems, leave applications often suffer from administrative delays, lack of transparency, record duplication, and cumbersome manual record-keeping. The Employee Leave Management System overcomes these inefficiencies by establishing an automated, transparent, and auditable client-server platform connecting employees directly with administrative decision-makers.

The application backend is built entirely using **Java Servlets (Jakarta Servlet API 6.0)** running on **Apache Tomcat 10.1**. In strict accordance with robust enterprise architectural principles, the user interface is generated dynamically through dedicated Java presentation utility classes utilizing servlet response output streams, completely avoiding legacy JavaServer Pages (JSP) and eliminating overhead from heavy front-end CSS frameworks. Persistent relational data management is provided through **Java Database Connectivity (JDBC)** interfacing with a **MySQL 8.0** relational database management system.

The system incorporates role-based access control, distinguishing between **Employee** and **Administrator** privileges. The Employee portal enables staff members to register, securely sign in, submit leave applications across varied categories (Casual Leave, Sick Leave, Earned Leave, Maternity Leave, Paternity Leave, and Unpaid Leave), monitor approval statuses through intuitive real-time status badges, view administrative remarks, and cancel pending requests. The Administrator portal empowers managers with comprehensive analytical dashboards, one-click leave approvals or rejections accompanied by decision remarks, company-wide leave auditing, and a centralized staff directory. The project demonstrates an end-to-end, production-ready full-stack enterprise web application following pure Model-View-Controller (MVC) paradigms.

---

### LIST OF CONTENTS

| Chapter No. | Title | Page No. |
| :---: | :--- | :---: |
| **1.** | **INTRODUCTION** | **1** |
| | 1.1 Overview of the Project | 1 |
| | 1.2 Problem Statement | 2 |
| | 1.3 Objectives | 2 |
| | 1.4 Scope | 3 |
| | 1.5 Feasibility Study | 3 |
| **2.** | **TECHNOLOGIES USED** | **5** |
| | 2.1 HTML and Embedded Lightweight CSS | 5 |
| | 2.2 Core Java (JDK 17+) | 5 |
| | 2.3 Jakarta Servlets (Servlet API 6.0) | 6 |
| | 2.4 Java Database Connectivity (JDBC) and MySQL | 6 |
| | 2.5 Apache Maven and Apache Tomcat 10.1 | 7 |
| **3.** | **SYSTEM ANALYSIS AND DESIGN** | **8** |
| | 3.1 Functional Requirements | 8 |
| | 3.2 Non-Functional Requirements | 9 |
| | 3.3 System Architecture | 10 |
| | 3.4 Database Design | 11 |
| | 3.5 Use-Case Description | 12 |
| **4.** | **CODING AND IMPLEMENTATION** | **14** |
| | 4.1 Project Structure | 14 |
| | 4.2 Database Connection & Schema Management | 15 |
| | 4.3 User Authentication and Session Management | 17 |
| | 4.4 Employee Leave Application and Tracking | 19 |
| | 4.5 Admin Leave Review and Status Management | 21 |
| | 4.6 Employee Directory Management | 23 |
| **5.** | **RESULTS AND SCREENSHOTS** | **24** |
| **6.** | **TESTING** | **29** |
| **7.** | **CONCLUSION AND FUTURE ENHANCEMENTS** | **31** |
| | 7.1 Conclusion | 31 |
| | 7.2 Future Enhancements | 31 |
| **8.** | **REFERENCES** | **33** |
| | **APPENDIX A: CORE CODE EXAMPLES** | **34** |
| | **APPENDIX B: ABBREVIATIONS** | **38** |

---

### LIST OF ABBREVIATIONS

| Abbreviation | Expansion |
| :--- | :--- |
| **HTML** | HyperText Markup Language |
| **CSS** | Cascading Style Sheets |
| **JSP** | JavaServer Pages |
| **JDBC** | Java Database Connectivity |
| **SQL** | Structured Query Language |
| **DBMS** | Database Management System |
| **RDBMS** | Relational Database Management System |
| **HTTP** | HyperText Transfer Protocol |
| **HTTPS** | HyperText Transfer Protocol Secure |
| **MVC** | Model–View–Controller |
| **CRUD** | Create, Read, Update, Delete |
| **IDE** | Integrated Development Environment |
| **WAR** | Web Application Archive |
| **JDK** | Java Development Kit |
| **JRE** | Java Runtime Environment |
| **UI** | User Interface |
| **UX** | User Experience |
| **API** | Application Programming Interface |
| **PK** | Primary Key |
| **FK** | Foreign Key |
| **XSS** | Cross-Site Scripting |
| **DAO** | Data Access Object |
| **ELMS** | Employee Leave Management System |

---

# 1. INTRODUCTION

## 1.1 Overview of the Project
The **Employee Leave Management System (ELMS)** is an automated intranet web application designed to handle all aspects of employee leave processing within corporate, industrial, and academic organizations. In workplace ecosystems, maintaining accurate workforce availability and handling employee absence is vital to operational continuity, project delivery, and regulatory compliance.

The ELMS replaces slow, error-prone manual paper trails and fragmented email correspondence with a centralized, automated web workflow. Built entirely using modern **Java Servlet technology** on the backend and direct HTML streaming via Java classes on the presentation tier, the application ensures maximum reliability, clean architecture, and rapid response times without reliance on heavy client-side JavaScript frameworks or legacy JavaServer Pages (JSP).

## 1.2 Problem Statement
Traditional leave processing within many organizations involves physical paper application forms, manual signatures from managers, verbal requests, or unstructured email exchanges. This approach introduces significant problems:
1. **Time Consumption:** Applications take days to route between employees, department heads, and human resource personnel.
2. **Lack of Transparency:** Employees have no real-time insight into the review status of their applications or reasons for refusal.
3. **Record Inaccuracy:** Manual ledger entries are prone to calculation errors, missing dates, duplicate approvals, and lost papers.
4. **Administrative Overhead:** HR managers must manually calculate leave balances, compile department availability sheets, and maintain audit trails.

The Employee Leave Management System eliminates these challenges by providing real-time submission, instant status updates, secure administrative review mechanisms, and centralized relational database storage.

## 1.3 Objectives
The principal objectives of the Employee Leave Management System are:
- **Centralized Authentication:** Provide secure user registration, credential validation, and role-based session routing for Employees and Administrators.
- **Categorized Leave Application:** Allow employees to submit leave requests specifying leave type (Casual, Sick, Earned, Maternity, Paternity, Unpaid), date boundaries, and detailed justifications.
- **Real-Time Tracking & Badging:** Offer an intuitive dashboard where employees monitor leave progression through visual status indicators (*Pending*, *Approved*, *Rejected*).
- **Administrative Decision Workflow:** Empower administrators with interactive dashboards to inspect employee requests, make one-click approval or rejection decisions, and attach explanatory remarks.
- **Auditability & Cancellation:** Permit employees to cancel unresolved pending leaves while maintaining historical logs of all processed leaves.
- **Directory & Resource Oversight:** Enable management to review company-wide employee directories, department distributions, and collective leave statistics.

## 1.4 Scope
The project encompasses:
- Core employee self-service modules: account creation, login, session termination, dashboard metrics, leave application forms, and historical leave tracking.
- Administrative management modules: centralized executive dashboard, pending leave action panels, historical leave review, decision remarks recording, and staff directory inspection.
- Server-side data validation, relational persistence via JDBC, automatic database schema bootstrapping, and cross-site scripting (XSS) prevention.
- Out of scope: Third-party payroll integrations, automated email/SMS push gateways, biometric punch-clock synchronization, and enterprise single sign-on (SSO) protocols.

## 1.5 Feasibility Study

### Technical Feasibility
The platform is built on enterprise-grade, established technologies: Core Java (JDK 17+), Jakarta Servlet API 6.0, JDBC, MySQL 8.0, Apache Maven, and Apache Tomcat 10.1. All components run stably on standard operating systems (Windows, Linux, macOS) and require minimal hardware footprints, rendering the system technically robust and fully feasible.

### Economic Feasibility
The complete software stack comprises open-source and free technologies—Java SE, Apache Tomcat, MySQL Community Server, and Maven. Development, deployment, and testing require no commercial software licenses, making the system cost-effective for deployment across small, medium, and educational institutions.

### Operational Feasibility
The user interface adheres to clean, ergonomic, and uncluttered design standards. Forms feature intuitive dropdowns, HTML5 date selectors, and clear action buttons. Users with basic web browsing skills can navigate the employee and administrative portals without specialized training.

### Schedule Feasibility
The modular design of the application allowed iterative development divided into distinct milestones: database modeling, DAO implementation, authentication servlets, employee portals, administrative review workflows, testing, and documentation, ensuring completion well within academic project deadlines.

---

# 2. TECHNOLOGIES USED

## 2.1 HTML and Embedded Lightweight CSS
The user interface is generated through clean HTML markup rendered directly by server-side Java presentation components (`HtmlTemplate.java`). Rather than loading heavy external CSS frameworks such as Bootstrap, Tailwind, or complex JavaScript bundles that increase page weight and network latency, the application uses an optimized, embedded CSS styling strategy:
- Clean typography utilizing modern system font stacks (`Segoe UI`, `Tahoma`, `sans-serif`).
- Consistent color palettes conveying authority and hierarchy (navy headers `#1e293b`, royal blue accents `#2563eb`, green success indicators `#16a34a`, amber warnings `#d97706`, and red alerts `#dc2626`).
- Responsive, flexible layouts using CSS Flexbox for metric cards, forms, and data tables.
- Zero client-side external asset dependencies, ensuring immediate page load speeds and full offline intranet capability.

## 2.2 Core Java (JDK 17+)
Java serves as the foundation for all server-side application logic. Its strong typing, object-oriented principles, exception handling, and platform independence make it ideal for transactional enterprise web systems. Java 17+ LTS language features ensure high performance, security, and long-term maintainability.

## 2.3 Jakarta Servlets (Servlet API 6.0)
Jakarta Servlets act as controller components within the application. Servlets map incoming HTTP requests (`GET` and `POST`) to business logic methods, manage user sessions via `HttpSession`, validate submitted form parameters, coordinate data retrieval with DAO classes, and stream sanitized HTML markup back to the client browser. The project adheres to Servlet API 6.0 specifications compatible with modern application servers.

## 2.4 Java Database Connectivity (JDBC) and MySQL
Data persistence is handled by **MySQL 8.0** connected through the official **MySQL Connector/J 8.4.0** driver using standard Java Database Connectivity (JDBC):
- **PreparedStatements:** All SQL queries use parameterized `PreparedStatement` interfaces, completely preventing SQL Injection vulnerabilities.
- **Relational Integrity:** Foreign key constraints with cascading rules link leave requests to employee identities.
- **Dual-Engine Architecture:** The data layer supports native MySQL 8.0 as the primary production database while retaining transparent fallback compatibility with embedded H2 databases.

## 2.5 Apache Maven and Apache Tomcat 10.1
- **Apache Maven 3.9:** Serves as the build automation and dependency management tool, assembling compiled classes, web configuration files, and JDBC libraries into a standard Web Application Archive (`leavemanagement.war`).
- **Apache Tomcat 10.1:** An open-source, high-performance web server and servlet container implementing Jakarta Servlet 6.0 specifications, hosting the deployed application.

---

# 3. SYSTEM ANALYSIS AND DESIGN

## 3.1 Functional Requirements

### Employee Module:
1. **User Registration:** Visitors can register with their full name, email address, secure password, and department.
2. **User Login & Session Management:** Authenticated employees gain access to personal dashboards with session timeouts and secure logout.
3. **Leave Application:** Employees can apply for leaves by selecting leave types, choosing valid start and end dates, and supplying explanations.
4. **Dashboard Summaries:** Employees view live counters for Pending, Approved, and Rejected requests alongside recent submissions.
5. **Leave Tracking & History:** Employees can inspect the complete history of their leaves, view approval badges, and read administrative feedback.
6. **Application Cancellation:** Employees can cancel their own unresolved *Pending* requests with one click.

### Administrator Module:
1. **Executive Overview:** Administrators view organizational statistics: total registered staff, pending approvals, approved leaves, and rejected counts.
2. **Pending Request Review:** An action panel displays all pending leaves with immediate **Approve** and **Reject** buttons and custom remarks input fields.
3. **Company-Wide Leave Audit:** Administrators can browse, sort, and inspect all leave records across the organization.
4. **Employee Directory:** Administrators can browse all registered personnel, departmental distributions, and cumulative leave tallies.

## 3.2 Non-Functional Requirements
- **Security:** Strict separation of privileges between Employees and Admins; parameterized SQL queries to prevent SQL Injection; HTML escaping across all dynamic fields to eliminate Cross-Site Scripting (XSS); secure session invalidation.
- **Usability:** Clean visual presentation with clear status badges, responsive tabular layouts, and intuitive form controls requiring no user manuals.
- **Performance:** Direct servlet HTML rendering without JSP translation overhead guarantees sub-second page response times.
- **Reliability:** Automated schema generation and self-healing database initialization ensure zero deployment downtime.
- **Portability:** Standard Java EE WAR packaging guarantees immediate deployment to any compliant servlet container across Windows, Linux, or cloud environments.

## 3.3 System Architecture
The application employs a layered client-server architecture based on Model-View-Controller (MVC) principles:

```
[ Web Browser (Client Presentation Tier) ]
                 │
                 ▼  HTTP Requests (GET / POST)
[ Jakarta Servlet Controllers (Controller Tier) ]
   ├── LoginServlet / RegisterServlet / LogoutServlet
   ├── DashboardServlet / ApplyLeaveServlet / LeaveHistoryServlet
   └── AdminDashboardServlet / ManageLeavesServlet / EmployeesServlet
                 │
                 ▼  Business Logic & Parameter Validation
[ Model & Utility Tier ]
   ├── Employee.java / LeaveRequest.java (Entity Models)
   └── HtmlTemplate.java (Presentation Utility / Sanitization)
                 │
                 ▼  JDBC Calls (PreparedStatement)
[ Data Access Tier (DatabaseHelper.java) ]
                 │
                 ▼  SQL Queries / ResultSets
[ MySQL 8.0 Relational Database (leave_db) ]
```

## 3.4 Database Design
The relational schema comprises two core normalized entities in third normal form (3NF):

### 1. `employees` Table
Stores registered user credentials, profile information, and access privileges.

| Column Name | Data Type | Constraints | Description |
| :--- | :--- | :--- | :--- |
| `id` | INT | PRIMARY KEY, AUTO_INCREMENT | Unique employee identifier |
| `name` | VARCHAR(100) | NOT NULL | Full name of the employee |
| `email` | VARCHAR(100) | UNIQUE, NOT NULL | Login email address |
| `password` | VARCHAR(100) | NOT NULL | Account authentication password |
| `department` | VARCHAR(50) | NOT NULL | Assigned organizational department |
| `role` | VARCHAR(20) | DEFAULT 'employee' | Access role (`admin` or `employee`) |

### 2. `leave_requests` Table
Records leave applications, date durations, current review statuses, and administrative feedback.

| Column Name | Data Type | Constraints | Description |
| :--- | :--- | :--- | :--- |
| `id` | INT | PRIMARY KEY, AUTO_INCREMENT | Unique leave request identifier |
| `employee_id` | INT | NOT NULL, FK &rarr; `employees(id)` | Associated employee reference |
| `leave_type` | VARCHAR(30) | NOT NULL | Type of leave (Casual, Sick, etc.) |
| `start_date` | DATE | NOT NULL | Beginning date of leave |
| `end_date` | DATE | NOT NULL | Concluding date of leave |
| `reason` | VARCHAR(500) | NOT NULL | Employee justification |
| `status` | VARCHAR(20) | DEFAULT 'Pending' | Current status (`Pending`, `Approved`, `Rejected`) |
| `applied_date` | DATE | DEFAULT CURRENT_DATE | Date on which leave was submitted |
| `admin_remarks` | VARCHAR(300) | NULL | Explanatory note from administrator |

## 3.5 Use-Case Description
- **Primary Actors:**
  - **Employee:** An authenticated staff member who applies for leaves, monitors approval statuses, and manages personal requests.
  - **Administrator:** A privileged manager responsible for reviewing leave applications, approving or rejecting requests, and auditing workforce availability.
- **Main Scenarios:**
  - *Employee Applies for Leave:* Employee logs in &rarr; selects "Apply Leave" &rarr; specifies leave type, date range, and reason &rarr; system validates date order &rarr; request saved with status `Pending`.
  - *Administrator Reviews Leave:* Administrator logs in &rarr; opens Admin Dashboard &rarr; views pending request &rarr; enters optional remarks &rarr; clicks "Approve" or "Reject" &rarr; database updates status and remarks &rarr; employee dashboard reflects decision immediately.
  - *Employee Cancels Leave:* Employee opens Leave History &rarr; identifies a `Pending` request &rarr; clicks "Cancel" &rarr; system verifies ownership and status &rarr; record deleted safely.

---

# 4. CODING AND IMPLEMENTATION

## 4.1 Project Structure
The project follows standard Maven web application directory conventions:

```
fsd2/
├── pom.xml                                      # Build dependencies & WAR packaging
├── build.bat                                    # Automated build batch script
├── deploy.bat                                   # Automated deployment to Tomcat
├── README.md                                    # Setup & user documentation
└── src/
    └── main/
        ├── java/
        │   └── com/leavemanagement/
        │       ├── model/
        │       │   ├── Employee.java            # Employee entity class
        │       │   └── LeaveRequest.java        # Leave request entity class
        │       ├── dao/
        │       │   └── DatabaseHelper.java      # JDBC connection & data access layer
        │       ├── util/
        │       │   └── HtmlTemplate.java        # Dynamic HTML & lightweight CSS generator
        │       └── servlet/
        │           ├── LoginServlet.java         # /login - User authentication
        │           ├── RegisterServlet.java      # /register - Account registration
        │           ├── LogoutServlet.java        # /logout - Session invalidation
        │           ├── DashboardServlet.java     # /dashboard - Employee overview
        │           ├── ApplyLeaveServlet.java    # /apply-leave - Leave application
        │           ├── LeaveHistoryServlet.java  # /leave-history - Employee history & cancel
        │           ├── AdminDashboardServlet.java# /admin-dashboard - Admin action center
        │           ├── ManageLeavesServlet.java  # /manage-leaves - Company leave audit
        │           └── EmployeesServlet.java     # /employees - Staff directory
        └── webapp/
            └── WEB-INF/
                └── web.xml                      # Deployment descriptor (Jakarta EE 6.0)
```

## 4.2 Database Connection & Schema Management
The [`DatabaseHelper.java`](file:///c:/Users/Administrator/Desktop/fsd2/src/main/java/com/leavemanagement/dao/DatabaseHelper.java) class implements `ServletContextListener` and manages the database lifecycle. Upon servlet container startup, `contextInitialized()` creates the MySQL schema and tables automatically if they do not already exist, and initializes the default administrator account.

```java
public static Connection getConnection() throws SQLException {
    if (!initialized) {
        initDatabase();
    }
    return DriverManager.getConnection(getDbUrl(), getDbUser(), getDbPass());
}
```

Connection parameters are defined as constants at the head of the file:
- `MYSQL_URL`: `jdbc:mysql://localhost:3306/leave_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true`
- `MYSQL_USER`: `root`
- `MYSQL_PASS`: Configurable database password

## 4.3 Registration and Login
- **Registration ([`RegisterServlet.java`](file:///c:/Users/Administrator/Desktop/fsd2/src/main/java/com/leavemanagement/servlet/RegisterServlet.java)):** Accepts user details, validates that all fields are populated, enforces email uniqueness in the database, and creates an employee record with `role = 'employee'`.
- **Authentication ([`LoginServlet.java`](file:///c:/Users/Administrator/Desktop/fsd2/src/main/java/com/leavemanagement/servlet/LoginServlet.java)):** Queries the `employees` table using a `PreparedStatement`. Upon verification, an `HttpSession` is initialized with the authenticated user object. Role-based redirection automatically routes users:
  - `role.equals("admin")` &rarr; `admin-dashboard`
  - `role.equals("employee")` &rarr; `dashboard`
- **Logout ([`LogoutServlet.java`](file:///c:/Users/Administrator/Desktop/fsd2/src/main/java/com/leavemanagement/servlet/LogoutServlet.java)):** Terminates the session via `session.invalidate()` and returns the user to the login screen.

## 4.4 Employee Leave Application and Tracking
- **Application Submission ([`ApplyLeaveServlet.java`](file:///c:/Users/Administrator/Desktop/fsd2/src/main/java/com/leavemanagement/servlet/ApplyLeaveServlet.java)):** Processes leave submissions. The servlet validates that the end date does not precede the start date (`startDate.compareTo(endDate) <= 0`) before persisting the request with default status `Pending`.
- **Dashboard & Metrics ([`DashboardServlet.java`](file:///c:/Users/Administrator/Desktop/fsd2/src/main/java/com/leavemanagement/servlet/DashboardServlet.java)):** Queries `DatabaseHelper` for counts of pending, approved, and rejected leaves, rendering visual summary cards and a quick-view table of recent applications.
- **History & Cancellation ([`LeaveHistoryServlet.java`](file:///c:/Users/Administrator/Desktop/fsd2/src/main/java/com/leavemanagement/servlet/LeaveHistoryServlet.java)):** Displays an interactive table of all leaves filed by the logged-in user. Any request currently in `Pending` status displays a "Cancel" button, allowing safe removal.

## 4.5 Admin Leave Review and Status Management
- **Admin Dashboard ([`AdminDashboardServlet.java`](file:///c:/Users/Administrator/Desktop/fsd2/src/main/java/com/leavemanagement/servlet/AdminDashboardServlet.java)):** Displays organizational metrics alongside an actionable pending requests table. Each pending record provides inline input for optional remarks and immediate **Approve** and **Reject** buttons.
- **Company Management ([`ManageLeavesServlet.java`](file:///c:/Users/Administrator/Desktop/fsd2/src/main/java/com/leavemanagement/servlet/ManageLeavesServlet.java)):** Provides full administrative auditing across all historical records, supporting status updates with custom managerial remarks.

## 4.6 Employee Directory Management
- **Directory Inspection ([`EmployeesServlet.java`](file:///c:/Users/Administrator/Desktop/fsd2/src/main/java/com/leavemanagement/servlet/EmployeesServlet.java)):** Lists all registered personnel with their respective department, system role, pending leave counts, and approved leave totals, giving leadership an immediate high-level view of staff availability.

---

# 5. RESULTS AND SCREENSHOTS

The application was deployed and verified on **Apache Tomcat 10.1** on `http://localhost:8080/leavemanagement/`. The following sections detail each verified interface.

### 5.1 Login Interface (`/login`)
The entrance portal features a clean, focused login card where users authenticate with their registered email address and password. It includes a link to the registration page and a helper box showing administrator demo credentials (`admin@company.com` / `admin123`).

### 5.2 Registration Interface (`/register`)
Provides an intuitive onboarding form where new employees register their Full Name, Email, Password (minimum 4 characters), and Department from a standardized dropdown list (IT Development, Human Resources, Finance & Accounts, Marketing & Sales, Operations, Customer Support).

### 5.3 Employee Dashboard (`/dashboard`)
Serves as the employee's operational hub. It displays a welcome banner showing their department and email, four colored metric cards (Pending Requests in amber, Approved Leaves in green, Rejected Requests in red, and Total Applications in blue), a shortcut button to apply for leave, and a table of the 5 most recent leave applications.

### 5.4 Apply Leave Form (`/apply-leave`)
Presents an ergonomic form enabling employees to select from 6 distinct leave types:
- *Casual Leave (CL)*
- *Sick Leave (SL)*
- *Earned Leave (EL)*
- *Maternity Leave*
- *Paternity Leave*
- *Unpaid Leave / Loss of Pay*

Includes HTML5 date controls for Start Date and End Date, a multi-line justification textarea, and client/server validation ensuring valid date sequencing.

### 5.5 Leave History & Cancellation (`/leave-history`)
Displays a comprehensive tabular log of all personal leave requests showing leave type, duration, justification, applied date, status badge, and administrator feedback remarks. Active `Pending` applications feature an interactive red **Cancel** button with confirmation prompts.

### 5.6 Admin Dashboard (`/admin-dashboard`)
Provides executive management with high-level summaries: total registered personnel, pending reviews, approved requests, and rejected counts. An actionable review table lists all pending applications, employee details, dates, and justifications, equipped with inline remarks fields and **Approve** and **Reject** action triggers.

### 5.7 Manage All Leaves (`/manage-leaves`)
Offers full visibility over company-wide leave records with detailed status badges (*Approved*, *Pending*, *Rejected*), recorded administrative feedback, and audit timestamps.

### 5.8 Employee Directory (`/employees`)
Presents an administrative listing of all registered staff members, their departments, assigned privileges, and current leave statistics.

---

# 6. TESTING

Functional, integration, and security testing were conducted on the deployed application running against Apache Tomcat 10.1 and MySQL 8.0.

| Test Case ID | Test Scenario | Input / Action | Expected Result | Actual Result | Status |
| :---: | :--- | :--- | :--- | :--- | :---: |
| **TC-01** | User Registration | Submit valid employee details (Name, Email, Password, Department) | New employee account created; redirect to login with success alert | Account created in MySQL; success notification displayed | **PASSED** |
| **TC-02** | Duplicate Registration | Register with an email address that already exists | Registration rejected; friendly error message displayed | Rejected by database constraint; error banner shown | **PASSED** |
| **TC-03** | Valid Admin Login | Submit `admin@company.com` and `admin123` | Authentication succeeds; session starts; routed to `/admin-dashboard` | Successfully logged in; admin dashboard displayed | **PASSED** |
| **TC-04** | Valid Employee Login | Submit valid employee credentials | Authentication succeeds; session starts; routed to `/dashboard` | Successfully logged in; employee dashboard displayed | **PASSED** |
| **TC-05** | Invalid Login | Submit incorrect password or non-existent email | Authentication fails; redirected to login with error alert | Access denied; error message shown | **PASSED** |
| **TC-06** | Session Protection | Access `/dashboard` or `/admin-dashboard` directly without logging in | Unauthenticated request intercepted; redirected to `/login` | Access blocked; redirected to login | **PASSED** |
| **TC-07** | Role Authorization | Authenticated employee attempts direct access to `/admin-dashboard` | Non-admin access intercepted; redirected to employee `/dashboard` | Access denied to admin portal; redirected to employee dashboard | **PASSED** |
| **TC-08** | Apply Valid Leave | Submit leave with `Start Date <= End Date` and valid reason | Leave stored in MySQL with status `Pending`; success alert shown | Leave recorded in `leave_requests` table; success banner shown | **PASSED** |
| **TC-09** | Invalid Date Order | Submit leave where `Start Date > End Date` | Application rejected by server validation; error shown | Application rejected; error banner displayed | **PASSED** |
| **TC-10** | Admin Approve Leave | Admin clicks "Approve" with custom remarks on a pending leave | Status updated to `Approved`; remarks saved; dashboard reflects change | MySQL record updated to `Approved`; remarks saved | **PASSED** |
| **TC-11** | Admin Reject Leave | Admin clicks "Reject" with explanation remarks | Status updated to `Rejected`; remarks saved; dashboard reflects change | MySQL record updated to `Rejected`; remarks saved | **PASSED** |
| **TC-12** | Cancel Pending Leave | Employee clicks "Cancel" on a `Pending` leave application | Pending request removed from database; history table updated | Record deleted safely; cancellation alert displayed | **PASSED** |
| **TC-13** | User Logout | Click "Logout" in navigation bar | `HttpSession` invalidated; user redirected to `/login` | Session terminated; back button cannot access protected page | **PASSED** |

---

# 7. CONCLUSION AND FUTURE ENHANCEMENTS

## 7.1 Conclusion
The **Employee Leave Management System (ELMS)** successfully demonstrates an automated, secure, and user-friendly web solution for corporate and academic absence tracking. By utilizing pure **Java Servlets (Jakarta Servlet API 6.0)**, direct dynamic HTML streaming via Java classes, and **JDBC with MySQL 8.0**, the application achieves exceptional response performance and structural clarity without relying on legacy JSP files or bulky external CSS frameworks.

The system delivers distinct, role-based workflows for employees and administrators, eliminating paper handling, expediting decision turnarounds, and maintaining transparent, audit-ready records. The project satisfies all academic objectives of the Skill Oriented Course and stands as a complete, practical enterprise software implementation.

## 7.2 Future Enhancements
While the current application is fully functional and stable, the following features can be added in future iterations:
1. **Automated Notifications:** Integration of JavaMail API to send real-time email notifications to employees upon approval or rejection of their leaves.
2. **Leave Balance Quotas:** Implementation of annual leave quotas (e.g., 12 Casual Leaves, 10 Sick Leaves per year) with automatic balance deductions upon approval.
3. **Document Attachment:** Enabling file uploads allowing employees to attach medical certificates or supporting documents for sick or maternity leave requests.
4. **Calendar Visualization:** An interactive monthly organizational calendar displaying approved leaves to prevent department understaffing.
5. **Password Hashing (jBCrypt):** Upgrading plaintext database passwords to cryptographic hash digests (e.g., BCrypt / Argon2) with salt generation.
6. **PDF Report Generation:** Allowing HR administrators to export monthly absence summaries and audit logs directly to formatted PDF or Excel documents.

---

# 8. REFERENCES

1. Eclipse Foundation. *Jakarta Servlet Specification 6.0*. https://jakarta.ee/specifications/servlet/6.0/
2. Oracle Corporation. *Java SE 17 and Java Platform Documentation*. https://docs.oracle.com/en/java/javase/17/
3. Oracle Corporation. *Java Database Connectivity (JDBC) Guide*. https://docs.oracle.com/javase/tutorial/jdbc/
4. MySQL AB / Oracle Corporation. *MySQL 8.0 Reference Manual*. https://dev.mysql.com/doc/refman/8.0/en/
5. MySQL AB / Oracle Corporation. *MySQL Connector/J 8.4 Developer Guide*. https://dev.mysql.com/doc/connector-j/en/
6. Apache Software Foundation. *Apache Tomcat 10.1 Documentation*. https://tomcat.apache.org/tomcat-10.1-doc/
7. Apache Software Foundation. *Apache Maven Project Documentation*. https://maven.apache.org/guides/
8. OWASP Foundation. *Cross-Site Scripting (XSS) and SQL Injection Prevention Cheat Sheets*. https://cheatsheetseries.owasp.org/

---

# APPENDIX A: CORE CODE EXAMPLES

### A.1 Database Connection and Table Creation (`DatabaseHelper.java`)
```java
public static Connection getConnection() throws SQLException {
    if (!initialized) {
        initDatabase();
    }
    return DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASS);
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

    try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
        stmt.execute(employeeTable);
        stmt.execute(leaveTable);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
```

### A.2 User Authentication and Role-Based Redirection (`LoginServlet.java`)
```java
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String email = req.getParameter("email");
    String password = req.getParameter("password");

    Employee emp = DatabaseHelper.authenticate(email, password);
    if (emp != null) {
        HttpSession session = req.getSession(true);
        session.setAttribute("employee", emp);
        if ("admin".equals(emp.getRole())) {
            resp.sendRedirect("admin-dashboard");
        } else {
            resp.sendRedirect("dashboard");
        }
    } else {
        resp.sendRedirect("login?error=1");
    }
}
```

### A.3 Leave Application Submission with Date Validation (`ApplyLeaveServlet.java`)
```java
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    HttpSession session = req.getSession(false);
    if (session == null || session.getAttribute("employee") == null) {
        resp.sendRedirect("login");
        return;
    }
    Employee emp = (Employee) session.getAttribute("employee");

    String leaveType = req.getParameter("leaveType");
    String startDate = req.getParameter("startDate");
    String endDate = req.getParameter("endDate");
    String reason = req.getParameter("reason");

    // Validate date order
    if (startDate.compareTo(endDate) > 0) {
        resp.sendRedirect("apply-leave?error=1");
        return;
    }

    boolean success = DatabaseHelper.applyLeave(emp.getId(), leaveType.trim(), startDate.trim(), endDate.trim(), reason.trim());
    if (success) {
        resp.sendRedirect("apply-leave?success=1");
    } else {
        resp.sendRedirect("apply-leave?error=1");
    }
}
```

### A.4 Administrative Leave Status Update (`ManageLeavesServlet.java`)
```java
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    HttpSession session = req.getSession(false);
    if (session == null || session.getAttribute("employee") == null) {
        resp.sendRedirect("login");
        return;
    }
    Employee emp = (Employee) session.getAttribute("employee");
    if (!"admin".equals(emp.getRole())) {
        resp.sendRedirect("dashboard");
        return;
    }

    try {
        int leaveId = Integer.parseInt(req.getParameter("leaveId"));
        String action = req.getParameter("action");
        String remarks = req.getParameter("remarks");
        if (remarks == null) remarks = "";

        DatabaseHelper.updateLeaveStatus(leaveId, action, remarks.trim());
    } catch (Exception ignored) {}

    resp.sendRedirect("manage-leaves?msg=1");
}
```

---

# APPENDIX B: ABBREVIATIONS

- **HTML** – HyperText Markup Language
- **CSS** – Cascading Style Sheets
- **JSP** – JavaServer Pages
- **JDBC** – Java Database Connectivity
- **SQL** – Structured Query Language
- **DBMS** – Database Management System
- **RDBMS** – Relational Database Management System
- **HTTP** – Hypertext Transfer Protocol
- **HTTPS** – Hypertext Transfer Protocol Secure
- **MVC** – Model–View–Controller
- **CRUD** – Create, Read, Update, Delete
- **IDE** – Integrated Development Environment
- **WAR** – Web Application Archive
- **JDK** – Java Development Kit
- **JRE** – Java Runtime Environment
- **UI** – User Interface
- **UX** – User Experience
- **API** – Application Programming Interface
- **PK** – Primary Key
- **FK** – Foreign Key
- **XSS** – Cross-Site Scripting
- **DAO** – Data Access Object
- **ELMS** – Employee Leave Management System

