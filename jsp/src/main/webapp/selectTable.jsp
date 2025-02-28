<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String table = request.getParameter("table");
    String action = request.getParameter("action");

    if (table != null && action != null) {
        String redirectPage;
        if (action.equals("read")) {
            redirectPage = table + "?action=read";
            response.sendRedirect(redirectPage);
        } else {
            redirectPage = action +'/' + action + table.substring(0,1).toUpperCase() + table.substring(1) + ".jsp";
            response.sendRedirect(redirectPage);
            RequestDispatcher dispatcher = request.getRequestDispatcher(redirectPage);
            dispatcher.forward(request, response);
        }
    } else {
        response.sendRedirect("index.jsp");
    }
%>