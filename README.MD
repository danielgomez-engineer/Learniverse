# Learniverse
Plataforma web para aprender y repasar conceptos de programacion
mediante secciones cortas, ejemplos practicos y videos 
explicativos
## Descripcion
**Learniverse** es una biblioteca digital de programacion que organiza
conceptos de forma clara y concisa. Incluye explicaciones con 
ejemplo de codigo, descripciones breves y recursos de video para
reforzar el aprendizaje.
Este proyecto esta orientado a desarrolladores que buscan una 
referencia practica y precisa sobre problemas reales de programacion
en aplicaciones modernas.
## Tecnologias
- Java 17
- Spring Boot 3.5.4
- Thymeleaf 
- MySQl
- Gradle
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
- Registro e inicio de sesión de usuarios.
- Manejo de roles y permisos.
- Funcionalidad para navegar entre secciones (anterior/siguiente).
- Métodos avanzados como la búsqueda de subtemas dentro de un tema específico.
## Planes a futuro
- Implementar búsqueda rápida por título y etiquetas.
- Migrar el frontend a React para una experiencia más dinámica.
- Añadir API REST para integrar con aplicaciones externas.
## Autor
Desarrollado por **Daniel Felipe Gómez Ferreira**

- 🌐 Portafolio: *(aquí pones el link cuando lo tengas)*
- 💼 LinkedIn: [linkedin.com/in/daniel-felipe-gómez-ferreira-050062276](https://www.linkedin.com/in/daniel-felipe-g%C3%B3mez-ferreira-050062276/)
- 📧 Email: danielf23.dev@gmail.com

