package com.sangtt.toeic_test.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Toeics.
 */
@Entity
@Table(name = "toeics")
public class Toeics implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "name_toeic", length = 50, nullable = false, unique = true)
    private String nameToeic;

    @NotNull
    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "view", nullable = false)
    private Long view;

    @NotNull
    @Column(name = "test", nullable = false)
    private Long test;

    @Column(name = "link_detail")
    private String linkDetail;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "toeics")
    @JsonIgnoreProperties(value = { "detailsToeicUsers", "toeics" }, allowSetters = true)
    private Set<ToeicUser> toeicUsers = new HashSet<>();

    @OneToMany(mappedBy = "toeics")
    @JsonIgnoreProperties(value = { "toeics" }, allowSetters = true)
    private Set<Parts> parts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Toeics id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameToeic() {
        return this.nameToeic;
    }

    public Toeics nameToeic(String nameToeic) {
        this.setNameToeic(nameToeic);
        return this;
    }

    public void setNameToeic(String nameToeic) {
        this.nameToeic = nameToeic;
    }

    public Integer getNumber() {
        return this.number;
    }

    public Toeics number(Integer number) {
        this.setNumber(number);
        return this;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return this.description;
    }

    public Toeics description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getView() {
        return this.view;
    }

    public Toeics view(Long view) {
        this.setView(view);
        return this;
    }

    public void setView(Long view) {
        this.view = view;
    }

    public Long getTest() {
        return this.test;
    }

    public Toeics test(Long test) {
        this.setTest(test);
        return this;
    }

    public void setTest(Long test) {
        this.test = test;
    }

    public String getLinkDetail() {
        return this.linkDetail;
    }

    public Toeics linkDetail(String linkDetail) {
        this.setLinkDetail(linkDetail);
        return this;
    }

    public void setLinkDetail(String linkDetail) {
        this.linkDetail = linkDetail;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public Toeics createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public Toeics updatedAt(LocalDate updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<ToeicUser> getToeicUsers() {
        return this.toeicUsers;
    }

    public void setToeicUsers(Set<ToeicUser> toeicUsers) {
        if (this.toeicUsers != null) {
            this.toeicUsers.forEach(i -> i.setToeics(null));
        }
        if (toeicUsers != null) {
            toeicUsers.forEach(i -> i.setToeics(this));
        }
        this.toeicUsers = toeicUsers;
    }

    public Toeics toeicUsers(Set<ToeicUser> toeicUsers) {
        this.setToeicUsers(toeicUsers);
        return this;
    }

    public Toeics addToeicUser(ToeicUser toeicUser) {
        this.toeicUsers.add(toeicUser);
        toeicUser.setToeics(this);
        return this;
    }

    public Toeics removeToeicUser(ToeicUser toeicUser) {
        this.toeicUsers.remove(toeicUser);
        toeicUser.setToeics(null);
        return this;
    }

    public Set<Parts> getParts() {
        return this.parts;
    }

    public void setParts(Set<Parts> parts) {
        if (this.parts != null) {
            this.parts.forEach(i -> i.setToeics(null));
        }
        if (parts != null) {
            parts.forEach(i -> i.setToeics(this));
        }
        this.parts = parts;
    }

    public Toeics parts(Set<Parts> parts) {
        this.setParts(parts);
        return this;
    }

    public Toeics addParts(Parts parts) {
        this.parts.add(parts);
        parts.setToeics(this);
        return this;
    }

    public Toeics removeParts(Parts parts) {
        this.parts.remove(parts);
        parts.setToeics(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Toeics)) {
            return false;
        }
        return id != null && id.equals(((Toeics) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Toeics{" +
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
