package com.luz.library;

import com.luz.library.models.*;
import com.luz.library.repositories.BookRepository;
import com.luz.library.repositories.UserBookRepository;
import com.luz.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEnconder;


	@Bean
	public CommandLineRunner initData(UserRepository userRepository,
									  BookRepository bookRepository,
									  UserBookRepository userBookRepository) {
		return (args) -> {

			//users
			User luz = User.builder()
					.firstName("Luz")
					.lastName("Brito")
					.email("britomluz@gmail.com")
					.password(passwordEnconder.encode("luz123"))
					.role(UserRole.ADMIN)
					.build();
			userRepository.save(luz);

			User marcos = User.builder()
					.firstName("Marcos")
					.lastName("Aguirre")
					.email("marcosa@gmail.com")
					.password(passwordEnconder.encode("marcos123"))
					.role(UserRole.USER)
					.build();
			userRepository.save(marcos);


			//books
			Book book = Book.builder()
					.titulo("Rayuela".toUpperCase())
					.autor("Julio Cortazar")
					.editorial("Alfaguara")
					.categoria("Novela")
					.precio(100D)
					.fechaLanzamiento(LocalDate.parse("1963-06-28"))
					.portada("https://res.cloudinary.com/luz-brito/image/upload/v1641250976/library/rayuela_pfdg14.jpg")
					.resenia("Plena de ambición literaria y vital, renovadora de las herramientas narrativas, destructora de lo establecido y buscadora de la raíz de la poesía.")
					.bestSeller(Boolean.FALSE)
					.build();
			bookRepository.save(book);

			Book book1 = Book.builder()
					.titulo("La Ladrona de Libros".toUpperCase())
					.autor("Markus Zusak")
					.editorial("DelBolsillo")
					.categoria("Literatura")
					.precio(127D)
					.fechaLanzamiento(LocalDate.parse("2015-04-08"))
					.portada("https://res.cloudinary.com/luz-brito/image/upload/v1641250976/library/ladrona-de-libros_fwcj5j.jpg")
					.resenia("Una novela preciosa, tremendamente humana y emocionante, que describe las peripecias de una niña alemana de nueve años desde que es dada en adopción por su madre hasta el final de la II Guerra Mundial.")
					.bestSeller(Boolean.FALSE)
					.build();
			bookRepository.save(book1);

			Book book2 = Book.builder()
					.titulo("La Tía Cosima".toUpperCase())
					.autor("Florencia Bonelli")
					.editorial("Suma de Letras")
					.categoria("Ficcion")
					.precio(117D)
					.fechaLanzamiento(LocalDate.parse("2021-10-16"))
					.portada("https://res.cloudinary.com/luz-brito/image/upload/v1641267550/library/tia-cosima_r7ykrd.jpg")
					.resenia("¿Puede el amor regalar una segunda oportunidad? Cósima es una mujer en la plenitud de la vida. Psicóloga especializada en el tratamiento del autismo infantil, posee una fundación de enorme prestigio, donde se respira un ambiente cuidado y buen humor. ")
					.bestSeller(Boolean.FALSE)
					.build();
			bookRepository.save(book2);

			Book book3 = Book.builder()
					.titulo("El Señor de los Anillos".toUpperCase())
					.autor("J.R.R Tolkien")
					.editorial("Minotauro")
					.categoria("Literatura")
					.precio(145D)
					.fechaLanzamiento(LocalDate.parse("2001-06-20"))
					.portada("https://res.cloudinary.com/luz-brito/image/upload/v1641267550/library/senior-anillos_wtdguu.jpg")
					.resenia("En la adormecida e idílica Comarca, un joven hobbit recibe un encargo: custodiar el Anillo Único y emprender el viaje para su destrucción en las Grietas del Destino.")
					.bestSeller(Boolean.TRUE)
					.build();
			bookRepository.save(book3);

			Book book4 = Book.builder()
					.titulo("El dia que Dejo de Nevar en Alaska".toUpperCase())
					.autor("Alice Kellen")
					.editorial("Titania")
					.categoria("Ficcion")
					.precio(95D)
					.fechaLanzamiento(LocalDate.parse("2016-08-28"))
					.portada("https://res.cloudinary.com/luz-brito/image/upload/v1641267550/library/dejo-de-nevar_w8zlyv.png")
					.resenia("Un chico con el corazón de hielo. Una chica que huye de sí misma. Dos destinos que se cruzan. Heather cree que sólo hay tres cosas que sabe hacer: atraer problemas, salir huyendo y correr.")
					.bestSeller(Boolean.FALSE)
					.build();
			bookRepository.save(book4);


			Book book5 = Book.builder()
					.titulo("Harry Potter y la Camara Secreta".toUpperCase())
					.autor("J.K. Rowling")
					.editorial("Salamandra")
					.categoria("Fantasia")
					.precio(130D)
					.fechaLanzamiento(LocalDate.parse("2000-04-23"))
					.portada("https://res.cloudinary.com/luz-brito/image/upload/v1641270207/library/camara-secreta_wlthux.jpg")
					.resenia("Tras derrotar una vez más a lord Voldemort, su siniestro enemigo en Harry Potter y la piedra filosofal, Harry espera impaciente en casa de sus insoportables tíos el inicio del segundo curso del Colegio Hogwarts de Magia y Hechicería.")
					.bestSeller(Boolean.TRUE)
					.build();
			bookRepository.save(book5);

			Book book6 = Book.builder()
					.titulo("La Cancion de Aquiles".toUpperCase())
					.autor("Madeleine Miller")
					.editorial("Adn Editores")
					.categoria("Literatura")
					.precio(187D)
					.fechaLanzamiento(LocalDate.parse("2009-10-20"))
					.portada("https://res.cloudinary.com/luz-brito/image/upload/v1641269248/library/aquiles_usejhv.jpg")
					.resenia("Grecia en la era de los héroes. Patroclo, un príncipe joven y torpe, ha sido exiliado al reino de Ftía, donde vive a la sombra del rey Peleo y su hijo divino, Aquiles.")
					.bestSeller(Boolean.FALSE)
					.build();
			bookRepository.save(book6);

			Book book7 = Book.builder()
					.titulo("Notas al pie".toUpperCase())
					.autor("Alejandro Dolina")
					.editorial("Planeta")
					.categoria("Novela")
					.precio(213D)
					.fechaLanzamiento(LocalDate.parse("2014-09-03"))
					.portada("https://res.cloudinary.com/luz-brito/image/upload/v1641269248/library/notas-al-pie_egyn4z.jpg")
					.resenia("Un universo con sus propias reglas que se despliega frente al lector como un juego de cajas chinas.")
					.bestSeller(Boolean.TRUE)
					.build();
			bookRepository.save(book7);

			Book book8 = Book.builder()
					.titulo("Asylum".toUpperCase())
					.autor("Madeleine Roux")
					.editorial("V&R Editoras")
					.categoria("Ficcion")
					.precio(87D)
					.fechaLanzamiento(LocalDate.parse("2017-12-20"))
					.portada("https://res.cloudinary.com/luz-brito/image/upload/v1641269248/library/asylum_bivn1g.jpg")
					.resenia("Para Dan Crawford, el programa de verano para alumnos sobresalientes es una oportunidad única, antes de asistir a la universidad. Sus amigos nunca comprendieron su fascinación por la historia y la ciencia.")
					.bestSeller(Boolean.FALSE)
					.build();
			bookRepository.save(book8);

			Book book9 = Book.builder()
					.titulo("Nunca".toUpperCase())
					.autor("Ken Follett")
					.editorial("Plaza y Janes")
					.categoria("Ficcion")
					.precio(163D)
					.fechaLanzamiento(LocalDate.parse("2008-01-30"))
					.portada("https://res.cloudinary.com/luz-brito/image/upload/v1641269248/library/nunca_c5lxfk.png")
					.resenia("En el desierto del Sáhara, dos agentes de inteligencia siguen la pista a un poderoso grupo terrorista arriesgando sus vidas -y, cuando se enamoran perdidamente, sus carreras- a cada paso.")
					.bestSeller(Boolean.TRUE)
					.build();
			bookRepository.save(book9);

			Book book10 = Book.builder()
					.titulo("El italiano".toUpperCase())
					.autor("Arturo Pérez Reverte")
					.editorial("Ficción")
					.categoria("Novela")
					.precio(150D)
					.fechaLanzamiento(LocalDate.parse("2018-07-15"))
					.portada("https://res.cloudinary.com/luz-brito/image/upload/v1641269248/library/el-italiano_vu7jhb.jpg")
					.resenia("En los años 1942 y 1943, durante la Segunda Guerra Mundial, buzos de combate italianos hundieron o dañaron catorce barcos aliados en Gibraltar y la bahía de Algeciras.")
					.bestSeller(Boolean.FALSE)
					.build();
			bookRepository.save(book10);

			Book book11 = Book.builder()
					.titulo("Asesino de Brujas - los Hijos del rey".toUpperCase())
					.autor("Shelby Mahurin")
					.editorial("Puck")
					.categoria("Juvenil")
					.precio(100D)
					.fechaLanzamiento(LocalDate.parse("2019-04-12"))
					.portada("https://res.cloudinary.com/luz-brito/image/upload/v1641269248/library/asesino-de-brujas_tmw17t.png")
					.resenia("Las brujas son más letales. Y el romance es aún más intenso. La bruja blanca; es perfecta para los fans de Sarah J. Maas. Adonde ella vaya; él irá. ")
					.bestSeller(Boolean.TRUE)
					.build();
			bookRepository.save(book11);

			Book book12 = Book.builder()
					.titulo("Antes de Diciembre".toUpperCase())
					.autor("Joana Marcus")
					.editorial("Montena")
					.categoria("Juvenil")
					.precio(117D)
					.fechaLanzamiento(LocalDate.parse("2017-10-05"))
					.portada("https://res.cloudinary.com/luz-brito/image/upload/v1641269248/library/antes-de-diciembre_vqpmou.jpg")
					.resenia("Para Jenna Brown, su primer año en la universidad supone alejarse de su familia y de sus amigos y enfrentarse al mundo por primera vez en su vida. ")
					.bestSeller(Boolean.TRUE)
					.build();
			bookRepository.save(book12);

			Book book13 = Book.builder()
					.titulo("Still with me".toUpperCase())
					.autor("Lily Del Pilar")
					.editorial("Planeta")
					.categoria("Juvenil")
					.precio(174D)
					.fechaLanzamiento(LocalDate.parse("2004-07-13"))
					.portada("https://res.cloudinary.com/luz-brito/image/upload/v1641269248/library/still-with-me_fagoad.jpg")
					.resenia("El oficial Jong Sungguk jamás imaginó que, en una tarde de lluvia, encontraría a un chico llamado Moon Daehyun atrapado en el ático de una casa de clase media en la ciudad de Daegu, Corea del Sur.")
					.bestSeller(Boolean.FALSE)
					.build();
			bookRepository.save(book13);


			Book book14 = Book.builder()
					.titulo("SAGA ESCUADRON 3 - CITONICA".toUpperCase())
					.autor("Brandon Sanderson")
					.editorial("Salamandra")
					.categoria("Ficcion")
					.precio(137D)
					.fechaLanzamiento(LocalDate.parse("2010-11-15"))
					.portada("https://res.cloudinary.com/luz-brito/image/upload/v1641269248/library/citonica_j9sfa8.jpg")
					.resenia("La esperada continuación de Escuadrón y Estelar, la épica saga de Brandon Sanderson sobre una chica que viaja más allá de las estrellas para salvar el mundo que ama de la destrucción.")
					.bestSeller(Boolean.FALSE)
					.build();
			bookRepository.save(book14);


			Book book15 = Book.builder()
					.titulo("El Mundo de Hielo y Fuego".toUpperCase())
					.autor("George R.R. Martin")
					.editorial("Grijalbo Ilustradol")
					.categoria("Ficción")
					.precio(196D)
					.fechaLanzamiento(LocalDate.parse("2003-08-13"))
					.portada("https://res.cloudinary.com/luz-brito/image/upload/v1641269248/library/mundo-hielo-fuego_vgo24l.jpg")
					.resenia("Este libro magníficamente ilustrado es una historia completa de los Siete Reinos, animada por vibrantes descripciones de las épicas batallas, enconadas rivalidades y audaces rebeliones.")
					.bestSeller(Boolean.TRUE)
					.build();
			bookRepository.save(book15);

			Book book16 = Book.builder()
					.titulo("Angela Merkel: La Fisica del Poder".toUpperCase())
					.autor("Patricia Salazar Figueroa - Christina Mendoza Weber")
					.editorial("Intermedio")
					.categoria("Biografia")
					.precio(137D)
					.fechaLanzamiento(LocalDate.parse("2013-04-22"))
					.portada("https://res.cloudinary.com/luz-brito/image/upload/v1641269248/library/fisica-poder_vuvtrd.jpg")
					.resenia("En pleno siglo XXI, era en la cual el empoderamiento femenino y la paridad entre hombres y mujeres son temas del día a día.")
					.bestSeller(Boolean.FALSE)
					.build();
			bookRepository.save(book16);

			Book book17 = Book.builder()
					.titulo("El Codigo da Vinci".toUpperCase())
					.autor("Dan Brown")
					.editorial("Planeta")
					.categoria("Ficcion")
					.precio(126D)
					.fechaLanzamiento(LocalDate.parse("2016-09-10"))
					.portada("https://res.cloudinary.com/luz-brito/image/upload/v1641278142/library/da-vinci_y6zp7y.jpg")
					.resenia("Un secreto milenario en peligro. Enigmas codificados por resolver. Una carrera de vida o muerte.   La mayor conspiración de los últimos dos mil años está a punto de ser develada. ¿conseguirán nuestros protagonistas impedirlo?")
					.bestSeller(Boolean.FALSE)
					.build();
			bookRepository.save(book17);

			Book book18 = Book.builder()
					.titulo("La Metamorfosis".toUpperCase())
					.autor("Franz Kafka")
					.editorial("Del Fondo")
					.categoria("Ficcion")
					.precio(131D)
					.fechaLanzamiento(LocalDate.parse("2005-12-20"))
					.portada("https://res.cloudinary.com/luz-brito/image/upload/v1641278142/library/metamorfosis_qg0dnc.jpg")
					.resenia("La metamorfosis es un relato dividido en tres partes, donde se narra la transformación de Gregorio Samsa, un viajante de comercio de telas, en un monstruoso insecto, y el impacto que tendrá este acontecimiento no solo en su vida.")
					.bestSeller(Boolean.FALSE)
					.build();
			bookRepository.save(book18);

			Book book19 = Book.builder()
					.titulo("La Magia de tu Casa".toUpperCase())
					.autor("Rosa María Cifuentes Castañeda")
					.editorial("Diana")
					.categoria("Decoracion")
					.precio(126D)
					.fechaLanzamiento(LocalDate.parse("2009-02-25"))
					.portada("https://res.cloudinary.com/luz-brito/image/upload/v1641278142/library/magia-casa_iysgsx.jpg")
					.resenia("Tus ambientes son el reflejo de tus emociones, personalidad y actitud ante la vida. La magia de tu casa cierra la trilogía de mis libros que combinan numerología, astrología y, ahora, feng shui para sanar tu hogar.")
					.bestSeller(Boolean.FALSE)
					.build();
			bookRepository.save(book19);


			UserBook userBook = UserBook.builder()
					.titulo(book5.getTitulo())
					.autor(book5.getAutor())
					.user(luz)
					.book(book5)
					.build();
			userBookRepository.save(userBook);

			UserBook userBook1 = UserBook.builder()
					.titulo(book2.getTitulo())
					.autor(book2.getAutor())
					.user(luz)
					.book(book2)
					.build();
			userBookRepository.save(userBook1);

			UserBook userBook2 = UserBook.builder()
					.titulo(book7.getTitulo())
					.autor(book7.getAutor())
					.user(luz)
					.book(book7)
					.build();
			userBookRepository.save(userBook2);

			UserBook userBook3 = UserBook.builder()
					.titulo(book15.getTitulo())
					.autor(book15.getAutor())
					.user(luz)
					.book(book15)
					.build();


			userBookRepository.save(userBook3);


			/*
			luz.getUserBooks().add(userBook3);
			userRepository.save(luz);*/
			//userBookRepository.save(userBook3);
			//book.getUserBooks().add(userBook3);

			/*
			luz.getUserBooks().add(userBook3);
			userBook3.getUser().getUserBooks().add(userBook3);

			book.getUserBooks().add(userBook3);
			userBook3.getBook().getUserBooks().add(userBook3);

			userBookRepository.save(userBook3);
			userRepository.save(luz);
			bookRepository.save(book);*/

			//book.getUserBooks().add(userBook3);


		};




	}
}
