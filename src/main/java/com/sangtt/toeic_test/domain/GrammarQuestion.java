package com.sangtt.toeic_test.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A GrammarQuestion.
 */
@Entity
@Table(name = "grammar_question")
public class GrammarQuestion implements Serializable {

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
    private GrammarAnswer grammarAnswer;

    @ManyToOne
    @JsonIgnoreProperties(value = { "grammarUsers", "grammarQuestions" }, allowSetters = true)
    private GrammarTopic grammarTopic;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public GrammarQuestion id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return this.question;
    }

    public GrammarQuestion question(String question) {
        this.setQuestion(question);
        return this;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect() {
        return this.correct;
    }

    public GrammarQuestion correct(String correct) {
        this.setCorrect(correct);
        return this;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public GrammarQuestion createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public GrammarQuestion updatedAt(LocalDate updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public GrammarAnswer getGrammarAnswer() {
        return this.grammarAnswer;
    }

    public void setGrammarAnswer(GrammarAnswer grammarAnswer) {
        this.grammarAnswer = grammarAnswer;
    }

    public GrammarQuestion grammarAnswer(GrammarAnswer grammarAnswer) {
        this.setGrammarAnswer(grammarAnswer);
        return this;
    }

    public GrammarTopic getGrammarTopic() {
        return this.grammarTopic;
    }

    public void setGrammarTopic(GrammarTopic grammarTopic) {
        this.grammarTopic = grammarTopic;
    }

    public GrammarQuestion grammarTopic(GrammarTopic grammarTopic) {
        this.setGrammarTopic(grammarTopic);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GrammarQuestion)) {
            return false;
        }
        return id != null && id.equals(((GrammarQuestion) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GrammarQuestion{" +
            "id=" + getId() +
            ", question='" + getQuestion() + "'" +
            ", correct='" + getCorrect() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
