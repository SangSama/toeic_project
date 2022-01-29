package com.sangtt.toeic_test.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sangtt.toeic_test.domain.Parts} entity.
 */
public class PartsDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer part;

    @NotNull
    private String description;

    @NotNull
    private String example;

    @NotNull
    private String fileLC;

    @NotNull
    private Long view;

    @NotNull
    private Long test;

    @NotNull
    private LocalDate createdAt;

    @NotNull
    private LocalDate updatedAt;

    private ToeicsDTO toeics;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPart() {
        return part;
    }

    public void setPart(Integer part) {
        this.part = part;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getFileLC() {
        return fileLC;
    }

    public void setFileLC(String fileLC) {
        this.fileLC = fileLC;
    }

    public Long getView() {
        return view;
    }

    public void setView(Long view) {
        this.view = view;
    }

    public Long getTest() {
        return test;
    }

    public void setTest(Long test) {
        this.test = test;
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

    public ToeicsDTO getToeics() {
        return toeics;
    }

    public void setToeics(ToeicsDTO toeics) {
        this.toeics = toeics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PartsDTO)) {
            return false;
        }

        PartsDTO partsDTO = (PartsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, partsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PartsDTO{" +
            "id=" + getId() +
            ", part=" + getPart() +
            ", description='" + getDescription() + "'" +
            ", example='" + getExample() + "'" +
            ", fileLC='" + getFileLC() + "'" +
            ", view=" + getView() +
            ", test=" + getTest() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", toeics=" + getToeics() +
            "}";
    }
}
