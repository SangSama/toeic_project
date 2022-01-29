package com.sangtt.toeic_test.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A ToeicUser.
 */
@Entity
@Table(name = "toeic_user")
public class ToeicUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "score")
    private Long score;

    @NotNull
    @Column(name = "reading", nullable = false)
    private String reading;

    @NotNull
    @Column(name = "listening", nullable = false)
    private String listening;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "toeicUser")
    @JsonIgnoreProperties(value = { "toeicUser" }, allowSetters = true)
    private Set<DetailsToeicUser> detailsToeicUsers = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "toeicUsers", "parts" }, allowSetters = true)
    private Toeics toeics;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ToeicUser id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public ToeicUser userId(Long userId) {
        this.setUserId(userId);
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getScore() {
        return this.score;
    }

    public ToeicUser score(Long score) {
        this.setScore(score);
        return this;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getReading() {
        return this.reading;
    }

    public ToeicUser reading(String reading) {
        this.setReading(reading);
        return this;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    public String getListening() {
        return this.listening;
    }

    public ToeicUser listening(String listening) {
        this.setListening(listening);
        return this;
    }

    public void setListening(String listening) {
        this.listening = listening;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public ToeicUser createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public ToeicUser updatedAt(LocalDate updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<DetailsToeicUser> getDetailsToeicUsers() {
        return this.detailsToeicUsers;
    }

    public void setDetailsToeicUsers(Set<DetailsToeicUser> detailsToeicUsers) {
        if (this.detailsToeicUsers != null) {
            this.detailsToeicUsers.forEach(i -> i.setToeicUser(null));
        }
        if (detailsToeicUsers != null) {
            detailsToeicUsers.forEach(i -> i.setToeicUser(this));
        }
        this.detailsToeicUsers = detailsToeicUsers;
    }

    public ToeicUser detailsToeicUsers(Set<DetailsToeicUser> detailsToeicUsers) {
        this.setDetailsToeicUsers(detailsToeicUsers);
        return this;
    }

    public ToeicUser addDetailsToeicUser(DetailsToeicUser detailsToeicUser) {
        this.detailsToeicUsers.add(detailsToeicUser);
        detailsToeicUser.setToeicUser(this);
        return this;
    }

    public ToeicUser removeDetailsToeicUser(DetailsToeicUser detailsToeicUser) {
        this.detailsToeicUsers.remove(detailsToeicUser);
        detailsToeicUser.setToeicUser(null);
        return this;
    }

    public Toeics getToeics() {
        return this.toeics;
    }

    public void setToeics(Toeics toeics) {
        this.toeics = toeics;
    }

    public ToeicUser toeics(Toeics toeics) {
        this.setToeics(toeics);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ToeicUser)) {
            return false;
        }
        return id != null && id.equals(((ToeicUser) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ToeicUser{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", score=" + getScore() +
            ", reading='" + getReading() + "'" +
            ", listening='" + getListening() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
