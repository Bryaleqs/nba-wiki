<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Crear cuenta &middot; NBA Wiki</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link href="https://fonts.googleapis.com/css2?family=Anton&family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

  <header class="hero" style="padding: 40px 24px 24px;">
    <div class="eyebrow">NBA Wiki</div>
    <h1 style="font-size: clamp(28px, 6vw, 46px);">Crear <span>cuenta</span></h1>
  </header>

  <main class="form-page">
    <div class="form-card">
      <h2>Registrate</h2>
      <p class="sub">Crea tu cuenta para marcar campeonatos como favoritos.</p>

      <% if (request.getAttribute("error") != null) { %>
        <div class="form-error"><%= request.getAttribute("error") %></div>
      <% } %>

      <form method="post" action="${pageContext.request.contextPath}/registro">
        <div class="field">
          <label for="nombreUsuario">Nombre de usuario</label>
          <input type="text" id="nombreUsuario" name="nombreUsuario" required minlength="3"
                 value="<%= request.getParameter("nombreUsuario") != null ? request.getParameter("nombreUsuario") : "" %>">
        </div>
        <div class="field">
          <label for="email">Correo</label>
          <input type="email" id="email" name="email" required
                 value="<%= request.getParameter("email") != null ? request.getParameter("email") : "" %>">
        </div>
        <div class="field">
          <label for="password">Contraseña</label>
          <input type="password" id="password" name="password" required minlength="6">
        </div>
        <div class="field">
          <label for="password2">Confirmar contraseña</label>
          <input type="password" id="password2" name="password2" required minlength="6">
        </div>
        <button type="submit" class="btn-primary">Crear cuenta</button>
      </form>

      <div class="form-footer-link">
        ¿Ya tienes cuenta? <a href="${pageContext.request.contextPath}/login">Inicia sesion</a>
      </div>
    </div>
  </main>

</body>
</html>
