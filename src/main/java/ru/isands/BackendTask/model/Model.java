package ru.isands.BackendTask.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter @Setter @ToString
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

    @Column(name = "type")
    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    @ToString.Exclude
    private Appliance appliance;

    //Computer
    @Column(name = "pc_category")
    private String computerCategory;

    @Column(name = "pc_processor_type")
    private String processorType;

    //Fridge
    @Column(name = "f_doors_number")
    private Integer numberOfDoors;

    @Column(name = "f_compressor_type")
    private String compressorType;

    //Phone
    @Column(name = "p_memory")
    private Integer memory;

    @Column(name = "p_cameras_number")
    private Integer numberOfCameras;

    //television
    @Column(name = "tv_category")
    private String televisionCategory;

    @Column(name = "tv_technology")
    private String televisionTechnology;

    //VacuumCleaner
    @Column(name = "vc_dust_bag_volume")
    private Float dustBagVolume;

    @Column(name = "vc_modes_number")
    private Integer numberOfModes;

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
