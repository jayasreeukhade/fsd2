package com.leavemanagement.servlet;

import com.leavemanagement.dao.DatabaseHelper;
import com.leavemanagement.util.HtmlTemplate;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String error = req.getParameter("error");

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.print(HtmlTemplate.startPage("Register"));
        out.print("<div class='container login-box'>");
        out.print("<div class='card'>");
        out.print("<h2>Employee Registration</h2>");
        out.print("<p style='margin-bottom:15px;color:#64748b;font-size:14px;'>Create an account to submit leave requests</p>");

        if (error != null) {
            out.print(HtmlTemplate.message("This email is already registered. Please login or use another email.", false));
        }

        out.print("<form method='post' action='register'>");
        out.print("<div class='form-group'><label>Full Name</label>");
        out.print("<input type='text' name='name' required placeholder='e.g. Alice Johnson'></div>");
        out.print("<div class='form-group'><label>Email Address</label>");
        out.print("<input type='email' name='email' required placeholder='alice@company.com'></div>");
        out.print("<div class='form-group'><label>Password</label>");
        out.print("<input type='password' name='password' required minlength='4' placeholder='At least 4 characters'></div>");
        out.print("<div class='form-group'><label>Department</label>");
        out.print("<select name='department' required>");
        out.print("<option value=''>-- Select Department --</option>");
        out.print("<option value='IT Development'>IT Development</option>");
        out.print("<option value='Human Resources'>Human Resources</option>");
        out.print("<option value='Finance & Accounts'>Finance & Accounts</option>");
        out.print("<option value='Marketing & Sales'>Marketing & Sales</option>");
        out.print("<option value='Operations'>Operations</option>");
        out.print("<option value='Customer Support'>Customer Support</option>");
        out.print("</select></div>");
        out.print("<button type='submit' style='width:100%;padding:10px;font-size:15px;'>Create Account</button>");
        out.print("</form>");

        out.print("<p style='margin-top:14px;text-align:center;font-size:13px;'>Already have an account? <a href='login' style='color:#2563eb;'>Login here</a></p>");
        out.print("</div></div>");
        out.print(HtmlTemplate.endPage());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String department = req.getParameter("department");

        if (name == null || email == null || password == null || department == null ||
            name.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty() || department.trim().isEmpty()) {
            resp.sendRedirect("register?error=1");
            return;
        }

        boolean success = DatabaseHelper.registerEmployee(name.trim(), email.trim(), password.trim(), department.trim());
        if (success) {
            resp.sendRedirect("login?registered=1");
        } else {
            resp.sendRedirect("register?error=1");
        }
    }
}
