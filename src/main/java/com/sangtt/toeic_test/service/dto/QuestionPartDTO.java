package com.sangtt.toeic_test.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sangtt.toeic_test.domain.QuestionPart} entity.
 */
public class QuestionPartDTO implements Serializable {

    private Long id;

    @NotNull
    private String question;

    @NotNull
    private String correct;

    @NotNull
    private LocalDate createdAt;

    @NotNull
    private LocalDate updatedAt;

    private AnswerQuestionDTO answerQuestion;

    private PartsDTO parts;

    private ExtendQuestionDTO extendQuestion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
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

    public AnswerQuestionDTO getAnswerQuestion() {
        return answerQuestion;
    }

    public void setAnswerQuestion(AnswerQuestionDTO answerQuestion) {
        this.answerQuestion = answerQuestion;
    }

    public PartsDTO getParts() {
        return parts;
    }

    public void setParts(PartsDTO parts) {
        this.parts = parts;
    }

    public ExtendQuestionDTO getExtendQuestion() {
        return extendQuestion;
    }

    public void setExtendQuestion(ExtendQuestionDTO extendQuestion) {
        this.extendQuestion = extendQuestion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionPartDTO)) {
            return false;
        }

        QuestionPartDTO questionPartDTO = (QuestionPartDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, questionPartDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionPartDTO{" +
            "id=" + getId() +
            ", question='" + getQuestion() + "'" +
            ", correct='" + getCorrect() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", answerQuestion=" + getAnswerQuestion() +
            ", parts=" + getParts() +
            ", extendQuestion=" + getExtendQuestion() +
            "}";
    }
}
