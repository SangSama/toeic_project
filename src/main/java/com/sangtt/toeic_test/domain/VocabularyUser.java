package com.sangtt.toeic_test.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A VocabularyUser.
 */
@Entity
@Table(name = "vocabulary_user")
public class VocabularyUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "score")
    private Long score;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "vocabularyUser")
    @JsonIgnoreProperties(value = { "vocabularyUser" }, allowSetters = true)
    private Set<DetailsVocabularyUser> detailsVocabularyUsers = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "vocabularyUsers", "vocabularies" }, allowSetters = true)
    private VocabularyTopic vocabularyTopic;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public VocabularyUser id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public VocabularyUser userId(Long userId) {
        this.setUserId(userId);
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getScore() {
        return this.score;
    }

    public VocabularyUser score(Long score) {
        this.setScore(score);
        return this;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public VocabularyUser createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public VocabularyUser updatedAt(LocalDate updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<DetailsVocabularyUser> getDetailsVocabularyUsers() {
        return this.detailsVocabularyUsers;
    }

    public void setDetailsVocabularyUsers(Set<DetailsVocabularyUser> detailsVocabularyUsers) {
        if (this.detailsVocabularyUsers != null) {
            this.detailsVocabularyUsers.forEach(i -> i.setVocabularyUser(null));
        }
        if (detailsVocabularyUsers != null) {
            detailsVocabularyUsers.forEach(i -> i.setVocabularyUser(this));
        }
        this.detailsVocabularyUsers = detailsVocabularyUsers;
    }

    public VocabularyUser detailsVocabularyUsers(Set<DetailsVocabularyUser> detailsVocabularyUsers) {
        this.setDetailsVocabularyUsers(detailsVocabularyUsers);
        return this;
    }

    public VocabularyUser addDetailsVocabularyUser(DetailsVocabularyUser detailsVocabularyUser) {
        this.detailsVocabularyUsers.add(detailsVocabularyUser);
        detailsVocabularyUser.setVocabularyUser(this);
        return this;
    }

    public VocabularyUser removeDetailsVocabularyUser(DetailsVocabularyUser detailsVocabularyUser) {
        this.detailsVocabularyUsers.remove(detailsVocabularyUser);
        detailsVocabularyUser.setVocabularyUser(null);
        return this;
    }

    public VocabularyTopic getVocabularyTopic() {
        return this.vocabularyTopic;
    }

    public void setVocabularyTopic(VocabularyTopic vocabularyTopic) {
        this.vocabularyTopic = vocabularyTopic;
    }

    public VocabularyUser vocabularyTopic(VocabularyTopic vocabularyTopic) {
        this.setVocabularyTopic(vocabularyTopic);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VocabularyUser)) {
            return false;
        }
        return id != null && id.equals(((VocabularyUser) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VocabularyUser{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", score=" + getScore() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
