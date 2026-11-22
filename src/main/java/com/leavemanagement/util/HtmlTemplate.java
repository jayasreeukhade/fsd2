package com.leavemanagement.util;

import com.leavemanagement.model.Employee;

public class HtmlTemplate {

    private static final String CSS = ""
            + "* { margin:0; padding:0; box-sizing:border-box; font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; }\n"
            + "body { background:#f4f6f9; color:#333; line-height:1.5; }\n"
            + ".navbar { background:#1e293b; color:#fff; padding:12px 24px; display:flex; justify-content:space-between; align-items:center; box-shadow:0 2px 4px rgba(0,0,0,0.1); }\n"
            + ".navbar .brand { font-size:18px; font-weight:bold; color:#fff; text-decoration:none; }\n"
            + ".navbar a { color:#cbd5e1; text-decoration:none; margin-left:16px; font-size:14px; transition:color 0.2s; }\n"
            + ".navbar a:hover { color:#fff; text-decoration:none; }\n"
            + ".container { max-width:960px; margin:28px auto; padding:0 16px; }\n"
            + ".card { background:#fff; border:1px solid #e2e8f0; border-radius:6px; padding:22px; margin-bottom:20px; box-shadow:0 1px 3px rgba(0,0,0,0.05); }\n"
            + ".card h2 { margin-bottom:14px; color:#1e293b; font-size:20px; border-bottom:1px solid #f1f5f9; padding-bottom:10px; }\n"
            + "table { width:100%; border-collapse:collapse; margin-top:10px; font-size:14px; }\n"
            + "th, td { padding:10px 12px; border:1px solid #e2e8f0; text-align:left; }\n"
            + "th { background:#f8fafc; color:#475569; font-weight:600; }\n"
            + "tr:hover { background:#f8fafc; }\n"
            + "input, select, textarea { padding:8px 10px; border:1px solid #cbd5e1; border-radius:4px; width:100%; margin-bottom:12px; font-size:14px; }\n"
            + "input:focus, select:focus, textarea:focus { outline:none; border-color:#2563eb; }\n"
            + "button, .btn { padding:7px 16px; border:none; border-radius:4px; cursor:pointer; color:#fff; background:#2563eb; text-decoration:none; display:inline-block; font-size:13px; font-weight:500; margin:2px; }\n"
            + "button:hover, .btn:hover { background:#1d4ed8; }\n"
            + ".btn-success { background:#16a34a; }\n"
            + ".btn-success:hover { background:#15803d; }\n"
            + ".btn-danger { background:#dc2626; }\n"
            + ".btn-danger:hover { background:#b91c1c; }\n"
            + ".btn-secondary { background:#64748b; }\n"
            + ".btn-secondary:hover { background:#475569; }\n"
            + ".msg { padding:10px 14px; margin-bottom:16px; border-radius:4px; font-size:14px; }\n"
            + ".msg-success { background:#dcfce7; color:#166534; border:1px solid #bbf7d0; }\n"
            + ".msg-error { background:#fee2e2; color:#991b1b; border:1px solid #fecaca; }\n"
            + ".stats { display:flex; gap:16px; flex-wrap:wrap; margin-bottom:20px; }\n"
            + ".stat-box { flex:1; min-width:140px; padding:16px; text-align:center; border-radius:6px; color:#fff; }\n"
            + ".stat-box h3 { font-size:26px; margin-bottom:4px; }\n"
            + ".stat-box p { font-size:13px; opacity:0.9; text-transform:uppercase; letter-spacing:0.5px; }\n"
            + ".form-group { margin-bottom:12px; }\n"
            + "label { display:block; margin-bottom:5px; font-weight:600; font-size:13px; color:#334155; }\n"
            + ".badge { padding:3px 8px; border-radius:12px; font-size:11px; font-weight:600; color:#fff; display:inline-block; text-transform:uppercase; }\n"
            + ".badge-pending { background:#d97706; }\n"
            + ".badge-approved { background:#16a34a; }\n"
            + ".badge-rejected { background:#dc2626; }\n"
            + ".login-box { max-width:440px; margin:60px auto; }\n"
            + ".demo-creds { background:#f1f5f9; border:1px solid #cbd5e1; border-radius:4px; padding:10px 12px; margin-top:14px; font-size:12px; color:#475569; }\n"
            + ".demo-creds code { background:#e2e8f0; padding:1px 4px; border-radius:3px; font-family:monospace; }\n";

    public static String startPage(String title) {
        return "<!DOCTYPE html>\n<html lang='en'>\n<head>\n<meta charset='UTF-8'>\n"
                + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
                + "<title>" + escapeHtml(title) + " - Employee Leave Management System</title>\n"
                + "<style>" + CSS + "</style>\n"
                + "</head>\n<body>\n";
    }

    public static String endPage() {
        return "</body>\n</html>";
    }

    public static String navbar(Employee emp) {
        StringBuilder sb = new StringBuilder();
        sb.append("<div class='navbar'>\n");
        sb.append("<a class='brand' href='").append(emp != null && "admin".equals(emp.getRole()) ? "admin-dashboard" : "dashboard").append("'>Leave Management System</a>\n");
        sb.append("<div>\n");
        if (emp != null) {
            if ("admin".equals(emp.getRole())) {
                sb.append("<a href='admin-dashboard'>Dashboard</a>");
                sb.append("<a href='manage-leaves'>All Leaves</a>");
                sb.append("<a href='employees'>Employees</a>");
            } else {
                sb.append("<a href='dashboard'>Dashboard</a>");
                sb.append("<a href='apply-leave'>Apply Leave</a>");
                sb.append("<a href='leave-history'>My Leaves</a>");
            }
            sb.append("<a href='logout'>Logout (").append(escapeHtml(emp.getName())).append(")</a>");
        } else {
            sb.append("<a href='login'>Login</a>");
            sb.append("<a href='register'>Register</a>");
        }
        sb.append("</div>\n</div>\n");
        return sb.toString();
    }

    public static String message(String msg, boolean success) {
        if (msg == null || msg.trim().isEmpty()) return "";
        String cls = success ? "msg-success" : "msg-error";
        return "<div class='msg " + cls + "'>" + escapeHtml(msg) + "</div>\n";
    }

    public static String statusBadge(String status) {
        if (status == null) status = "Pending";
        String cls = "badge-pending";
        if ("Approved".equalsIgnoreCase(status)) cls = "badge-approved";
        else if ("Rejected".equalsIgnoreCase(status)) cls = "badge-rejected";
        return "<span class='badge " + cls + "'>" + escapeHtml(status) + "</span>";
    }

    public static String escapeHtml(String text) {
        if (text == null) return "";
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}
