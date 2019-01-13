package am.aca.quiz_software.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "type",unique = true,nullable = false)
    private String type;


    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<SubCategoryEntity> subCategoryEntityLists;


    public CategoryEntity(){

    }
    public CategoryEntity(String type, List<SubCategoryEntity> subCategoryEntityLists) {
        this.type = type;
        this.subCategoryEntityLists = subCategoryEntityLists;
    }

    public CategoryEntity(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
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
