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
import java.util.List;

@WebServlet("/employees")
public class EmployeesServlet extends HttpServlet {

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

        List<Employee> employees = DatabaseHelper.getAllEmployees();

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.print(HtmlTemplate.startPage("Employee Directory"));
        out.print(HtmlTemplate.navbar(emp));
        out.print("<div class='container'>");
        out.print("<div class='card'>");
        out.print("<h2>Employee Directory (" + employees.size() + ")</h2>");
        out.print("<p style='color:#64748b;font-size:13px;margin-bottom:14px;'>List of all registered staff members in the system</p>");

        out.print("<table>");
        out.print("<thead><tr><th>#</th><th>Employee Name</th><th>Email</th><th>Department</th><th>Role</th><th>Pending Leaves</th><th>Approved Leaves</th></tr></thead>");
        out.print("<tbody>");
        int i = 1;
        for (Employee e : employees) {
            int pCount = DatabaseHelper.countByStatusForEmployee(e.getId(), "Pending");
            int aCount = DatabaseHelper.countByStatusForEmployee(e.getId(), "Approved");
            out.print("<tr>");
            out.print("<td>" + i++ + "</td>");
            out.print("<td><b>" + HtmlTemplate.escapeHtml(e.getName()) + "</b></td>");
            out.print("<td>" + HtmlTemplate.escapeHtml(e.getEmail()) + "</td>");
            out.print("<td>" + HtmlTemplate.escapeHtml(e.getDepartment()) + "</td>");
            out.print("<td><span style='text-transform:uppercase;font-size:11px;font-weight:bold;color:" + ("admin".equals(e.getRole()) ? "#2563eb" : "#475569") + ";'>" + HtmlTemplate.escapeHtml(e.getRole()) + "</span></td>");
            out.print("<td>" + pCount + "</td>");
            out.print("<td>" + aCount + "</td>");
            out.print("</tr>");
        }
        out.print("</tbody></table>");

        out.print("</div></div>");
        out.print(HtmlTemplate.endPage());
    }
}

