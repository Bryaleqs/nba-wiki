# 🏀 NBA Wiki - Salón de Banderines

¡Hola! Este es mi repositorio para el proyecto final de Programación Web en la carrera de Tecnologías de la Información (Sexto semestre) de la Universidad de Guayaquil.

El proyecto es una wiki interactiva que documenta los campeonatos de la NBA desde 2015 hasta 2026. Para demostrar lo aprendido durante el curso, desarrollé la aplicación en dos versiones distintas manteniendo el mismo diseño visual.

## 🌐 Versión Estática (Frontend)
Esta es la presentación pura del lado del cliente, construida completamente con **HTML, CSS y JavaScript vanilla**. Los datos de los campeonatos se cargan dinámicamente simulando una respuesta con arreglos de JS.
* **Demo en vivo:** [Ver versión estática en Netlify](https://nba-wiki-bryan-pgweb.netlify.app/)

## ☕ Versión Java Web (Fullstack)
Esta es la implementación backend del proyecto. Está desarrollada en Java utilizando la arquitectura de **Servlets, JSP y JDBC**. 
Características principales:
* Sistema completo de registro e inicio de sesión de usuarios con contraseñas encriptadas.
* Funcionalidad interactiva para guardar campeonatos en "Favoritos".
* Base de datos relacional **H2 embebida**. (Se auto-construye al compilar el proyecto, por lo que no depende de servicios externos).
* **Demo en vivo:** [Ver versión Java en Render](https://nba-wiki.onrender.com/campeonatos) 
*(Nota: Al estar alojado en la capa gratuita de Render, el servidor entra en reposo por inactividad. Si demora en cargar la primera vez, solo dale unos 50 segundos para que despierte).*

## 📂 Estructura del Repositorio
* `/static` — Código fuente de la web estática.
* `/javaweb` — Proyecto Maven con la aplicación backend en Java.
* `/database` — Script SQL de referencia con el esquema completo (equipos, jugadores, campeonatos, usuarios y favoritos) por si se desea migrar a otro motor de base de datos en el futuro.

---
**Desarrollado por:** Bryan Alexander Quiñonez Spooner
