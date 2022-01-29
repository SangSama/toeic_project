package com.sangtt.toeic_test.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Vocabulary.
 */
@Entity
@Table(name = "vocabulary")
public class Vocabulary implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "word", nullable = false)
    private String word;

    @NotNull
    @Column(name = "mean", nullable = false)
    private String mean;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    @ManyToOne
    @JsonIgnoreProperties(value = { "vocabularyUsers", "vocabularies" }, allowSetters = true)
    private VocabularyTopic vocabularyTopic;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Vocabulary id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return this.word;
    }

    public Vocabulary word(String word) {
        this.setWord(word);
        return this;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMean() {
        return this.mean;
    }

    public Vocabulary mean(String mean) {
        this.setMean(mean);
        return this;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public Vocabulary createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public Vocabulary updatedAt(LocalDate updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public VocabularyTopic getVocabularyTopic() {
        return this.vocabularyTopic;
    }

    public void setVocabularyTopic(VocabularyTopic vocabularyTopic) {
        this.vocabularyTopic = vocabularyTopic;
    }

    public Vocabulary vocabularyTopic(VocabularyTopic vocabularyTopic) {
        this.setVocabularyTopic(vocabularyTopic);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vocabulary)) {
            return false;
        }
        return id != null && id.equals(((Vocabulary) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Vocabulary{" +
            "id=" + getId() +
            ", word='" + getWord() + "'" +
            ", mean='" + getMean() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
