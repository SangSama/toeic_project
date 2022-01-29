package com.sangtt.toeic_test.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A DetailsVocabularyUser.
 */
@Entity
@Table(name = "details_vocabulary_user")
public class DetailsVocabularyUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "vocabulary_id", nullable = false)
    private Long vocabularyId;

    @NotNull
    @Column(name = "status", nullable = false)
    private Long status;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    @ManyToOne
    @JsonIgnoreProperties(value = { "detailsVocabularyUsers", "vocabularyTopic" }, allowSetters = true)
    private VocabularyUser vocabularyUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DetailsVocabularyUser id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVocabularyId() {
        return this.vocabularyId;
    }

    public DetailsVocabularyUser vocabularyId(Long vocabularyId) {
        this.setVocabularyId(vocabularyId);
        return this;
    }

    public void setVocabularyId(Long vocabularyId) {
        this.vocabularyId = vocabularyId;
    }

    public Long getStatus() {
        return this.status;
    }

    public DetailsVocabularyUser status(Long status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public DetailsVocabularyUser createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public DetailsVocabularyUser updatedAt(LocalDate updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public VocabularyUser getVocabularyUser() {
        return this.vocabularyUser;
    }

    public void setVocabularyUser(VocabularyUser vocabularyUser) {
        this.vocabularyUser = vocabularyUser;
    }

    public DetailsVocabularyUser vocabularyUser(VocabularyUser vocabularyUser) {
        this.setVocabularyUser(vocabularyUser);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DetailsVocabularyUser)) {
            return false;
        }
        return id != null && id.equals(((DetailsVocabularyUser) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DetailsVocabularyUser{" +
            "id=" + getId() +
            ", vocabularyId=" + getVocabularyId() +
            ", status=" + getStatus() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
