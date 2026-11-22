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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("employee") != null) {
            Employee emp = (Employee) session.getAttribute("employee");
            if ("admin".equals(emp.getRole())) {
                resp.sendRedirect("admin-dashboard");
            } else {
                resp.sendRedirect("dashboard");
            }
            return;
        }

        String error = req.getParameter("error");
        String registered = req.getParameter("registered");

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.print(HtmlTemplate.startPage("Login"));
        out.print("<div class='container login-box'>");
        out.print("<div class='card'>");
        out.print("<h2>Employee Leave Management</h2>");
        out.print("<p style='margin-bottom:15px;color:#64748b;font-size:14px;'>Sign in to your account</p>");

        if (error != null) {
            out.print(HtmlTemplate.message("Invalid email or password. Please try again.", false));
        }
        if (registered != null) {
            out.print(HtmlTemplate.message("Registration successful! You can now log in.", true));
        }

        out.print("<form method='post' action='login'>");
        out.print("<div class='form-group'><label>Email Address</label>");
        out.print("<input type='email' name='email' required placeholder='name@company.com'></div>");
        out.print("<div class='form-group'><label>Password</label>");
        out.print("<input type='password' name='password' required placeholder='Enter your password'></div>");
        out.print("<button type='submit' style='width:100%;padding:10px;font-size:15px;'>Sign In</button>");
        out.print("</form>");

        out.print("<p style='margin-top:14px;text-align:center;font-size:13px;'>Don't have an account? <a href='register' style='color:#2563eb;'>Register here</a></p>");

        out.print("<div class='demo-creds'>");
        out.print("<strong>Administrator Demo Account:</strong><br>");
        out.print("&bull; <b>Email:</b> <code>admin@company.com</code><br>");
        out.print("&bull; <b>Password:</b> <code>admin123</code>");
        out.print("</div>");

        out.print("</div></div>");
        out.print(HtmlTemplate.endPage());
    }

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
}
