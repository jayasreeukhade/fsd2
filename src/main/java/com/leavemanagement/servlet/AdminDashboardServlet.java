package com.leavemanagement.servlet;

import com.leavemanagement.dao.DatabaseHelper;
import com.leavemanagement.model.Employee;
import com.leavemanagement.model.LeaveRequest;
import com.leavemanagement.util.HtmlTemplate;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/admin-dashboard")
public class AdminDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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

        int pending = DatabaseHelper.countByStatus("Pending");
        int approved = DatabaseHelper.countByStatus("Approved");
        int rejected = DatabaseHelper.countByStatus("Rejected");
        int totalEmployees = DatabaseHelper.getAllEmployees().size();
        List<LeaveRequest> pendingLeaves = DatabaseHelper.getPendingLeaves();

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.print(HtmlTemplate.startPage("Admin Dashboard"));
        out.print(HtmlTemplate.navbar(emp));
        out.print("<div class='container'>");

        out.print("<div class='card'>");
        out.print("<h2 style='border:none;margin-bottom:4px;padding:0;'>Admin Dashboard</h2>");
        out.print("<p style='color:#64748b;font-size:14px;'>Logged in as <b>" + HtmlTemplate.escapeHtml(emp.getName()) + "</b> (" + HtmlTemplate.escapeHtml(emp.getEmail()) + ")</p>");
        out.print("</div>");

        // Stats boxes
        out.print("<div class='stats'>");
        out.print("<div class='stat-box' style='background:#2563eb;'><h3>" + totalEmployees + "</h3><p>Registered Users</p></div>");
        out.print("<div class='stat-box' style='background:#d97706;'><h3>" + pending + "</h3><p>Pending Approvals</p></div>");
        out.print("<div class='stat-box' style='background:#16a34a;'><h3>" + approved + "</h3><p>Approved Leaves</p></div>");
        out.print("<div class='stat-box' style='background:#dc2626;'><h3>" + rejected + "</h3><p>Rejected Requests</p></div>");
        out.print("</div>");

        // Pending Requests action panel
        out.print("<div class='card'>");
        out.print("<div style='display:flex;justify-content:space-between;align-items:center;margin-bottom:10px;'>");
        out.print("<h2 style='border:none;margin:0;padding:0;'>Pending Leave Approvals (" + pendingLeaves.size() + ")</h2>");
        out.print("<a href='manage-leaves' style='color:#2563eb;font-size:13px;text-decoration:none;'>View all leaves &rarr;</a>");
        out.print("</div>");

        if (pendingLeaves.isEmpty()) {
            out.print("<p style='color:#16a34a;padding:20px 0;'>&check; All clear! There are no pending leave requests to review.</p>");
        } else {
            out.print("<table>");
            out.print("<thead><tr><th>Employee</th><th>Department</th><th>Type</th><th>Duration</th><th>Reason</th><th>Review & Remarks</th></tr></thead>");
            out.print("<tbody>");
            for (LeaveRequest lr : pendingLeaves) {
                out.print("<tr>");
                out.print("<td><b>" + HtmlTemplate.escapeHtml(lr.getEmployeeName()) + "</b></td>");
                out.print("<td>" + HtmlTemplate.escapeHtml(lr.getDepartment()) + "</td>");
                out.print("<td>" + HtmlTemplate.escapeHtml(lr.getLeaveType()) + "</td>");
                out.print("<td>" + HtmlTemplate.escapeHtml(lr.getStartDate()) + "<br><small style='color:#64748b;'>to</small> " + HtmlTemplate.escapeHtml(lr.getEndDate()) + "</td>");
                out.print("<td>" + HtmlTemplate.escapeHtml(lr.getReason()) + "</td>");
                out.print("<td style='white-space:nowrap;'>");
                out.print("<form method='post' action='manage-leaves' style='display:inline;'>");
                out.print("<input type='hidden' name='leaveId' value='" + lr.getId() + "'>");
                out.print("<input type='text' name='remarks' placeholder='Optional remarks' style='width:140px;padding:5px 8px;font-size:12px;margin-bottom:4px;display:block;'>");
                out.print("<button name='action' value='Approved' class='btn btn-success' style='padding:5px 10px;font-size:12px;'>Approve</button> ");
                out.print("<button name='action' value='Rejected' class='btn btn-danger' style='padding:5px 10px;font-size:12px;'>Reject</button>");
                out.print("</form>");
                out.print("</td>");
                out.print("</tr>");
            }
            out.print("</tbody></table>");
        }
        out.print("</div>");

        out.print("</div>");
        out.print(HtmlTemplate.endPage());
    }
}
