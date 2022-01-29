package com.sangtt.toeic_test.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Parts.
 */
@Entity
@Table(name = "parts")
public class Parts implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "part", nullable = false)
    private Integer part;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "example", nullable = false)
    private String example;

    @NotNull
    @Column(name = "file_lc", nullable = false)
    private String fileLC;

    @NotNull
    @Column(name = "view", nullable = false)
    private Long view;

    @NotNull
    @Column(name = "test", nullable = false)
    private Long test;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    @ManyToOne
    @JsonIgnoreProperties(value = { "toeicUsers", "parts" }, allowSetters = true)
    private Toeics toeics;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Parts id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPart() {
        return this.part;
    }

    public Parts part(Integer part) {
        this.setPart(part);
        return this;
    }

    public void setPart(Integer part) {
        this.part = part;
    }

    public String getDescription() {
        return this.description;
    }

    public Parts description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExample() {
        return this.example;
    }

    public Parts example(String example) {
        this.setExample(example);
        return this;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getFileLC() {
        return this.fileLC;
    }

    public Parts fileLC(String fileLC) {
        this.setFileLC(fileLC);
        return this;
    }

    public void setFileLC(String fileLC) {
        this.fileLC = fileLC;
    }

    public Long getView() {
        return this.view;
    }

    public Parts view(Long view) {
        this.setView(view);
        return this;
    }

    public void setView(Long view) {
        this.view = view;
    }

    public Long getTest() {
        return this.test;
    }

    public Parts test(Long test) {
        this.setTest(test);
        return this;
    }

    public void setTest(Long test) {
        this.test = test;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public Parts createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public Parts updatedAt(LocalDate updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Toeics getToeics() {
        return this.toeics;
    }

    public void setToeics(Toeics toeics) {
        this.toeics = toeics;
    }

    public Parts toeics(Toeics toeics) {
        this.setToeics(toeics);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Parts)) {
            return false;
        }
        return id != null && id.equals(((Parts) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Parts{" +
            "id=" + getId() +
            ", part=" + getPart() +
            ", description='" + getDescription() + "'" +
            ", example='" + getExample() + "'" +
            ", fileLC='" + getFileLC() + "'" +
            ", view=" + getView() +
            ", test=" + getTest() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
