package nl.novi.techiteasy1121.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class WallBracket {

    @Id
    @GeneratedValue
    private Long id;
    private String size;
    private Boolean adjustable;
    private String name;
    private Double price;

    // Dit is de target kant van de relatie. Er staat niks in de database
    @ManyToMany(mappedBy = "wallBrackets")
    List<Television> televisions;

    public Long getId() {
        return id;
    }

    public String getSize() {
        return size;
    }

    public Boolean getAdjustable() {
        return adjustable;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public List<Television> getTelevisions() {
        return televisions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setAdjustable(Boolean adjustable) {
        this.adjustable = adjustable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setTelevisions(List<Television> televisions) {
        this.televisions = televisions;
    }
}
