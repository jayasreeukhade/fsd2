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

@WebServlet("/leave-history")
public class LeaveHistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("employee") == null) {
            resp.sendRedirect("login");
            return;
        }
        Employee emp = (Employee) session.getAttribute("employee");

        String cancelled = req.getParameter("cancelled");
        List<LeaveRequest> leaves = DatabaseHelper.getLeavesByEmployee(emp.getId());

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.print(HtmlTemplate.startPage("My Leave History"));
        out.print(HtmlTemplate.navbar(emp));
        out.print("<div class='container'>");
        out.print("<div class='card'>");
        out.print("<div style='display:flex;justify-content:space-between;align-items:center;margin-bottom:12px;'>");
        out.print("<h2 style='border:none;margin:0;padding:0;'>My Leave Applications</h2>");
        out.print("<a href='apply-leave' class='btn' style='padding:7px 14px;'>+ Apply New Leave</a>");
        out.print("</div>");

        if (cancelled != null) {
            out.print(HtmlTemplate.message("Pending leave application successfully cancelled.", true));
        }

        if (leaves.isEmpty()) {
            out.print("<p style='color:#64748b;padding:20px 0;'>No leave applications found. <a href='apply-leave' style='color:#2563eb;'>Submit an application here</a>.</p>");
        } else {
            out.print("<table>");
            out.print("<thead><tr><th>#</th><th>Leave Type</th><th>Duration</th><th>Reason</th><th>Status</th><th>Applied Date</th><th>Admin Remarks</th><th>Action</th></tr></thead>");
            out.print("<tbody>");
            int i = 1;
            for (LeaveRequest lr : leaves) {
                out.print("<tr>");
                out.print("<td>" + i++ + "</td>");
                out.print("<td><b>" + HtmlTemplate.escapeHtml(lr.getLeaveType()) + "</b></td>");
                out.print("<td>" + HtmlTemplate.escapeHtml(lr.getStartDate()) + " &rarr; " + HtmlTemplate.escapeHtml(lr.getEndDate()) + "</td>");
                out.print("<td>" + HtmlTemplate.escapeHtml(lr.getReason()) + "</td>");
                out.print("<td>" + HtmlTemplate.statusBadge(lr.getStatus()) + "</td>");
                out.print("<td>" + HtmlTemplate.escapeHtml(lr.getAppliedDate()) + "</td>");
                String remarks = lr.getAdminRemarks() != null && !lr.getAdminRemarks().trim().isEmpty() ? lr.getAdminRemarks() : "-";
                out.print("<td>" + HtmlTemplate.escapeHtml(remarks) + "</td>");

                if ("Pending".equalsIgnoreCase(lr.getStatus())) {
                    out.print("<td>");
                    out.print("<form method='post' action='leave-history' onsubmit='return confirm(\"Are you sure you want to cancel this leave application?\");' style='margin:0;'>");
                    out.print("<input type='hidden' name='leaveId' value='" + lr.getId() + "'>");
                    out.print("<button type='submit' class='btn btn-danger' style='padding:3px 8px;font-size:12px;'>Cancel</button>");
                    out.print("</form>");
                    out.print("</td>");
                } else {
                    out.print("<td style='color:#94a3b8;'>-</td>");
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

        try {
            int leaveId = Integer.parseInt(req.getParameter("leaveId"));
            DatabaseHelper.cancelLeave(leaveId, emp.getId());
        } catch (Exception ignored) {}

        resp.sendRedirect("leave-history?cancelled=1");
    }
}
