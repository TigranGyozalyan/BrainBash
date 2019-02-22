package am.aca.quiz.software.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sub_category")

public class SubCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_name", unique = true, nullable = false)
    private String typeName;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL)
    private List<TopicEntity> topicEntityList = new ArrayList<>();

    public SubCategoryEntity() {

    }

    public SubCategoryEntity(String typeName, CategoryEntity category) {
        this.typeName = typeName;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
            '}';
    }
}
