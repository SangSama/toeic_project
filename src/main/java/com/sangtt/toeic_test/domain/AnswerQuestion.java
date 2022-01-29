package com.sangtt.toeic_test.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A AnswerQuestion.
 */
@Entity
@Table(name = "answer_question")
public class AnswerQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "answer_a", nullable = false)
    private String answerA;

    @NotNull
    @Column(name = "answer_b", nullable = false)
    private String answerB;

    @NotNull
    @Column(name = "answer_c", nullable = false)
    private String answerC;

    @NotNull
    @Column(name = "answer_d", nullable = false)
    private String answerD;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AnswerQuestion id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswerA() {
        return this.answerA;
    }

    public AnswerQuestion answerA(String answerA) {
        this.setAnswerA(answerA);
        return this;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return this.answerB;
    }

    public AnswerQuestion answerB(String answerB) {
        this.setAnswerB(answerB);
        return this;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return this.answerC;
    }

    public AnswerQuestion answerC(String answerC) {
        this.setAnswerC(answerC);
        return this;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return this.answerD;
    }

    public AnswerQuestion answerD(String answerD) {
        this.setAnswerD(answerD);
        return this;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public AnswerQuestion createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public AnswerQuestion updatedAt(LocalDate updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnswerQuestion)) {
            return false;
        }
        return id != null && id.equals(((AnswerQuestion) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnswerQuestion{" +
            "id=" + getId() +
            ", answerA='" + getAnswerA() + "'" +
            ", answerB='" + getAnswerB() + "'" +
            ", answerC='" + getAnswerC() + "'" +
            ", answerD='" + getAnswerD() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
