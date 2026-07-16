# 🏆 NBA Wiki — Salón de Banderines

Una wiki interactiva con los **80 campeonatos de la NBA**, desde 1947 hasta hoy: el equipo campeón de cada año, su rival en la final, el resultado de la serie, el MVP de Finales y el roster estelar — todo presentado como un salón de banderines de arena, con animaciones y un sistema de cuentas donde puedes elegir tu equipo favorito y ganar insignias.

Construido en dos versiones con el mismo diseño:

| Versión | Tecnología | Demo |
|---|---|---|
| 🌐 Estática | HTML + CSS + JS puro | [nba-wiki-bryan-pgweb.netlify.app](https://nba-wiki-bryan-pgweb.netlify.app/) |
| ☕ Dinámica | Servlets + JSP + JDBC | [nba-wiki.onrender.com](https://nba-wiki.onrender.com) |

---

## ✨ Características

- **Histórico completo (1947–2026)** — las 80 finales de la NBA, incluyendo franquicias con sus nombres de época (Minneapolis Lakers, Fort Wayne Pistons, Syracuse Nationals...)
- **Filtros** por conferencia y por década
- **Cuentas de usuario** con registro, login y sesión (contraseñas nunca en texto plano)
- **Favoritos** — marca los campeonatos que más te gustan
- **Perfil personalizado** — elige tu equipo favorito y mira sus estadísticas reales (títulos totales, primer y último campeonato), y gana insignias según tu actividad
- **Animaciones** — entrada escalonada de tarjetas y banderines, hover states, transiciones suaves
- **Base de datos real** vía JDBC (H2 embebida por defecto, sin depender de ningún hosting externo — también soporta MySQL si prefieres tu propio servidor)

## 🛠️ Stack tecnológico

**Estática:** HTML5, CSS3 (animaciones nativas, variables CSS), JavaScript vanilla
**Dinámica:** Java, Servlets, JSP + JSTL, JDBC, Maven, H2 / MySQL
**Diseño:** tipografía Anton + Inter, tema oscuro inspirado en un arena de noche con banderines retirados

## 📁 Estructura del proyecto

```
nba-wiki/
├── static/                    → Versión HTML/CSS/JS
│   ├── index.html
│   ├── css/style.css
│   ├── js/data.js             (datos de los 80 campeonatos)
│   ├── js/app.js
│   └── img/logos/             (logos de equipos — ver LEEME.txt)
│
├── javaweb/                   → Versión Servlets + JSP + JDBC (Maven)
│   ├── pom.xml
│   ├── src/main/java/...      (Servlets y modelos)
│   ├── src/main/webapp/...    (JSP, CSS, imágenes)
│   ├── src/main/resources/    (script SQL para H2, autoejecutable)
│   ├── Dockerfile             (para desplegar en Render)
│   └── README.txt             (guía detallada de instalación y arquitectura)
│
├── database/
│   └── nba_wiki.sql           (esquema completo para MySQL)
│
└── README.md                  → estás aquí
```

## 🚀 Cómo verlo funcionando

- **Versión estática:** abre `static/index.html` en tu navegador, o entra al link de Netlify de arriba.
- **Versión JavaWeb:** requiere Java + Maven. Instrucciones paso a paso en [`javaweb/README.txt`](./javaweb/README.txt), o entra directo al link de Render de arriba.

## 🗄️ Base de datos

El esquema completo (equipos, jugadores, campeonatos, roster, usuarios, favoritos) está en [`database/nba_wiki.sql`](./database/nba_wiki.sql). La versión JavaWeb usa una copia embebida (H2) que se autogenera al arrancar, así que no hace falta instalar nada aparte para probarla.

---

**Proyecto académico** — Programación Web, Universidad de Guayaquil (FCMF)
Autor: Bryan Quiñonez Spooner
