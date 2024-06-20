package org.example.it355pz.model;
import jakarta.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "hospital", schema = "it355pz", catalog = "")
public class HospitalEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "location")
    private String location;
    @Basic
    @Column(name = "img")
    private String img;
    @Basic
    @Column(name = "capacity")
    private Integer capacity;

    // Default constructor
    public HospitalEntity() {
    }

    // Parameterized constructor
    public HospitalEntity(String name, String location, String img, Integer capacity) {
        this.name = name;
        this.location = location;
        this.img = img;
        this.capacity = capacity;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HospitalEntity that = (HospitalEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(location, that.location) && Objects.equals(img, that.img) && Objects.equals(capacity, that.capacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, img, capacity);
    }
}
