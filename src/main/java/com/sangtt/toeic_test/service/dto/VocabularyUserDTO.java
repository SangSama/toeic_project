package com.sangtt.toeic_test.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sangtt.toeic_test.domain.VocabularyUser} entity.
 */
public class VocabularyUserDTO implements Serializable {

    private Long id;

    @NotNull
    private Long userId;

    private Long score;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
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
        if (!(o instanceof VocabularyUserDTO)) {
            return false;
        }

        VocabularyUserDTO vocabularyUserDTO = (VocabularyUserDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, vocabularyUserDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VocabularyUserDTO{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", score=" + getScore() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", vocabularyTopic=" + getVocabularyTopic() +
            "}";
    }
}
