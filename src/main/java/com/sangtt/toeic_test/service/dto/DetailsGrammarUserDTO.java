package com.sangtt.toeic_test.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sangtt.toeic_test.domain.DetailsGrammarUser} entity.
 */
public class DetailsGrammarUserDTO implements Serializable {

    private Long id;

    @NotNull
    private Long grammarQuestionId;

    @NotNull
    private Long status;

    @NotNull
    private LocalDate createdAt;

    @NotNull
    private LocalDate updatedAt;

    private GrammarUserDTO grammarUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGrammarQuestionId() {
        return grammarQuestionId;
    }

    public void setGrammarQuestionId(Long grammarQuestionId) {
        this.grammarQuestionId = grammarQuestionId;
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

    public GrammarUserDTO getGrammarUser() {
        return grammarUser;
    }

    public void setGrammarUser(GrammarUserDTO grammarUser) {
        this.grammarUser = grammarUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DetailsGrammarUserDTO)) {
            return false;
        }

        DetailsGrammarUserDTO detailsGrammarUserDTO = (DetailsGrammarUserDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, detailsGrammarUserDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DetailsGrammarUserDTO{" +
            "id=" + getId() +
            ", grammarQuestionId=" + getGrammarQuestionId() +
            ", status=" + getStatus() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", grammarUser=" + getGrammarUser() +
            "}";
    }
}
