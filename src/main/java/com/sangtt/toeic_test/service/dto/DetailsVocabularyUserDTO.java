package com.sangtt.toeic_test.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sangtt.toeic_test.domain.DetailsVocabularyUser} entity.
 */
public class DetailsVocabularyUserDTO implements Serializable {

    private Long id;

    @NotNull
    private Long vocabularyId;

    @NotNull
    private Long status;

    @NotNull
    private LocalDate createdAt;

    @NotNull
    private LocalDate updatedAt;

    private VocabularyUserDTO vocabularyUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVocabularyId() {
        return vocabularyId;
    }

    public void setVocabularyId(Long vocabularyId) {
        this.vocabularyId = vocabularyId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
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

    public VocabularyUserDTO getVocabularyUser() {
        return vocabularyUser;
    }

    public void setVocabularyUser(VocabularyUserDTO vocabularyUser) {
        this.vocabularyUser = vocabularyUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DetailsVocabularyUserDTO)) {
            return false;
        }

        DetailsVocabularyUserDTO detailsVocabularyUserDTO = (DetailsVocabularyUserDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, detailsVocabularyUserDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DetailsVocabularyUserDTO{" +
            "id=" + getId() +
            ", vocabularyId=" + getVocabularyId() +
            ", status=" + getStatus() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", vocabularyUser=" + getVocabularyUser() +
            "}";
    }
}
