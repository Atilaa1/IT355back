package org.example.it355pz.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sponsors", schema = "it355pz", catalog = "")
public class SponsorsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "img")
    private String img;
    @Basic
    @Column(name = "link")
    private String link;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SponsorsEntity that = (SponsorsEntity) o;
        return id == that.id && Objects.equals(img, that.img) && Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, img, link);
    }
}
