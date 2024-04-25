package co.com.samtel.exersice.model;

import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class User {


    private Integer id;
    private String name;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User usuario = (User) o;
        return Objects.equals(id, usuario.getId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
