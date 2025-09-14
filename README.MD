# Learniverse
📚 Plataforma web para aprender y repasar conceptos de programacion
mediante secciones cortas, ejemplos practicos y videos explicativos.
## Descripcion
**Learniverse** es una biblioteca digital de programacion que organiza
conceptos de forma clara y concisa. Incluye explicaciones con 
ejemplo de codigo, descripciones breves y recursos de video para
reforzar el aprendizaje.
Este proyecto esta orientado a desarrolladores y estudiantes que buscan una 
referencia practica y precisa sobre problemas reales de programacion
en aplicaciones modernas.
## Tecnologias
- ☕ Java 17
- 🌱 Spring Boot 3.5.4
- 🎨 Thymeleaf
- 🐬 MySQL
- ⚙️ Gradle
## Instalacion y ejecucion
1. Clonar el repositorio:
```bash
git clone https://github.com/danielgomez-engineer/Learniverse.git
```
2. Configurar la base de datos MySQL en application.yml
```
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/learniverse?allowPublicKeyRetrieval=true&useSSL=false
    username: ${DB_USER:root}
    password: ${DB_PASSWORD:admin}
    driver-class-name: com.mysql.cj.jdbc.Driver

  thymeleaf:
    cache: false



  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

```
3. Ejecutar proyecto con Gradle
```bash
   ./gradlew bootRun
```
4. Acceder a la aplicacion
- Local: [http://localhost:8080](http://localhost:8080)
- Producción: [https://learniverse.onrender.com](https://learniverse.onrender.com) 

        
## Funcionalidades actuales
- Gestión de *topics*, *subtopics*, *contents* y *sections* (CRUD completo).
- Registro e inicio de sesión de usuarios con autenticación.
- Manejo de roles y permisos para diferentes tipos de usuario.
- Navegación entre secciones (anterior/siguiente).
- Búsqueda de subtemas dentro de un tema específico.
## Planes a futuro
- 🔍 Implementar búsqueda avanzada por título y etiquetas.
- ⚛️ Migrar el frontend a React para mejorar la experiencia de usuario.
- 🔗 Añadir API REST para integraciones con aplicaciones externas.
- ☁️ Desplegar en la nube con pipeline CI/CD automatizado.
## Autor
Desarrollado por **Daniel Felipe Gómez Ferreira**

- 🌐 Portafolio: *(próximamente)*
- 💼 LinkedIn: [linkedin.com/in/daniel-felipe-gómez-ferreira-050062276](https://www.linkedin.com/in/daniel-felipe-gómez-ferreira-050062276/)
- 📧 Email: danielf23.dev@gmail.com  
## Licencia
Este proyecto está bajo la licencia MIT.  
Si reutilizas este código, por favor mantén el crédito al autor original (**Daniel Felipe Gómez Ferreira**) y enlaza este repositorio.

