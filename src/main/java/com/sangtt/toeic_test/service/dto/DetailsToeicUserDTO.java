package com.sangtt.toeic_test.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sangtt.toeic_test.domain.DetailsToeicUser} entity.
 */
public class DetailsToeicUserDTO implements Serializable {

    private Long id;

    @NotNull
    private Long toeicId;

    @NotNull
    private Long partId;

    @NotNull
    private Long questionId;

    @NotNull
    private Long status;

    @NotNull
    private LocalDate createdAt;

    @NotNull
    private LocalDate updatedAt;

    private ToeicUserDTO toeicUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getToeicId() {
        return toeicId;
    }

    public void setToeicId(Long toeicId) {
        this.toeicId = toeicId;
    }

    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
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

    public ToeicUserDTO getToeicUser() {
        return toeicUser;
    }

    public void setToeicUser(ToeicUserDTO toeicUser) {
        this.toeicUser = toeicUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DetailsToeicUserDTO)) {
            return false;
        }

        DetailsToeicUserDTO detailsToeicUserDTO = (DetailsToeicUserDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, detailsToeicUserDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DetailsToeicUserDTO{" +
            "id=" + getId() +
            ", toeicId=" + getToeicId() +
            ", partId=" + getPartId() +
            ", questionId=" + getQuestionId() +
            ", status=" + getStatus() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", toeicUser=" + getToeicUser() +
            "}";
    }
}
