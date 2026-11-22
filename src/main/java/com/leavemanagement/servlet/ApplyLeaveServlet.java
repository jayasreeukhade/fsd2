package com.leavemanagement.servlet;

import com.leavemanagement.dao.DatabaseHelper;
import com.leavemanagement.model.Employee;
import com.leavemanagement.util.HtmlTemplate;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/apply-leave")
public class ApplyLeaveServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("employee") == null) {
            resp.sendRedirect("login");
            return;
        }
        Employee emp = (Employee) session.getAttribute("employee");

        String success = req.getParameter("success");
        String error = req.getParameter("error");

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.print(HtmlTemplate.startPage("Apply Leave"));
        out.print(HtmlTemplate.navbar(emp));
        out.print("<div class='container' style='max-width:650px;'>");
        out.print("<div class='card'>");
        out.print("<h2>Submit Leave Application</h2>");

        if (success != null) {
            out.print(HtmlTemplate.message("Leave application submitted successfully! It is currently pending review.", true));
        }
        if (error != null) {
            out.print(HtmlTemplate.message("Failed to submit leave application. Please check your dates and try again.", false));
        }

        out.print("<form method='post' action='apply-leave'>");

        out.print("<div class='form-group'><label>Leave Type</label>");
        out.print("<select name='leaveType' required>");
        out.print("<option value=''>-- Select Leave Type --</option>");
        out.print("<option value='Casual Leave'>Casual Leave (CL)</option>");
        out.print("<option value='Sick Leave'>Sick Leave (SL)</option>");
        out.print("<option value='Earned Leave'>Earned Leave (EL)</option>");
        out.print("<option value='Maternity Leave'>Maternity Leave</option>");
        out.print("<option value='Paternity Leave'>Paternity Leave</option>");
        out.print("<option value='Unpaid Leave'>Unpaid Leave / Loss of Pay</option>");
        out.print("</select></div>");

        out.print("<div style='display:flex;gap:12px;'>");
        out.print("<div class='form-group' style='flex:1;'><label>Start Date</label>");
        out.print("<input type='date' name='startDate' required></div>");

        out.print("<div class='form-group' style='flex:1;'><label>End Date</label>");
        out.print("<input type='date' name='endDate' required></div>");
        out.print("</div>");

        out.print("<div class='form-group'><label>Reason for Leave</label>");
        out.print("<textarea name='reason' rows='4' required placeholder='Please provide a clear and brief reason for your leave request...'></textarea></div>");

        out.print("<div style='display:flex;gap:10px;margin-top:10px;'>");
        out.print("<button type='submit' style='padding:10px 22px;font-size:14px;'>Submit Application</button>");
        out.print("<a href='dashboard' class='btn btn-secondary' style='padding:10px 18px;font-size:14px;'>Cancel</a>");
        out.print("</div>");

        out.print("</form>");

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

        String leaveType = req.getParameter("leaveType");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String reason = req.getParameter("reason");

        if (leaveType == null || startDate == null || endDate == null || reason == null ||
            leaveType.trim().isEmpty() || startDate.trim().isEmpty() || endDate.trim().isEmpty() || reason.trim().isEmpty()) {
            resp.sendRedirect("apply-leave?error=1");
            return;
        }

        // Basic date validation: ensure startDate <= endDate
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
}
