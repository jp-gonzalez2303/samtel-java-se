package co.com.samtel.exersice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Book {


    private String title;
    private String author;
    private Integer publicationYear;


}