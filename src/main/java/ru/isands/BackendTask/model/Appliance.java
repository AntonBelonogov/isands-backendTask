package ru.isands.BackendTask.model;

import lombok.*;
import org.hibernate.Hibernate;
import ru.isands.BackendTask.converter.HashMapConverter;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Appliance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "is_online_order")
    private Boolean onlineOrder;

    @Column(name = "installment")
    private Boolean installment;

    @Convert(converter = HashMapConverter.class)
    @Column(name = "appliance_attributes")
    private Map<String, Object> applianceAttributes;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "appliance_id")
    private List<Model> models;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Appliance that = (Appliance) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
