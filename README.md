# NBA Wiki &middot; Salon de Banderines

Proyecto final de Programacion Web &mdash; Universidad de Guayaquil (FCMF)
Autor: Bryan Quinonez Spooner

Wiki de los campeonatos de la NBA (2015-2026), con dos implementaciones
del mismo diseno:

## 🌐 Version estatica &mdash; `/static`
HTML + CSS + JS puro. Datos simulados con un arreglo JS.
**Demo en vivo:** _pega aqui tu link de Netlify_

## ☕ Version JavaWeb &mdash; `/javaweb`
Servlets + JSP + JDBC, con base de datos H2 embebida (sin dependencias
externas), registro/login de usuarios y sistema de favoritos.
**Demo en vivo:** _pega aqui tu link de Render (si lo desplegaste)_
**Como correrlo localmente:** ver `javaweb/README.txt`

## 🗄️ Base de datos &mdash; `/database`
Script SQL (`nba_wiki.sql`) con el esquema completo: equipos, jugadores,
campeonatos, roster, usuarios y favoritos.

## Estructura
```
nba-wiki/
├── static/       -> version HTML/CSS/JS
├── javaweb/      -> version Servlets + JSP + JDBC (Maven)
├── database/     -> script SQL de referencia
└── README.md
```
