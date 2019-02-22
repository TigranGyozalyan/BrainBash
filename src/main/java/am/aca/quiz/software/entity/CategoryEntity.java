package am.aca.quiz.software.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", unique = true, nullable = false)
    private String type;


    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<SubCategoryEntity> subCategoryEntityLists = new ArrayList<>();


    public CategoryEntity() {
    }

    public CategoryEntity(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SubCategoryEntity> getSubCategoryEntityLists() {
        return subCategoryEntityLists;
    }

    public void setSubCategoryEntityLists(List<SubCategoryEntity> subCategoryEntityLists) {
        this.subCategoryEntityLists = subCategoryEntityLists;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
            "id=" + id +
            ", type='" + type + '\'' +
            '}';
    }


}
