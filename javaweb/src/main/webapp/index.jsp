<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Punto de entrada: redirige siempre al Servlet que arma los datos.
    response.sendRedirect(request.getContextPath() + "/campeonatos");
%>
