<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Equipos &middot; NBA Wiki</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link href="https://fonts.googleapis.com/css2?family=Anton&family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

  <header class="hero">
    <div class="eyebrow">Universidad de Guayaquil &middot; Programacion Web</div>
    <h1>Todos los <span>Equipos</span></h1>
    <p class="sub">Las 35 franquicias que han jugado unas Finales de la NBA desde 1947, ordenadas por titulos.</p>
  </header>

  <div class="authbar">
    <a href="${pageContext.request.contextPath}/campeonatos">Campeonatos</a>
    <c:choose>
      <c:when test="${not empty sessionScope.usuario}">
        <span class="saludo">Hola, <b>${sessionScope.usuario.nombreUsuario}</b></span>
        <a href="${pageContext.request.contextPath}/perfil">Mi perfil</a>
        <a href="${pageContext.request.contextPath}/logout" class="btn-link">Cerrar sesion</a>
      </c:when>
      <c:otherwise>
        <a href="${pageContext.request.contextPath}/login">Iniciar sesion</a>
        <a href="${pageContext.request.contextPath}/registro" class="btn-link">Registrarse</a>
      </c:otherwise>
    </c:choose>
  </div>

  <main class="equipos-wrap">
    <c:forEach var="e" items="${equipos}" varStatus="st">
      <a href="${pageContext.request.contextPath}/equipo?id=${e.id}" class="equipo-row fade-in-up"
         style="--team-color: ${e.colorPrincipal}; animation-delay: ${(st.index % 15) * 0.03}s;">
        <span class="equipo-rank">#${st.index + 1}</span>
        <div class="badge">
          <img class="badge-logo" src="${pageContext.request.contextPath}/img/logos/${e.abreviatura}.png" alt=""
               onload="this.style.display='block'; this.nextElementSibling.style.display='none';"
               onerror="this.style.display='none';">
          <span class="badge-fallback">${e.abreviatura}</span>
        </div>
        <div class="equipo-info">
          <h3>${e.ciudad} ${e.nombre}</h3>
          <span class="equipo-sub">${e.apariciones} aparicion<c:if test="${e.apariciones != 1}">es</c:if> en la final</span>
        </div>
        <div class="equipo-titulos">
          <c:choose>
            <c:when test="${e.titulos > 0}">
              <span class="titulos-num">${e.titulos}</span>
              <span class="titulos-label">titulo<c:if test="${e.titulos != 1}">s</c:if></span>
            </c:when>
            <c:otherwise>
              <span class="titulos-label sin-titulos">Sin titulos</span>
            </c:otherwise>
          </c:choose>
        </div>
      </a>
    </c:forEach>
  </main>

  <footer>
    Proyecto academico &middot; Bryan Quinonez Spooner
  </footer>

</body>
</html>
