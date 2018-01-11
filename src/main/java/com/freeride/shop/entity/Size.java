package com.freeride.shop.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String size;
    private String type;

//    @ManyToMany(mappedBy = "availableSizes", fetch = FetchType.EAGER)
//    private List<Category> availableInCategory;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public List<Category> getAvailableInCategory() {
//        return availableInCategory;
//    }
//
//    public void setAvailableInCategory(List<Category> availableInCategory) {
//        this.availableInCategory = availableInCategory;
//    }

//    public String caregoriesToString() {
//        StringBuilder sb = new StringBuilder();
//        for (Category category : availableInCategory) {
//            sb.append(category.getName().toLowerCase()).append(" ");
//        }
//        return sb.toString().trim();
//    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Size size1 = (Size) o;

        if (size != null ? !size.equals(size1.size) : size1.size != null) return false;
        return type != null ? type.equals(size1.type) : size1.type == null;
    }

    @Override
    public int hashCode() {
        int result = size != null ? size.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
