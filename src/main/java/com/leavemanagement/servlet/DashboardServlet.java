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

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("employee") == null) {
            resp.sendRedirect("login");
            return;
        }
        Employee emp = (Employee) session.getAttribute("employee");
        if ("admin".equals(emp.getRole())) {
            resp.sendRedirect("admin-dashboard");
            return;
        }

        int pending = DatabaseHelper.countByStatusForEmployee(emp.getId(), "Pending");
        int approved = DatabaseHelper.countByStatusForEmployee(emp.getId(), "Approved");
        int rejected = DatabaseHelper.countByStatusForEmployee(emp.getId(), "Rejected");
        List<LeaveRequest> recentLeaves = DatabaseHelper.getLeavesByEmployee(emp.getId());

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.print(HtmlTemplate.startPage("Employee Dashboard"));
        out.print(HtmlTemplate.navbar(emp));
        out.print("<div class='container'>");

        out.print("<div class='card' style='display:flex;justify-content:space-between;align-items:center;flex-wrap:wrap;gap:10px;'>");
        out.print("<div>");
        out.print("<h2 style='border:none;margin-bottom:4px;padding:0;'>Welcome back, " + HtmlTemplate.escapeHtml(emp.getName()) + "!</h2>");
        out.print("<p style='color:#64748b;font-size:14px;'>Department: <b>" + HtmlTemplate.escapeHtml(emp.getDepartment()) + "</b> | Email: <b>" + HtmlTemplate.escapeHtml(emp.getEmail()) + "</b></p>");
        out.print("</div>");
        out.print("<div><a href='apply-leave' class='btn' style='padding:9px 18px;font-size:14px;'>+ Apply for Leave</a></div>");
        out.print("</div>");

        // Stats summary cards
        out.print("<div class='stats'>");
        out.print("<div class='stat-box' style='background:#d97706;'><h3>" + pending + "</h3><p>Pending Requests</p></div>");
        out.print("<div class='stat-box' style='background:#16a34a;'><h3>" + approved + "</h3><p>Approved Leaves</p></div>");
        out.print("<div class='stat-box' style='background:#dc2626;'><h3>" + rejected + "</h3><p>Rejected Requests</p></div>");
        out.print("<div class='stat-box' style='background:#2563eb;'><h3>" + recentLeaves.size() + "</h3><p>Total Applications</p></div>");
        out.print("</div>");

        // Recent Leaves table
        out.print("<div class='card'>");
        out.print("<div style='display:flex;justify-content:space-between;align-items:center;'>");
        out.print("<h2>Recent Leave Applications</h2>");
        if (!recentLeaves.isEmpty()) {
            out.print("<a href='leave-history' style='color:#2563eb;font-size:13px;text-decoration:none;'>View all &rarr;</a>");
        }
        out.print("</div>");

        if (recentLeaves.isEmpty()) {
            out.print("<p style='color:#64748b;padding:15px 0;'>You haven't submitted any leave requests yet. <a href='apply-leave' style='color:#2563eb;'>Submit your first request</a>.</p>");
        } else {
            out.print("<table><thead><tr><th>Leave Type</th><th>Start Date</th><th>End Date</th><th>Reason</th><th>Status</th><th>Applied Date</th></tr></thead><tbody>");
            int limit = Math.min(recentLeaves.size(), 5);
            for (int i = 0; i < limit; i++) {
                LeaveRequest lr = recentLeaves.get(i);
                out.print("<tr>");
                out.print("<td><b>" + HtmlTemplate.escapeHtml(lr.getLeaveType()) + "</b></td>");
                out.print("<td>" + HtmlTemplate.escapeHtml(lr.getStartDate()) + "</td>");
                out.print("<td>" + HtmlTemplate.escapeHtml(lr.getEndDate()) + "</td>");
                out.print("<td>" + HtmlTemplate.escapeHtml(lr.getReason()) + "</td>");
                out.print("<td>" + HtmlTemplate.statusBadge(lr.getStatus()) + "</td>");
                out.print("<td>" + HtmlTemplate.escapeHtml(lr.getAppliedDate()) + "</td>");
                out.print("</tr>");
            }
            out.print("</tbody></table>");
        }
        out.print("</div>");

        out.print("</div>");
        out.print(HtmlTemplate.endPage());
    }
}
