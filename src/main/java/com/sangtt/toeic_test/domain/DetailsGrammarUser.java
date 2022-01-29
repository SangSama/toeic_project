package com.sangtt.toeic_test.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A DetailsGrammarUser.
 */
@Entity
@Table(name = "details_grammar_user")
public class DetailsGrammarUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "grammar_question_id", nullable = false)
    private Long grammarQuestionId;

    @NotNull
    @Column(name = "status", nullable = false)
    private Long status;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    @ManyToOne
    @JsonIgnoreProperties(value = { "detailsGrammarUsers", "grammarTopic" }, allowSetters = true)
    private GrammarUser grammarUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DetailsGrammarUser id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGrammarQuestionId() {
        return this.grammarQuestionId;
    }

    public DetailsGrammarUser grammarQuestionId(Long grammarQuestionId) {
        this.setGrammarQuestionId(grammarQuestionId);
        return this;
    }

    public void setGrammarQuestionId(Long grammarQuestionId) {
        this.grammarQuestionId = grammarQuestionId;
    }

    public Long getStatus() {
        return this.status;
    }

    public DetailsGrammarUser status(Long status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public DetailsGrammarUser createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public DetailsGrammarUser updatedAt(LocalDate updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public GrammarUser getGrammarUser() {
        return this.grammarUser;
    }

    public void setGrammarUser(GrammarUser grammarUser) {
        this.grammarUser = grammarUser;
    }

    public DetailsGrammarUser grammarUser(GrammarUser grammarUser) {
        this.setGrammarUser(grammarUser);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DetailsGrammarUser)) {
            return false;
        }
        return id != null && id.equals(((DetailsGrammarUser) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DetailsGrammarUser{" +
            "id=" + getId() +
            ", grammarQuestionId=" + getGrammarQuestionId() +
            ", status=" + getStatus() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
