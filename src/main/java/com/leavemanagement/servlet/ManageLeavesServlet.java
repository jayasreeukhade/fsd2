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

@WebServlet("/manage-leaves")
public class ManageLeavesServlet extends HttpServlet {

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

        String msg = req.getParameter("msg");
        List<LeaveRequest> allLeaves = DatabaseHelper.getAllLeaves();

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.print(HtmlTemplate.startPage("Manage Leave Requests"));
        out.print(HtmlTemplate.navbar(emp));
        out.print("<div class='container'>");
        out.print("<div class='card'>");
        out.print("<h2>All Leave Applications</h2>");

        if (msg != null) {
            out.print(HtmlTemplate.message("Leave application has been successfully updated!", true));
        }

        if (allLeaves.isEmpty()) {
            out.print("<p style='color:#64748b;padding:20px 0;'>No leave applications found in the system.</p>");
        } else {
            out.print("<table>");
            out.print("<thead><tr><th>#</th><th>Employee</th><th>Dept</th><th>Type</th><th>Duration</th><th>Reason</th><th>Status</th><th>Applied</th><th>Admin Remarks</th><th>Action</th></tr></thead>");
            out.print("<tbody>");
            int i = 1;
            for (LeaveRequest lr : allLeaves) {
                out.print("<tr>");
                out.print("<td>" + i++ + "</td>");
                out.print("<td><b>" + HtmlTemplate.escapeHtml(lr.getEmployeeName()) + "</b></td>");
                out.print("<td>" + HtmlTemplate.escapeHtml(lr.getDepartment()) + "</td>");
                out.print("<td>" + HtmlTemplate.escapeHtml(lr.getLeaveType()) + "</td>");
                out.print("<td>" + HtmlTemplate.escapeHtml(lr.getStartDate()) + " &rarr; " + HtmlTemplate.escapeHtml(lr.getEndDate()) + "</td>");
                out.print("<td>" + HtmlTemplate.escapeHtml(lr.getReason()) + "</td>");
                out.print("<td>" + HtmlTemplate.statusBadge(lr.getStatus()) + "</td>");
                out.print("<td>" + HtmlTemplate.escapeHtml(lr.getAppliedDate()) + "</td>");
                String remarks = lr.getAdminRemarks() != null && !lr.getAdminRemarks().trim().isEmpty() ? lr.getAdminRemarks() : "-";
                out.print("<td>" + HtmlTemplate.escapeHtml(remarks) + "</td>");

                if ("Pending".equalsIgnoreCase(lr.getStatus())) {
                    out.print("<td style='white-space:nowrap;'>");
                    out.print("<form method='post' action='manage-leaves' style='display:inline;margin:0;'>");
                    out.print("<input type='hidden' name='leaveId' value='" + lr.getId() + "'>");
                    out.print("<input type='text' name='remarks' placeholder='Remarks' style='width:100px;padding:4px 6px;font-size:12px;margin-bottom:3px;display:block;'>");
                    out.print("<button name='action' value='Approved' class='btn btn-success' style='padding:3px 8px;font-size:11px;'>Approve</button> ");
                    out.print("<button name='action' value='Rejected' class='btn btn-danger' style='padding:3px 8px;font-size:11px;'>Reject</button>");
                    out.print("</form>");
                    out.print("</td>");
                } else {
                    out.print("<td style='color:#94a3b8;font-size:12px;'>Decided</td>");
                }

                out.print("</tr>");
            }
            out.print("</tbody></table>");
        }

        out.print("</div></div>");
        out.print(HtmlTemplate.endPage());
    }

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

        String referer = req.getHeader("Referer");
        if (referer != null && referer.contains("admin-dashboard")) {
            resp.sendRedirect("admin-dashboard");
        } else {
            resp.sendRedirect("manage-leaves?msg=1");
        }
    }
}
