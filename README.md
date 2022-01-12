# digitalLibrary

Digital Library es una aplicacion hecha con:
BACKEND: Java, Spring, Spring Boot, Spring Security, JPA, Hibernate, Graddle y H2 console, Lombok.
FRONTEND: Frontend se utilizo HTML5, CSS3. Bootstrap5, Javascript y VueJs.
En la misma se desarrolla una API REST que sirve de backend para desarrollar la aplicacion completa que ya está subida a Heroku.


Para ingresar a la interfaz de la aplicación se puede ingresar con el siguiente link
https://dlibl.herokuapp.com/

Se puede crear un usuario nuevo o loguearse con uno existente.


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

Para correr la aplicacion primero se debe loguear con las siguientes credenciales:
-Recibe los datos por parametro
- email: britomluz@gmail.com
- password: luz123
- url: http://localhost:8080/api/login
- ejemplo peticion completa: http://localhost:8080/api/login?email=britomluz@gmail.com&password=luz123

Luego de estar logeuado las rutas para hacer el CRUD en son:

***********GET**********

OBTENER TODOS LOS LIBROS CON PAGINACIÓN, FILTROS Y ORDEN
- Recibe los datos por parametro
- url: http://localhost:8080/api/books
- Se puede elegir una pagina: elegir el ordenamiento de forma ascendente o descendente por id, titulo, autor, categoria, editorial o precio; se puede filtrar por titulo, autor, editorial, categoria y/o precio.
- peticion completa sin parametros(luego del igual se debe poner el parametro, si esta vacio devuelve la lista sin filtrar. El precio es el unico parametro que si debe estar asignado): http://localhost:8080/api/books?page=&size=&sort&filter%5Btitulo%5D=&filter%5Bautor%5D=&filter%5Beditorial%5D=&filter%5Bcategoria%5D=&filter%5Bprecio%5D=500
- ejemplo de peticion completa con filtro por categoria "ficcion" ordenado por "titulo" de forma descendente: http://localhost:8080/api/books?page=&size=&sort=titulo,desc&filter%5Btitulo%5D=&filter%5Bautor%5D=&filter%5Beditorial%5D=&filter%5Bcategoria%5D=ficcion&filter%5Bprecio%5D=500

OBTENER UN LIBRO EN PARTICULAR POR ID
- url: http://localhost:8080/api/books{id}
- ejemplo peticion completa: http://localhost:8080/api/books/3


***********POST**********

AGREGAR LIBRO  
- Recibe los datos en un JSON
- url: http://localhost:8080/api/books
- Ejemplo de JSON:
  {
  "titulo": "string",
  "autor": "string",
  "editorial": "string",
  "categoria": "string",
  "precio": "string",
  "fechaLanzamiento": "2004-07-24",
  "portada": "string",
  "resenia": "string"
}

AGREGAR UN LIBRO A LA BIBLIOTECA
- Recibe un id de un libro por parametro
- url: http://localhost:8080/api/books/userbook
- ejemplo: http://localhost:8080/api/books/userbook?bookId=2


***********PUT**********

EDITAR LIBRO
- Recibe los datos en un JSON y es necesario pasarle el id del libro en el path
- url: http://localhost:8080/api/books{id}
- ejemplo de peticion: http://localhost:8080/api/books/5
- Ejemplo de JSON:
  {
  "titulo": "string",
  "autor": "string",
  "editorial": "string",
  "categoria": "string",
  "precio": "string",
  "fechaLanzamiento": "2019-03-09",
  "portada": "string",
  "resenia": "string"
}


***********DELETE**********

BORRAR LIBRO
- Recibe el id del libro en el path
- url: http://localhost:8080/api/books{id}
- ejemplo: http://localhost:8080/api/books/12


***********PATCH**********

AGREGAR LIBRO A BESTSELLER
- Se edita la propiedad "bestSeller" el cual es un booleano
- Recibe un id de un libro por parametro
- url: http://localhost:8080/api/books/userbook
- ejemplo: http://localhost:8080/api/books/userbook?bookId=2




      
      
      

