# 📚 Learniverse - Plataforma de Aprendizaje de Programación

## 🚀 Resumen del Proyecto

**Learniverse** es una biblioteca digital educativa desarrollada para organizar y servir contenido de programación de forma estructurada, interactiva y escalable.  
El sistema permite gestionar más de **100 conceptos de programación** mediante secciones, ejemplos y recursos multimedia.

Como **Arquitecto y Desarrollador Full Stack**, mi enfoque estuvo en el diseño de una base de datos robusta, la implementación de seguridad con Spring Security y la construcción de una arquitectura modular preparada para una futura migración a **React** y **API REST**.

---

## 🛠️ Stack Tecnológico Clave

Mi enfoque técnico se centra en un desarrollo **Java Full Stack moderno**, con herramientas que garantizan escalabilidad, seguridad y mantenibilidad.

| Categoría | Tecnologías / Herramientas |
|------------|-----------------------------|
| **Lenguaje** | ![Java](https://img.shields.io/badge/Java_17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white) |
| **Framework** | ![Spring Boot](https://img.shields.io/badge/Spring_Boot_3.5.4-6DB33F?style=for-the-badge&logo=springboot&logoColor=white) ![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white) |
| **Persistencia** | ![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white) ![JPA](https://img.shields.io/badge/JPA-4B8BBE?style=for-the-badge) |
| **Base de Datos** | ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white) |
| **Build Tool** | ![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white) |
| **Frontend** | ![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white) |
| **DevOps / Despliegue** | ![Render](https://img.shields.io/badge/Render-46E3B7?style=for-the-badge&logo=render&logoColor=white) ![CI/CD](https://img.shields.io/badge/CI%2FCD-181717?style=for-the-badge&logo=githubactions&logoColor=white) |

---

## ⚙️ Arquitectura y Logros Técnicos Destacados

### 1. Diseño y Estructura de Datos
- **Modelo relacional jerárquico** con entidades `Topic → Subtopic → Content → Section`, optimizado con **Hibernate/JPA**.  
- Validaciones y restricciones a nivel de entidad para asegurar integridad de los datos.  
- **Control de acceso seguro** con **Spring Security**, roles y permisos diferenciados (ADMIN / USER).

### 2. Arquitectura Modular y Escalable
- Separación clara de capas (**Controladores, Servicios, Repositorios**) bajo principios **SOLID**.  
- Estructura de código preparada para la futura exposición de una **API REST** y la migración del frontend a **React**.  
- Arquitectura limpia y desacoplada para facilitar la mantenibilidad y extensión.

### 3. Mantenimiento y Despliegue
- Mantenimiento activo y refactorización continua para mejorar calidad y legibilidad.  
- Despliegue automatizado en **Render** con integración Gradle.  
- Gestión de versiones y documentación técnica alineada con buenas prácticas de desarrollo profesional.

---

## 🚀 Funcionalidades Clave

- **Autenticación y Roles:** Registro e inicio de sesión de usuarios.  
- **Gestión de Contenido (CRUD):** Administración de temas, subtemas, contenidos y secciones.  
- **Navegación Dinámica:** Exploración por categorías y navegación secuencial entre secciones.  
- **Seguridad:** Restricciones de acceso basadas en roles y sesiones seguras.  

---

## 📈 Planes a Futuro (Crecimiento del Proyecto)

- ⚛️ **Migrar el frontend a React** para mejorar la experiencia del usuario (SPA).  
- 🔗 **Implementar API REST** para integración con aplicaciones externas.  
- ☁️ **Pipeline CI/CD** completo con pruebas automatizadas y despliegue continuo.  
- 🔍 **Búsqueda avanzada** por título, etiquetas y contenidos.

---

## 🖥️ Demostración en Producción

Puedes explorar la versión desplegada del proyecto aquí:  
🔗 **Demo URL:** [https://learniverse.onrender.com](https://learniverse.onrender.com)

---

## 📥 Cómo Ejecutar Localmente

### 1️⃣ Clonar el Repositorio
```bash
git clone https://github.com/danielgomez-engineer/Learniverse.git
cd Learniverse
