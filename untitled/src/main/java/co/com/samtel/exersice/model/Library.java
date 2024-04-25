package co.com.samtel.exersice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Library {


    private List<Book> catalog = new ArrayList<>();

    private List<Book> catalogAvailable = new ArrayList<>();

    private HashMap<User, List<Book>> borrowedBook = new HashMap<>();


    /**
     * metodo encargado de insertar un libro
     * @param bookToSave
     * @return
     */
    public String addBookToCatalog(Book bookToSave) {
        String response = "Libro agregado al catálogo";
        Optional<Book> bookFind = findByName(catalog, bookToSave.getTitle());
        if (bookFind.isEmpty()) {
            catalog.add(bookToSave);
            catalogAvailable.add(bookToSave);
        } else
            response = "Libro ya existe en el catálogo";

        return response;

    }

    /**
     * metodo encargado de prestar un libro a un usuario en especifico
     * @param bookToLend
     * @param user
     * @return
     */
    public String lendBook(String bookToLend, User user) {
        String response = "Libro prestaado correctamente a " + user.getName();
        Optional<Book> bookFind = findByName(catalogAvailable, bookToLend);
        if (bookFind.isPresent()) {
            borrowedBook.computeIfAbsent(user, k -> new ArrayList<>()).add(bookFind.get());
            catalogAvailable.remove(bookFind.get());
        } else {
            response = "El libro no esta disponible";
        }

        return response;

    }

    /**
     * metodo encargado de hacer el proceso de devolucion de un libro con base en el nombre y en el id del usuario
     * @param bookToLend
     * @param userId
     * @return
     */
    public String returnBook(String bookToLend, Integer userId) {
        String response = "Libro devuelto correctamente  ";
        User user = User.builder().id(userId).build();
        if (borrowedBook.containsKey(user)) {
            Optional<Book> book = findByName(borrowedBook.get(user), bookToLend);
            if (book.isPresent()) {
                catalogAvailable.add(book.get());
                borrowedBook.get(user).remove(book.get());
            } else {
                response = "El usuario no posee este libro";
            }

        } else {
            response = "El usuario no existe";
        }

        return response;

    }

    /**
     *  metodo que junta todos los libros prestd oen una sola lista
     * @return List<Book>
     */
    public List<Book> getAllBorrowedBook(){
        List<Book> todosLosLibros = new ArrayList<>();
        borrowedBook.values().forEach(todosLosLibros::addAll);
        return todosLosLibros;
    }

    /**
     * metodo que se encarga de buscarun libro en una lista especifica por nombre
     * @param list
     * @param name
     * @return
     */
    private Optional<Book> findByName(List<Book> list, String name) {
        return list.stream().filter(book -> book.getTitle().equals(name)).findFirst();
    }

}
