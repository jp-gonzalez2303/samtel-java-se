package co.com.samtel.exersice;

import co.com.samtel.exersice.model.Book;
import co.com.samtel.exersice.model.Library;
import co.com.samtel.exersice.model.User;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase main encargda de la ejecucion del programa
 */
public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Library library = new Library();

    /**
     * metodo que arranca la aplicacion
     * @param args
     */
    public static void main(String[] args) {
        Integer option = 0;
        System.out.println("Bienvenido al Sistema de Gestion de Biblioteca");
        System.out.println("1. Agregar Nuevo Libro");
        System.out.println("2. Prestar Libro");
        System.out.println("3. Devolver Libro");
        System.out.println("4. Mostrar Catalogo");
        System.out.println("5. Mostrar Libros Prestados");
        System.out.println("6. Salir");

        while (option != 6) {
            try {
                System.out.print("Por favor, seleccione una opción:");
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        addBookToCatalog();
                        break;
                    case 2:
                        lendBook();
                        break;
                    case 3:
                        returnBook();
                        break;
                    case 4:
                        System.out.println(library.getCatalog());
                        break;
                    case 5:
                        System.out.println(library.getAllBorrowedBook());
                        break;

                    default:
                        break;
                }
            } catch (InputMismatchException exception) {
                System.out.println("Debe ingresar un numero no una letra");
                scanner.next();
            }
        }


    }


    /**
     * metodo que se encarga de recibir la informacion para guardarla en el catalogo
     * @throws InputMismatchException
     */
    private static void addBookToCatalog() throws InputMismatchException{
        Book book = new Book();
        System.out.print("Ingrese el titulo del libro: ");
        book.setTitle(scanner.next());

        System.out.print("Ingrese el autor del libro: ");
        book.setAuthor(scanner.next());

        System.out.print("Ingrese el año de publicacion del libro: ");
        book.setPublicationYear(scanner.nextInt());

        System.out.println(library.addBookToCatalog(book));


    }

    /**
     * metodo que se encarga de recibir la informacion para prestar un libro y almacenar est oen la biblioteca
     * @throws InputMismatchException
     */
    private static void lendBook()  throws InputMismatchException {
        User user = new User();
        System.out.print("Ingrese el nombre del usuario: ");
        user.setName(scanner.next());

        System.out.print("Ingrese el identificador del usuario: ");
        user.setId(scanner.nextInt());

        System.out.print("Ingrese el titulo del libro a prestar: ");
        String book = scanner.next();

        System.out.println(library.lendBook(book, user));


    }

    /**
     * meotod encargad ode hacer el proceso de devolucio nde un libro
     *
     * @throws InputMismatchException
     */
    private static void returnBook()  throws InputMismatchException{


        System.out.print("Ingrese el identificador del usuario: ");
        Integer userId = (scanner.nextInt());

        System.out.print("Ingrese el titulo del libro a devolver: ");
        String book = scanner.next();

        System.out.println(library.returnBook(book, userId));


    }

}
