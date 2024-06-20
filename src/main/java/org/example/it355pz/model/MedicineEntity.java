package org.example.it355pz.model;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "medicine", schema = "it355pz", catalog = "")
public class MedicineEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "company")
    private String company;
    @Basic
    @Column(name = "dose")
    private String dose;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "img")
    private String img;

    public MedicineEntity() {
    }

    // Parameterized constructor
    public MedicineEntity(String name, String company, String dose, String description, String img) {
        this.name = name;
        this.company = company;
        this.dose = dose;
        this.description = description;
        this.img = img;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicineEntity that = (MedicineEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(company, that.company) && Objects.equals(dose, that.dose) && Objects.equals(description, that.description) && Objects.equals(img, that.img);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, company, dose, description, img);
    }
}
