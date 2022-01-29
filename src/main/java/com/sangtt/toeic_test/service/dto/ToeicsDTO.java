package com.sangtt.toeic_test.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sangtt.toeic_test.domain.Toeics} entity.
 */
public class ToeicsDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String nameToeic;

    @NotNull
    private Integer number;

    private String description;

    @NotNull
    private Long view;

    @NotNull
    private Long test;

    private String linkDetail;

    @NotNull
    private LocalDate createdAt;

    @NotNull
    private LocalDate updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameToeic() {
        return nameToeic;
    }

    public void setNameToeic(String nameToeic) {
        this.nameToeic = nameToeic;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getLinkDetail() {
        return linkDetail;
    }

    public void setLinkDetail(String linkDetail) {
        this.linkDetail = linkDetail;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ToeicsDTO)) {
            return false;
        }

        ToeicsDTO toeicsDTO = (ToeicsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, toeicsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ToeicsDTO{" +
            "id=" + getId() +
            ", nameToeic='" + getNameToeic() + "'" +
            ", number=" + getNumber() +
            ", description='" + getDescription() + "'" +
            ", view=" + getView() +
            ", test=" + getTest() +
            ", linkDetail='" + getLinkDetail() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
