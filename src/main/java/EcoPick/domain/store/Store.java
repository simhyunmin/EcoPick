package EcoPick.domain.store;

import jakarta.persistence.*;


@Entity
public class Store {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String icon_src;
}
