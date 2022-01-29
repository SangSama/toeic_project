package com.sangtt.toeic_test.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A VocabularyTopic.
 */
@Entity
@Table(name = "vocabulary_topic")
public class VocabularyTopic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name_topic", length = 100, nullable = false)
    private String nameTopic;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "view", nullable = false)
    private Long view;

    @NotNull
    @Column(name = "test", nullable = false)
    private Long test;

    @Column(name = "level")
    private Long level;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "vocabularyTopic")
    @JsonIgnoreProperties(value = { "detailsVocabularyUsers", "vocabularyTopic" }, allowSetters = true)
    private Set<VocabularyUser> vocabularyUsers = new HashSet<>();

    @OneToMany(mappedBy = "vocabularyTopic")
    @JsonIgnoreProperties(value = { "vocabularyTopic" }, allowSetters = true)
    private Set<Vocabulary> vocabularies = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public VocabularyTopic id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameTopic() {
        return this.nameTopic;
    }

    public VocabularyTopic nameTopic(String nameTopic) {
        this.setNameTopic(nameTopic);
        return this;
    }

    public void setNameTopic(String nameTopic) {
        this.nameTopic = nameTopic;
    }

    public String getDescription() {
        return this.description;
    }

    public VocabularyTopic description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getView() {
        return this.view;
    }

    public VocabularyTopic view(Long view) {
        this.setView(view);
        return this;
    }

    public void setView(Long view) {
        this.view = view;
    }

    public Long getTest() {
        return this.test;
    }

    public VocabularyTopic test(Long test) {
        this.setTest(test);
        return this;
    }

    public void setTest(Long test) {
        this.test = test;
    }

    public Long getLevel() {
        return this.level;
    }

    public VocabularyTopic level(Long level) {
        this.setLevel(level);
        return this;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public VocabularyTopic createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public VocabularyTopic updatedAt(LocalDate updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<VocabularyUser> getVocabularyUsers() {
        return this.vocabularyUsers;
    }

    public void setVocabularyUsers(Set<VocabularyUser> vocabularyUsers) {
        if (this.vocabularyUsers != null) {
            this.vocabularyUsers.forEach(i -> i.setVocabularyTopic(null));
        }
        if (vocabularyUsers != null) {
            vocabularyUsers.forEach(i -> i.setVocabularyTopic(this));
        }
        this.vocabularyUsers = vocabularyUsers;
    }

    public VocabularyTopic vocabularyUsers(Set<VocabularyUser> vocabularyUsers) {
        this.setVocabularyUsers(vocabularyUsers);
        return this;
    }

    public VocabularyTopic addVocabularyUser(VocabularyUser vocabularyUser) {
        this.vocabularyUsers.add(vocabularyUser);
        vocabularyUser.setVocabularyTopic(this);
        return this;
    }

    public VocabularyTopic removeVocabularyUser(VocabularyUser vocabularyUser) {
        this.vocabularyUsers.remove(vocabularyUser);
        vocabularyUser.setVocabularyTopic(null);
        return this;
    }

    public Set<Vocabulary> getVocabularies() {
        return this.vocabularies;
    }

    public void setVocabularies(Set<Vocabulary> vocabularies) {
        if (this.vocabularies != null) {
            this.vocabularies.forEach(i -> i.setVocabularyTopic(null));
        }
        if (vocabularies != null) {
            vocabularies.forEach(i -> i.setVocabularyTopic(this));
        }
        this.vocabularies = vocabularies;
    }

    public VocabularyTopic vocabularies(Set<Vocabulary> vocabularies) {
        this.setVocabularies(vocabularies);
        return this;
    }

    public VocabularyTopic addVocabulary(Vocabulary vocabulary) {
        this.vocabularies.add(vocabulary);
        vocabulary.setVocabularyTopic(this);
        return this;
    }

    public VocabularyTopic removeVocabulary(Vocabulary vocabulary) {
        this.vocabularies.remove(vocabulary);
        vocabulary.setVocabularyTopic(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VocabularyTopic)) {
            return false;
        }
        return id != null && id.equals(((VocabularyTopic) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VocabularyTopic{" +
            "id=" + getId() +
            ", nameTopic='" + getNameTopic() + "'" +
            ", description='" + getDescription() + "'" +
            ", view=" + getView() +
            ", test=" + getTest() +
            ", level=" + getLevel() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
