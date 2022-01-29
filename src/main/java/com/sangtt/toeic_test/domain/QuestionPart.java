package com.sangtt.toeic_test.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A QuestionPart.
 */
@Entity
@Table(name = "question_part")
public class QuestionPart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "question", nullable = false)
    private String question;

    @NotNull
    @Column(name = "correct", nullable = false)
    private String correct;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    @OneToOne
    @JoinColumn(unique = true)
    private AnswerQuestion answerQuestion;

    @ManyToOne
    @JsonIgnoreProperties(value = { "toeics" }, allowSetters = true)
    private Parts parts;

    @ManyToOne
    private ExtendQuestion extendQuestion;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public QuestionPart id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return this.question;
    }

    public QuestionPart question(String question) {
        this.setQuestion(question);
        return this;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect() {
        return this.correct;
    }

    public QuestionPart correct(String correct) {
        this.setCorrect(correct);
        return this;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public QuestionPart createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public QuestionPart updatedAt(LocalDate updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public AnswerQuestion getAnswerQuestion() {
        return this.answerQuestion;
    }

    public void setAnswerQuestion(AnswerQuestion answerQuestion) {
        this.answerQuestion = answerQuestion;
    }

    public QuestionPart answerQuestion(AnswerQuestion answerQuestion) {
        this.setAnswerQuestion(answerQuestion);
        return this;
    }

    public Parts getParts() {
        return this.parts;
    }

    public void setParts(Parts parts) {
        this.parts = parts;
    }

    public QuestionPart parts(Parts parts) {
        this.setParts(parts);
        return this;
    }

    public ExtendQuestion getExtendQuestion() {
        return this.extendQuestion;
    }

    public void setExtendQuestion(ExtendQuestion extendQuestion) {
        this.extendQuestion = extendQuestion;
    }

    public QuestionPart extendQuestion(ExtendQuestion extendQuestion) {
        this.setExtendQuestion(extendQuestion);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionPart)) {
            return false;
        }
        return id != null && id.equals(((QuestionPart) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionPart{" +
            "id=" + getId() +
            ", question='" + getQuestion() + "'" +
            ", correct='" + getCorrect() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
