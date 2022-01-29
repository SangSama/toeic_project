package com.sangtt.toeic_test.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sangtt.toeic_test.domain.GrammarQuestion} entity.
 */
public class GrammarQuestionDTO implements Serializable {

    private Long id;

    @NotNull
    private String question;

    @NotNull
    private String correct;

    @NotNull
    private LocalDate createdAt;

    @NotNull
    private LocalDate updatedAt;

    private GrammarAnswerDTO grammarAnswer;

    private GrammarTopicDTO grammarTopic;

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

    public GrammarAnswerDTO getGrammarAnswer() {
        return grammarAnswer;
    }

    public void setGrammarAnswer(GrammarAnswerDTO grammarAnswer) {
        this.grammarAnswer = grammarAnswer;
    }

    public GrammarTopicDTO getGrammarTopic() {
        return grammarTopic;
    }

    public void setGrammarTopic(GrammarTopicDTO grammarTopic) {
        this.grammarTopic = grammarTopic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GrammarQuestionDTO)) {
            return false;
        }

        GrammarQuestionDTO grammarQuestionDTO = (GrammarQuestionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, grammarQuestionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GrammarQuestionDTO{" +
            "id=" + getId() +
            ", question='" + getQuestion() + "'" +
            ", correct='" + getCorrect() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", grammarAnswer=" + getGrammarAnswer() +
            ", grammarTopic=" + getGrammarTopic() +
            "}";
    }
}
