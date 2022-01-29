package com.sangtt.toeic_test.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sangtt.toeic_test.domain.Vocabulary} entity.
 */
public class VocabularyDTO implements Serializable {

    private Long id;

    @NotNull
    private String word;

    @NotNull
    private String mean;

    @NotNull
    private LocalDate createdAt;

    @NotNull
    private LocalDate updatedAt;

    private VocabularyTopicDTO vocabularyTopic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public VocabularyTopicDTO getVocabularyTopic() {
        return vocabularyTopic;
    }

    public void setVocabularyTopic(VocabularyTopicDTO vocabularyTopic) {
        this.vocabularyTopic = vocabularyTopic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VocabularyDTO)) {
            return false;
        }

        VocabularyDTO vocabularyDTO = (VocabularyDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, vocabularyDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VocabularyDTO{" +
            "id=" + getId() +
            ", word='" + getWord() + "'" +
            ", mean='" + getMean() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", vocabularyTopic=" + getVocabularyTopic() +
            "}";
    }
}
