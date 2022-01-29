package com.sangtt.toeic_test.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sangtt.toeic_test.domain.GrammarTopic} entity.
 */
public class GrammarTopicDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String nameTopic;

    private String description;

    @NotNull
    private Long view;

    @NotNull
    private Long test;

    private Long level;

    private String filePractice;

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

    public String getNameTopic() {
        return nameTopic;
    }

    public void setNameTopic(String nameTopic) {
        this.nameTopic = nameTopic;
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

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getFilePractice() {
        return filePractice;
    }

    public void setFilePractice(String filePractice) {
        this.filePractice = filePractice;
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
        if (!(o instanceof GrammarTopicDTO)) {
            return false;
        }

        GrammarTopicDTO grammarTopicDTO = (GrammarTopicDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, grammarTopicDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GrammarTopicDTO{" +
            "id=" + getId() +
            ", nameTopic='" + getNameTopic() + "'" +
            ", description='" + getDescription() + "'" +
            ", view=" + getView() +
            ", test=" + getTest() +
            ", level=" + getLevel() +
            ", filePractice='" + getFilePractice() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
