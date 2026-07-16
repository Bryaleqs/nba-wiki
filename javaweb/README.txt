NBA WIKI - VERSION JAVAWEB (Servlets + JSP + JDBC + H2 embebida)
====================================================================

NOVEDAD: esta version YA NO depende de ningun servicio de base de
datos externo (nada de db4free, Aiven, etc.). Usa H2, una base de
datos SQL real que corre empaquetada junto con tu propia app. Se
crea y se llena sola la primera vez que arrancas el proyecto, leyendo
src/main/resources/nba_wiki_h2.sql. Cero cuentas de terceros, cero
credenciales expuestas.

1) COMPILAR Y EMPAQUETAR (requiere Maven instalado)
------------------------------------------------------
Desde la carpeta del proyecto:

    mvn clean package

Esto genera target/nba-wiki.war

2) EJECUTAR EN LOCAL
----------------------
Opcion A - NetBeans / Eclipse:
  - Abre el proyecto como "Maven Web Application" y dale "Run".
  - Abre: http://localhost:8080/nba-wiki/

Opcion B - Tomcat manual:
  - Copia target/nba-wiki.war a la carpeta webapps/ de tu Tomcat.
  - Arranca Tomcat, abre http://localhost:8080/nba-wiki/

La primera vez que la app abre una conexion, H2 crea automaticamente
la carpeta data/ (junto al proyecto) con el archivo nba_wiki.mv.db,
ya con las 4 tablas y los 12 campeonatos cargados.

3) DESPLIEGUE GRATUITO (Render, con Docker)
-----------------------------------------------
No necesitas contratar ni registrar ninguna base de datos aparte:
  1. Sube el proyecto a un repo de GitHub (incluye el Dockerfile).
  2. En render.com: New + -> Web Service -> conecta el repo.
  3. Render detecta el Dockerfile solo (Environment: Docker).
  4. Instance Type: Free -> Deploy.

Nota: en el free tier de Render, el contenedor se reinicia cuando
"despierta" tras estar dormido, asi que la base H2 se re-crea limpia
en cada arranque (con los mismos 12 campeonatos). Para una wiki de
solo lectura como esta, eso es justo lo que quieres: siempre arranca
con datos consistentes, sin que nada se corrompa.

4) SI EN EL FUTURO QUIERES CAMBIAR A TU PROPIO MYSQL
--------------------------------------------------------
El proyecto lo deja listo: en WEB-INF/web.xml hay un bloque comentado
con la configuracion de MySQL. Solo comenta el bloque de H2, descomenta
el de MySQL, pon tus credenciales, y usa database/nba_wiki.sql para
crear la base en tu propio servidor (uno que tu controles, no un
hosting gratuito de terceros).

HISTORICO COMPLETO (1947-2026)
----------------------------------
La wiki ahora cubre las 80 finales de la NBA, desde los Philadelphia
Warriors de 1947 hasta los New York Knicks de 2026. Incluye franquicias
historicas (Minneapolis Lakers, Fort Wayne Pistons, Syracuse Nationals,
etc.) tal como se llamaban en su epoca. El MVP de Finales solo existe
desde 1969; para años anteriores se muestra un aviso en vez de un dato
inventado.

Filtros disponibles: por conferencia (Este/Oeste, segun corresponda
a ESE año especifico, ya que algunos equipos cambiaron de conferencia
con el tiempo) y por decada.

MI PERFIL (nueva seccion)
-----------------------------
Cada usuario logueado tiene una pagina /perfil con:
  - Insignias segun su actividad (Fan, Coleccionista, Historiador,
    Old School, Al dia, Hincha)
  - Un equipo favorito elegible desde un selector (se guarda en la
    base de datos), con sus estadisticas: total de titulos, primer
    y ultimo campeonato, y una mini linea de tiempo con cada año
    que salio campeon
  - La lista de campeonatos que ha marcado como favoritos, con
    enlace directo a cada tarjeta

ANIMACIONES
--------------
Los banderines y las tarjetas aparecen con una animacion de entrada
escalonada (fade + deslizamiento), las tarjetas se elevan al pasar
el mouse, y el boton de favorito tiene un pulso dorado al activarse.
Todo esta hecho en CSS puro (@keyframes), sin librerias externas.

ARQUITECTURA (patron MVC simple)
-----------------------------------
  ChampionsServlet.java   -> Controlador: consulta campeonatos + favoritos (+ filtros)
  RegistroServlet.java    -> Controlador: crea cuentas nuevas
  LoginServlet.java       -> Controlador: verifica credenciales, crea sesion
  LogoutServlet.java      -> Controlador: cierra sesion
  FavoritoServlet.java    -> Controlador: marca/desmarca un campeonato favorito
  PerfilServlet.java      -> Controlador: equipo favorito, insignias, stats
  PasswordUtil.java       -> Hashea contraseñas (SHA-256 + salt por usuario)
  Campeonato.java / Usuario.java / Equipo.java -> Modelos (POJOs)
  DBConnection.java       -> Detecta H2 o MySQL segun la URL de web.xml
  WEB-INF/views/*.jsp     -> Vistas: index, login, registro, perfil
  index.jsp (raiz)        -> redirige siempre a /campeonatos

REGISTRO E INICIO DE SESION
-------------------------------
  /registro  -> formulario de registro (GET) / crea la cuenta (POST)
  /login     -> formulario de login (GET) / valida credenciales (POST)
  /logout    -> cierra la sesion

Las contraseñas nunca se guardan en texto plano: cada usuario tiene un
"salt" aleatorio propio, y se guarda el hash SHA-256 de (password + salt).
Esto se explica en un comentario dentro de PasswordUtil.java, incluyendo
por que en un sistema real de produccion se usaria BCrypt en vez de esto.

FAVORITOS (funcionalidad que depende de estar logueado)
-------------------------------------------------------
Cualquier usuario logueado puede marcar un campeonato como favorito
desde su tarjeta (boton con estrella). Esto hace un INSERT/DELETE real
en la tabla `favoritos`, y el contador de favoritos se ve incluso para
quien no ha iniciado sesion. Si alguien sin sesion intenta marcar un
favorito, se le redirige automaticamente a /login.

RUTAS
------
  /                       -> redirige a /campeonatos
  /campeonatos            -> todos los campeonatos (con estado de favoritos)
  /campeonatos?conferencia=Este   -> filtra por conferencia Este
  /campeonatos?conferencia=Oeste  -> filtra por conferencia Oeste
  /campeonatos?decada=1990        -> filtra por decada (1940 a 2020)
  /registro               -> crear cuenta
  /login                  -> iniciar sesion
  /logout                 -> cerrar sesion
  /favorito (POST)        -> marca/desmarca favorito (requiere sesion)
  /perfil (GET)            -> ver perfil, insignias y equipo favorito
  /perfil (POST)           -> actualizar equipo favorito
