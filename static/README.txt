NBA WIKI - VERSION ESTATICA (HTML + CSS + JS)
================================================

Como probarla:
1. Abre index.html directamente en el navegador (doble clic), o
2. Mejor aun: usa la extension "Live Server" de VS Code para evitar
   problemas de rutas relativas.

Estructura:
  index.html      -> estructura de la pagina
  css/style.css   -> todos los estilos (tema "Salon de Banderines")
  js/data.js      -> datos de los 12 campeonatos (simula la base de datos)
  js/app.js       -> logica que pinta los banderines y las tarjetas

Como desplegarla gratis:
  - Netlify (netlify.com): arrastra la carpeta completa a "Deploys".
  - Vercel (vercel.com): conecta el repo de GitHub o sube la carpeta.
  - GitHub Pages: sube esta carpeta a un repo y activa Pages en Settings.

Cualquiera de las tres te da un dominio gratis tipo:
  tuproyecto.netlify.app / tuproyecto.vercel.app / usuario.github.io

Para personalizar:
  - Agrega mas campeonatos copiando un objeto dentro del arreglo
    CAMPEONATOS en js/data.js (sigue exactamente el mismo formato).
  - Los colores de cada banderin salen del campo "color" de cada equipo.
