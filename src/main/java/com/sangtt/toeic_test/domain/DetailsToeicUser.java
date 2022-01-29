package com.sangtt.toeic_test.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A DetailsToeicUser.
 */
@Entity
@Table(name = "details_toeic_user")
public class DetailsToeicUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "toeic_id", nullable = false)
    private Long toeicId;

    @NotNull
    @Column(name = "part_id", nullable = false)
    private Long partId;

    @NotNull
    @Column(name = "question_id", nullable = false)
    private Long questionId;

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
    @JsonIgnoreProperties(value = { "detailsToeicUsers", "toeics" }, allowSetters = true)
    private ToeicUser toeicUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DetailsToeicUser id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getToeicId() {
        return this.toeicId;
    }

    public DetailsToeicUser toeicId(Long toeicId) {
        this.setToeicId(toeicId);
        return this;
    }

    public void setToeicId(Long toeicId) {
        this.toeicId = toeicId;
    }

    public Long getPartId() {
        return this.partId;
    }

    public DetailsToeicUser partId(Long partId) {
        this.setPartId(partId);
        return this;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public Long getQuestionId() {
        return this.questionId;
    }

    public DetailsToeicUser questionId(Long questionId) {
        this.setQuestionId(questionId);
        return this;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getStatus() {
        return this.status;
    }

    public DetailsToeicUser status(Long status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public DetailsToeicUser createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public DetailsToeicUser updatedAt(LocalDate updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ToeicUser getToeicUser() {
        return this.toeicUser;
    }

    public void setToeicUser(ToeicUser toeicUser) {
        this.toeicUser = toeicUser;
    }

    public DetailsToeicUser toeicUser(ToeicUser toeicUser) {
        this.setToeicUser(toeicUser);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DetailsToeicUser)) {
            return false;
        }
        return id != null && id.equals(((DetailsToeicUser) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DetailsToeicUser{" +
            "id=" + getId() +
            ", toeicId=" + getToeicId() +
            ", partId=" + getPartId() +
            ", questionId=" + getQuestionId() +
            ", status=" + getStatus() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
