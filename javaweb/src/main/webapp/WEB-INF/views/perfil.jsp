<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Mi perfil &middot; NBA Wiki</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link href="https://fonts.googleapis.com/css2?family=Anton&family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

  <header class="hero" style="padding: 40px 24px 24px;">
    <div class="eyebrow">NBA Wiki</div>
    <h1 style="font-size: clamp(28px, 6vw, 46px);">Mi <span>perfil</span></h1>
  </header>

  <div class="authbar">
    <span class="saludo">Hola, <b>${sessionScope.usuario.nombreUsuario}</b></span>
    <a href="${pageContext.request.contextPath}/campeonatos">Ver campeonatos</a>
    <a href="${pageContext.request.contextPath}/logout" class="btn-link">Cerrar sesion</a>
  </div>

  <main class="perfil-wrap">

    <!-- INSIGNIAS -->
    <section class="perfil-card fade-in">
      <h2>Insignias</h2>
      <div class="badges">
        <c:forEach var="ins" items="${insignias}">
          <c:set var="titulo" value="${fn:substringBefore(ins, ':')}" />
          <span class="badge-pill">${titulo}</span>
        </c:forEach>
      </div>
    </section>

    <!-- EQUIPO FAVORITO -->
    <section class="perfil-card fade-in" style="animation-delay: .08s;">
      <h2>Mi equipo favorito</h2>

      <c:choose>
        <c:when test="${not empty equipoFavNombre}">
          <div class="team-hero" style="--team-color: ${equipoFavColor}">
            <div class="badge-big">
              <img class="badge-logo" src="${pageContext.request.contextPath}/img/logos/${equipoFavAbbr}.png" alt=""
                   onload="this.style.display='block'; this.nextElementSibling.style.display='none';"
                   onerror="this.style.display='none';">
              <span class="badge-fallback">${equipoFavAbbr}</span>
            </div>
            <div>
              <h3>${equipoFavCiudad} ${equipoFavNombre}</h3>
              <p class="team-stats">
                <b>${equipoFavTitulos}</b> titulo<c:if test="${equipoFavTitulos != 1}">s</c:if> en la wiki
                <c:if test="${equipoFavTitulos > 0}">
                  &middot; primero en <b>${equipoFavPrimero}</b> &middot; el mas reciente en <b>${equipoFavUltimo}</b>
                </c:if>
              </p>
            </div>
          </div>

          <c:if test="${not empty aniosTitulo}">
            <div class="mini-timeline">
              <c:forEach var="anio" items="${aniosTitulo}">
                <span class="dot" style="--team-color: ${equipoFavColor}" title="${anio}">${anio}</span>
              </c:forEach>
            </div>
          </c:if>
        </c:when>
        <c:otherwise>
          <p class="sub" style="text-transform:none; letter-spacing:normal;">Aun no elegiste un equipo favorito.</p>
        </c:otherwise>
      </c:choose>

      <form method="post" action="${pageContext.request.contextPath}/perfil" class="team-picker">
        <select name="equipoFavoritoId" required>
          <option value="" disabled ${empty equipoFavNombre ? 'selected' : ''}>Elige tu equipo...</option>
          <c:forEach var="equipo" items="${equipos}">
            <option value="${equipo.id}" ${equipo.nombre == equipoFavNombre && equipo.ciudad == equipoFavCiudad ? 'selected' : ''}>
              ${equipo.ciudad} ${equipo.nombre}
            </option>
          </c:forEach>
        </select>
        <button type="submit" class="btn-primary" style="width:auto; padding: 10px 20px;">Guardar</button>
      </form>
    </section>

    <!-- FAVORITOS -->
    <section class="perfil-card fade-in" style="animation-delay: .16s;">
      <h2>Mis campeonatos favoritos</h2>
      <c:choose>
        <c:when test="${empty favoritos}">
          <p class="sub" style="text-transform:none; letter-spacing:normal;">
            Todavia no marcas ningun campeonato como favorito.
            <a href="${pageContext.request.contextPath}/campeonatos">Explora la wiki</a> y marca los que te gusten.
          </p>
        </c:when>
        <c:otherwise>
          <div class="fav-chip-grid">
            <c:forEach var="f" items="${favoritos}">
              <a href="${pageContext.request.contextPath}/campeonatos#card-${f.anio}" class="fav-chip" style="--team-color: ${f.colorPrincipal}">
                <span class="chip-abbr">${f.abreviatura}</span>
                <span>${f.anio} &middot; ${f.campeon}</span>
              </a>
            </c:forEach>
          </div>
        </c:otherwise>
      </c:choose>
    </section>

  </main>

</body>
</html>
