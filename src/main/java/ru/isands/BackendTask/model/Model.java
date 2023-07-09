package ru.isands.BackendTask.model;

import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;
import ru.isands.BackendTask.converter.HashMapConverter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "color")
    private String color;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "size")
    private Float size;

    @Column(name = "is_available")
    private Boolean available;

    @Convert(converter = HashMapConverter.class)
    @Column(name =  "model_attributes", columnDefinition = "json")
    private Map<String, Object> modelAttributes;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appliance_id")
    private Appliance appliance;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Model model = (Model) o;
        return id != null && Objects.equals(id, model.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
