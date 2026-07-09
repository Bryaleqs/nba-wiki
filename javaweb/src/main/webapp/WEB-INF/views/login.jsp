<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Iniciar sesion &middot; NBA Wiki</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link href="https://fonts.googleapis.com/css2?family=Anton&family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

  <header class="hero" style="padding: 40px 24px 24px;">
    <div class="eyebrow">NBA Wiki</div>
    <h1 style="font-size: clamp(28px, 6vw, 46px);">Iniciar <span>sesion</span></h1>
  </header>

  <main class="form-page">
    <div class="form-card">
      <h2>Bienvenido de nuevo</h2>
      <p class="sub">Ingresa para marcar tus campeonatos favoritos.</p>

      <% if (request.getParameter("registrado") != null) { %>
        <div class="form-success">Cuenta creada. Ahora inicia sesion.</div>
      <% } %>

      <% if (request.getAttribute("error") != null) { %>
        <div class="form-error"><%= request.getAttribute("error") %></div>
      <% } %>

      <form method="post" action="${pageContext.request.contextPath}/login">
        <div class="field">
          <label for="nombreUsuario">Nombre de usuario</label>
          <input type="text" id="nombreUsuario" name="nombreUsuario" required autofocus>
        </div>
        <div class="field">
          <label for="password">Contraseña</label>
          <input type="password" id="password" name="password" required>
        </div>
        <button type="submit" class="btn-primary">Entrar</button>
      </form>

      <div class="form-footer-link">
        ¿No tienes cuenta? <a href="${pageContext.request.contextPath}/registro">Registrate</a>
      </div>
    </div>
  </main>

</body>
</html>
