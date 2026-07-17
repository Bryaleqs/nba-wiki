<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Salon de Banderines &middot; NBA Wiki</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link href="https://fonts.googleapis.com/css2?family=Anton&family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

  <header class="hero">
    <div class="eyebrow">Universidad de Guayaquil &middot; Programacion Web</div>
    <h1>Salon de <span>Banderines</span></h1>
    <p class="sub">Un recorrido por los 80 campeonatos de la NBA, desde 1947 hasta hoy: el equipo campeon, su rival en la final y el jugador mas valioso de cada año.</p>
  </header>

  <div class="authbar">
    <a href="${pageContext.request.contextPath}/equipos">Equipos</a>
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

  <section class="filters">
    <a href="campeonatos"><button class="${conferenciaActiva == 'todos' ? 'active' : ''}">Todos</button></a>
    <a href="campeonatos?conferencia=Este"><button class="${conferenciaActiva == 'Este' ? 'active' : ''}">Conferencia Este</button></a>
    <a href="campeonatos?conferencia=Oeste"><button class="${conferenciaActiva == 'Oeste' ? 'active' : ''}">Conferencia Oeste</button></a>
  </section>

  <section class="filters filters-decada">
    <a href="campeonatos"><button class="chip-decada ${decadaActiva == 'todos' ? 'active' : ''}">Todas las decadas</button></a>
    <c:forEach var="d" begin="1940" end="2020" step="10">
      <a href="campeonatos?decada=${d}"><button class="chip-decada ${decadaActiva != 'todos' && decadaActiva == d ? 'active' : ''}">${d}s</button></a>
    </c:forEach>
  </section>

  <nav class="rafters" aria-label="Linea de tiempo de campeonatos">
    <c:forEach var="c" items="${campeonatos}" varStatus="st">
      <a href="#card-${c.anio}" class="banner fade-in-up" style="--team-color: ${c.colorPrincipal}; animation-delay: ${st.index * 0.02}s;">
        <div class="flag">
          <span class="year">${c.anio}</span>
          <span class="abbr">${c.abreviatura}</span>
        </div>
        <div class="label">${c.campeon}</div>
      </a>
    </c:forEach>
  </nav>

  <main class="grid">
    <c:forEach var="c" items="${campeonatos}" varStatus="st">
      <div class="card fade-in-up" id="card-${c.anio}" style="--team-color: ${c.colorPrincipal}; animation-delay: ${(st.index % 12) * 0.04}s;">
        <div class="top">
          <a href="${pageContext.request.contextPath}/equipo?id=${c.equipoCampeonId}" class="badge">
            <img class="badge-logo" src="${pageContext.request.contextPath}/img/logos/${c.abreviatura}.png" alt=""
                 onload="this.style.display='block'; this.nextElementSibling.style.display='none';"
                 onerror="this.style.display='none';">
            <span class="badge-fallback">${c.abreviatura}</span>
          </a>
          <div>
            <h3><a href="${pageContext.request.contextPath}/equipo?id=${c.equipoCampeonId}" class="team-link">${c.campeon}</a></h3>
            <div class="year-tag">${c.ciudad} &middot; Temporada ${c.anio}</div>
          </div>
        </div>
        <div class="row"><span>Conferencia</span><b>${c.conferencia}</b></div>
        <div class="row"><span>Rival en la final</span><b><a href="${pageContext.request.contextPath}/equipo?id=${c.equipoFinalistaId}" class="team-link">${c.finalista}</a></b></div>
        <div class="row"><span>Resultado de la serie</span><b>${c.resultadoSerie}</b></div>
        <div class="roster">
          <c:forEach var="jugador" items="${c.roster}">
            <span>${jugador}</span>
          </c:forEach>
        </div>
        <div class="mvp-tag">MVP Finales: ${c.mvp}</div>

        <form class="fav-form" method="post" action="${pageContext.request.contextPath}/favorito">
          <input type="hidden" name="campeonatoId" value="${c.id}">
          <input type="hidden" name="anio" value="${c.anio}">
          <button type="submit" class="fav-btn ${c.esFavorito ? 'activo' : ''}">
            <c:choose>
              <c:when test="${c.esFavorito}">&#9733; En tus favoritos</c:when>
              <c:otherwise>&#9734; Marcar como favorito</c:otherwise>
            </c:choose>
            <c:if test="${c.totalFavoritos > 0}"> (${c.totalFavoritos})</c:if>
          </button>
        </form>
      </div>
    </c:forEach>
  </main>

  <footer>
    Proyecto academico &middot; Bryan Quinonez Spooner &middot; Datos leidos via Servlet + JDBC
  </footer>

</body>
</html>
