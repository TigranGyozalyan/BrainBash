package am.aca.quiz_software.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sub_category")
public class SubCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_name", unique = true,nullable = false)
    private String typeName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id",insertable = false,updatable = false)
    private CategoryEntity category;

    @OneToMany(mappedBy = "subCategory",cascade = CascadeType.ALL)
    private List<TopicEntity> topicEntityList;

    public SubCategoryEntity(){

    }

    public SubCategoryEntity(String typeName, CategoryEntity category, List<TopicEntity> topicEntityList) {
        this.typeName = typeName;
        this.category = category;
        this.topicEntityList = topicEntityList;
    }

    public Long getId() {
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

    public void setId(long id) {
        this.id = id;
    }

    public List<TopicEntity> getTopicEntityList() {
        return topicEntityList;
    }

    public void setTopicEntityList(List<TopicEntity> topicEntityList) {
        this.topicEntityList = topicEntityList;
    }

    @Override
    public String toString() {
        return "SubCategoryEntity{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", category=" + category +
                ", topicEntityList=" + topicEntityList +
                '}';
    }
}
