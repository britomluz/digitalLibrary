package com.luz.library;

import com.luz.library.models.*;
import com.luz.library.repositories.BookRepository;
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
									  BookRepository bookRepository) {
		return (args) -> {

			//users
			User luz = new User("Luz", "Brito","britomluz@gmail.com", passwordEnconder.encode("luz123"), UserRole.ADMIN);
			userRepository.save(luz);

			User marcos = new User("Marcos", "Aguirre", "marcosa@gmail.com", passwordEnconder.encode("marcos123"), UserRole.USER);
			userRepository.save(marcos);

			//books
			Book book1 = new Book("Rayuela".toUpperCase(), "Julio Cortazar", "Alfaguara", "Novela", 100D, LocalDate.parse("1963-06-28"), "https://res.cloudinary.com/luz-brito/image/upload/v1641250976/library/rayuela_pfdg14.jpg", "Plena de ambición literaria y vital, renovadora de las herramientas narrativas, destructora de lo establecido y buscadora de la raíz de la poesía.", BookType.NOFAV, BookBestSeller.YES);
			bookRepository.save(book1);

			Book book2 = new Book("La Ladrona de Libros".toUpperCase(), "Markus Zusak","DelBolsillo", "Literatura", 127D, LocalDate.parse("2015-04-08"), "https://res.cloudinary.com/luz-brito/image/upload/v1641250976/library/ladrona-de-libros_fwcj5j.jpg", "Una novela preciosa, tremendamente humana y emocionante, que describe las peripecias de una niña alemana de nueve años desde que es dada en adopción por su madre hasta el final de la II Guerra Mundial.", BookType.FAV, BookBestSeller.NO);
			bookRepository.save(book2);

			Book book3 = new Book("La Tía Cosima".toUpperCase(), "Florencia Bonelli", "Suma de Letras", "Ficción", 117D, LocalDate.parse("2021-10-16"), "https://res.cloudinary.com/luz-brito/image/upload/v1641267550/library/tia-cosima_r7ykrd.jpg", "¿Puede el amor regalar una segunda oportunidad? Cósima es una mujer en la plenitud de la vida. Psicóloga especializada en el tratamiento del autismo infantil, posee una fundación de enorme prestigio, donde se respira un ambiente cuidado y buen humor. ", BookType.FAV, BookBestSeller.YES);
			bookRepository.save(book3);

			Book book4 = new Book("El Señor de los Anillos".toUpperCase(), "J.R.R Tolkien", "Minotauro", "Literatura", 145D, LocalDate.parse("2001-06-20"), "https://res.cloudinary.com/luz-brito/image/upload/v1641267550/library/senior-anillos_wtdguu.jpg", "En la adormecida e idílica Comarca, un joven hobbit recibe un encargo: custodiar el Anillo Único y emprender el viaje para su destrucción en las Grietas del Destino.", BookType.NOFAV, BookBestSeller.YES);
			bookRepository.save(book4);

			Book book5 = new Book("El dia que Dejo de Nevar en Alaska".toUpperCase(), "Alice Kellen", "Titania", "Ficción", 95D, LocalDate.parse("2016-08-28"), "https://res.cloudinary.com/luz-brito/image/upload/v1641267550/library/dejo-de-nevar_w8zlyv.png", "Un chico con el corazón de hielo. Una chica que huye de sí misma. Dos destinos que se cruzan. Heather cree que sólo hay tres cosas que sabe hacer: atraer problemas, salir huyendo y correr.", BookType.NOFAV, BookBestSeller.NO);
			bookRepository.save(book5);

			Book book6 = new Book("Harry Potter y la Camara Secreta".toUpperCase(), "J.K. Rowling", "Salamandra", "Fantasía", 130D, LocalDate.parse("2000-04-23"), "https://res.cloudinary.com/luz-brito/image/upload/v1641270207/library/camara-secreta_wlthux.jpg", "Tras derrotar una vez más a lord Voldemort, su siniestro enemigo en Harry Potter y la piedra filosofal, Harry espera impaciente en casa de sus insoportables tíos el inicio del segundo curso del Colegio Hogwarts de Magia y Hechicería.", BookType.NOFAV, BookBestSeller.NO);
			bookRepository.save(book6);

			Book book7 = new Book("La Cancion de Aquiles".toUpperCase(), "Madeleine Miller", "Adn Editores", "Literatura", 187D, LocalDate.parse("2009-10-20"), "https://res.cloudinary.com/luz-brito/image/upload/v1641269248/library/aquiles_usejhv.jpg", "Grecia en la era de los héroes. Patroclo, un príncipe joven y torpe, ha sido exiliado al reino de Ftía, donde vive a la sombra del rey Peleo y su hijo divino, Aquiles.", BookType.NOFAV, BookBestSeller.NO);
			bookRepository.save(book7);

			Book book8 = new Book("Notas al pie".toUpperCase(), "Alejandro Dolina", "Planeta", "Novela", 213D, LocalDate.parse("2014-09-03"), "https://res.cloudinary.com/luz-brito/image/upload/v1641269248/library/notas-al-pie_egyn4z.jpg", "Un universo con sus propias reglas que se despliega frente al lector como un juego de cajas chinas.", BookType.NOFAV, BookBestSeller.NO);
			bookRepository.save(book8);

			Book book9 = new Book("Asylum".toUpperCase(), "Madeleine Roux", "V&R Editoras", "Ficción", 87D, LocalDate.parse("2017-12-20"), "https://res.cloudinary.com/luz-brito/image/upload/v1641269248/library/asylum_bivn1g.jpg", "Para Dan Crawford, el programa de verano para alumnos sobresalientes es una oportunidad única, antes de asistir a la universidad. Sus amigos nunca comprendieron su fascinación por la historia y la ciencia.", BookType.NOFAV, BookBestSeller.NO);
			bookRepository.save(book9);

			Book book10 = new Book("Nunca".toUpperCase(), "Ken Follett", "Plaza y Janes", "Ficción", 87D, LocalDate.parse("2008-01-30"), "https://res.cloudinary.com/luz-brito/image/upload/v1641269248/library/nunca_c5lxfk.png", "En el desierto del Sáhara, dos agentes de inteligencia siguen la pista a un poderoso grupo terrorista arriesgando sus vidas -y, cuando se enamoran perdidamente, sus carreras- a cada paso.", BookType.NOFAV, BookBestSeller.YES);
			bookRepository.save(book10);

			Book book11 = new Book("El italiano".toUpperCase(), "Arturo Pérez-Reverte ", "Alfaguara", "Ficción", 87D, LocalDate.parse("2018-07-15"), "https://res.cloudinary.com/luz-brito/image/upload/v1641269248/library/el-italiano_vu7jhb.jpg", "En los años 1942 y 1943, durante la Segunda Guerra Mundial, buzos de combate italianos hundieron o dañaron catorce barcos aliados en Gibraltar y la bahía de Algeciras.", BookType.NOFAV, BookBestSeller.YES);
			bookRepository.save(book11);

			Book book12 = new Book("Asesino de Brujas - los Hijos del rey".toUpperCase(), "Mahurin, Shelby ", "Puck", "Juvenil", 87D, LocalDate.parse("2019-04-12"), "https://res.cloudinary.com/luz-brito/image/upload/v1641269248/library/asesino-de-brujas_tmw17t.png", "Las brujas son más letales. Y el romance es aún más intenso. La bruja blanca; es perfecta para los fans de Sarah J. Maas. Adonde ella vaya; él irá. ", BookType.NOFAV, BookBestSeller.YES);
			bookRepository.save(book12);

			Book book13 = new Book("Antes de Diciembre".toUpperCase(), "Joana Marcus ", "Montena", "Juvenil", 87D, LocalDate.parse("2017-10-05"), "https://res.cloudinary.com/luz-brito/image/upload/v1641269248/library/antes-de-diciembre_vqpmou.jpg", "Para Jenna Brown, su primer año en la universidad supone alejarse de su familia y de sus amigos y enfrentarse al mundo por primera vez en su vida. ", BookType.NOFAV, BookBestSeller.YES);
			bookRepository.save(book13);

			Book book14 = new Book("Still with me".toUpperCase(), "Lily Del Pilar", "Planeta", "Juvenil", 87D, LocalDate.parse("2004-07-13"), "https://res.cloudinary.com/luz-brito/image/upload/v1641269248/library/still-with-me_fagoad.jpg", "El oficial Jong Sungguk jamás imaginó que, en una tarde de lluvia, encontraría a un chico llamado Moon Daehyun atrapado en el ático de una casa de clase media en la ciudad de Daegu, Corea del Sur.", BookType.NOFAV, BookBestSeller.NO);
			bookRepository.save(book14);

			Book book15 = new Book("SAGA ESCUADRON 3 - CITONICA".toUpperCase(), "Sanderson, Brandon ", "Salamandra", "Ficción", 87D, LocalDate.parse("2010-11-15"), "https://res.cloudinary.com/luz-brito/image/upload/v1641269248/library/citonica_j9sfa8.jpg", "La esperada continuación de Escuadrón y Estelar, la épica saga de Brandon Sanderson sobre una chica que viaja más allá de las estrellas para salvar el mundo que ama de la destrucción.", BookType.NOFAV,BookBestSeller.NO);
			bookRepository.save(book15);

			Book book16 = new Book("El Mundo de Hielo y Fuego".toUpperCase(), "George R.R. Martin", "Grijalbo Ilustradol", "Ficción", 87D, LocalDate.parse("2003-08-13"), "https://res.cloudinary.com/luz-brito/image/upload/v1641269248/library/mundo-hielo-fuego_vgo24l.jpg", "Este libro magníficamente ilustrado es una historia completa de los Siete Reinos, animada por vibrantes descripciones de las épicas batallas, enconadas rivalidades y audaces rebeliones.", BookType.NOFAV,BookBestSeller.YES);
			bookRepository.save(book16);

			Book book17 = new Book("Angela Merkel: La Fisica del Poder".toUpperCase(), "Patricia Salazar Figueroa, Christina Mendoza Weber ", "Intermedio", "Biografía", 87D, LocalDate.parse("2013-04-22"), "https://res.cloudinary.com/luz-brito/image/upload/v1641269248/library/fisica-poder_vuvtrd.jpg", "En pleno siglo XXI, era en la cual el empoderamiento femenino y la paridad entre hombres y mujeres son temas del día a día.", BookType.NOFAV,BookBestSeller.NO);
			bookRepository.save(book17);

			Book book18 = new Book("El Codigo da Vinci".toUpperCase(), "Dan Brown", "Planeta", "Ficción", 87D, LocalDate.parse("2016-09-10"), "https://res.cloudinary.com/luz-brito/image/upload/v1641278142/library/da-vinci_y6zp7y.jpg", "Un secreto milenario en peligro. Enigmas codificados por resolver. Una carrera de vida o muerte.   La mayor conspiración de los últimos dos mil años está a punto de ser develada. ¿conseguirán nuestros protagonistas impedirlo? ", BookType.NOFAV,BookBestSeller.NO);
			bookRepository.save(book18);

			Book book19 = new Book("La Metamorfosis".toUpperCase(), "Franz Kafka", "Del Fondo", "Ficción", 87D, LocalDate.parse("2005-12-20"), "https://res.cloudinary.com/luz-brito/image/upload/v1641278142/library/metamorfosis_qg0dnc.jpg", "La metamorfosis es un relato dividido en tres partes, donde se narra la transformación de Gregorio Samsa, un viajante de comercio de telas, en un monstruoso insecto, y el impacto que tendrá este acontecimiento no solo en su vida.", BookType.NOFAV,BookBestSeller.NO);
			bookRepository.save(book19);

			Book book20 = new Book("La Magia de tu Casa".toUpperCase(), "Rosa María Cifuentes Castañeda", "Diana", "Decoración", 87D, LocalDate.parse("2009-02-25"), "https://res.cloudinary.com/luz-brito/image/upload/v1641278142/library/magia-casa_iysgsx.jpg", "Tus ambientes son el reflejo de tus emociones, personalidad y actitud ante la vida. La magia de tu casa cierra la trilogía de mis libros que combinan numerología, astrología y, ahora, feng shui para sanar tu hogar.", BookType.NOFAV,BookBestSeller.NO);
			bookRepository.save(book20);






		};
	}
}
