<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${equipo.ciudad} ${equipo.nombre} &middot; NBA Wiki</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link href="https://fonts.googleapis.com/css2?family=Anton&family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body style="--team-color: ${equipo.colorPrincipal};">

  <div class="authbar">
    <a href="${pageContext.request.contextPath}/equipos">&larr; Todos los equipos</a>
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

  <header class="hero equipo-hero">
    <div class="badge-xl" style="--team-color: ${equipo.colorPrincipal};">
      <img class="badge-logo" src="${pageContext.request.contextPath}/img/logos/${equipo.abreviatura}.png" alt=""
           onload="this.style.display='block'; this.nextElementSibling.style.display='none';"
           onerror="this.style.display='none';">
      <span class="badge-fallback">${equipo.abreviatura}</span>
    </div>
    <h1>${equipo.ciudad} <span>${equipo.nombre}</span></h1>

    <div class="equipo-stats-row">
      <div class="stat-box">
        <span class="stat-num">${equipo.titulos}</span>
        <span class="stat-label">Titulo<c:if test="${equipo.titulos != 1}">s</c:if></span>
      </div>
      <div class="stat-box">
        <span class="stat-num">${equipo.apariciones}</span>
        <span class="stat-label">Finales jugadas</span>
      </div>
      <c:if test="${not empty rivalHistorico}">
        <div class="stat-box">
          <span class="stat-num" style="font-size: 20px;">${rivalHistorico}</span>
          <span class="stat-label">Rival mas frecuente (${rivalHistoricoVeces}x)</span>
        </div>
      </c:if>
    </div>
  </header>

  <main class="equipo-timeline-wrap">
    <h2 class="section-title">Historial en las Finales</h2>
    <div class="equipo-timeline">
      <c:forEach var="ap" items="${apariciones}" varStatus="st">
        <div class="apar-row fade-in-up" style="animation-delay: ${(st.index % 15) * 0.03}s;">
          <span class="apar-anio">${ap.anio}</span>
          <span class="apar-resultado ${ap.campeon ? 'campeon' : 'finalista'}">
            <c:choose>
              <c:when test="${ap.campeon}">&#127942; Campeon</c:when>
              <c:otherwise>Finalista</c:otherwise>
            </c:choose>
          </span>
          <span class="apar-rival">vs ${ap.rivalCiudad} ${ap.rivalNombre}</span>
          <span class="apar-serie">${ap.resultadoSerie}</span>
        </div>
      </c:forEach>
    </div>
  </main>

  <footer>
    Proyecto academico &middot; Bryan Quinonez Spooner
  </footer>

</body>
</html>
