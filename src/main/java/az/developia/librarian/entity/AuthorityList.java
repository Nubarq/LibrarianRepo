package az.developia.librarian.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "authority_list")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String authority;

    @Column(nullable = false)
    private Integer librarian;

    @Column(nullable = false)
    private Integer student;
}
