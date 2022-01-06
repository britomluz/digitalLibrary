# digitalLibrary

Digital Library es una aplicacion hecha con Java, Spring, Spring Boot, Spring Security, JPA, Hibernate, Graddle.
En la misma se desarrolla una API REST que sirve de backend para desarrollar la aplicacion completa que ya está subida a Heroku.

Para ingresar a la aplicación se puede ingresar con el siguiente link

Se puede crear un usuario nuevo o loguearse con uno existente.
Se recomienda ingresar con el usuario Luz Brito el cual desde el backend tiene los permisos necesarios para realizar el CRUD.

email: britomluz@gmail.com
password: luz123

En la aplicacion se puede:

Como usuario común:
- Ver el listado de libros disponibles
- Agregar libros a la sección "Mis Libros" haciendo click en el botón de añadir a la biblioteca
(próximamente se agregara el carrito de compras y este botón será para comprar el libro)

Como usuario administrador:
- Ver el listado de libros
- Ingresar a la sección especial "Admin Panel" donde puede:
      - Ver en un listado los libros
      - Agregar uno o más libros
      - Editar uno o más libros
      - Eliminar uno o más libros
      
      
      
Para poder realizar estas acciones desde el back cuenta con las clases Book y User donde se establecen los atributos necesarios para poder desarrollar la app
con sus respectivos repositorios, servicios, DTOs y controladores.
 
La app cuenta una capa de seguridad con Spring Security por lo que es necesario ingresar como usuario "ADMIN" para realizar el CRUD.
 
En el BookController se encuentran los métodos GET, POST, PATCH y DELETE necesarios, comenzando la ruta para hacer la peticion en "/api"

Cuenta con jUnit para el testeo, la cual pasó con exito el test de los metodos principales.
