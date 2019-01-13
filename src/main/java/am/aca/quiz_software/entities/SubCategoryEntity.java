package am.aca.quiz_software.entities;

import javax.persistence.*;

@Entity
@Table(name = "sub_category")
public class SubCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "type_name", unique = true,nullable = false)
    private String typeName;

    @ManyToOne
    CategoryEntity category;

    public SubCategoryEntity(){

    }

    public SubCategoryEntity(String typeName, CategoryEntity category) {
        this.typeName = typeName;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "SubCategoryEntity{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", category=" + category +
                '}';
    }
}
