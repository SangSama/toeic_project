package com.sangtt.toeic_test.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A GrammarUser.
 */
@Entity
@Table(name = "grammar_user")
public class GrammarUser implements Serializable {

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
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "grammarUser")
    @JsonIgnoreProperties(value = { "grammarUser" }, allowSetters = true)
    private Set<DetailsGrammarUser> detailsGrammarUsers = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "grammarUsers", "grammarQuestions" }, allowSetters = true)
    private GrammarTopic grammarTopic;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public GrammarUser id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public GrammarUser userId(Long userId) {
        this.setUserId(userId);
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getScore() {
        return this.score;
    }

    public GrammarUser score(Long score) {
        this.setScore(score);
        return this;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public GrammarUser createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public GrammarUser updatedAt(LocalDate updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<DetailsGrammarUser> getDetailsGrammarUsers() {
        return this.detailsGrammarUsers;
    }

    public void setDetailsGrammarUsers(Set<DetailsGrammarUser> detailsGrammarUsers) {
        if (this.detailsGrammarUsers != null) {
            this.detailsGrammarUsers.forEach(i -> i.setGrammarUser(null));
        }
        if (detailsGrammarUsers != null) {
            detailsGrammarUsers.forEach(i -> i.setGrammarUser(this));
        }
        this.detailsGrammarUsers = detailsGrammarUsers;
    }

    public GrammarUser detailsGrammarUsers(Set<DetailsGrammarUser> detailsGrammarUsers) {
        this.setDetailsGrammarUsers(detailsGrammarUsers);
        return this;
    }

    public GrammarUser addDetailsGrammarUser(DetailsGrammarUser detailsGrammarUser) {
        this.detailsGrammarUsers.add(detailsGrammarUser);
        detailsGrammarUser.setGrammarUser(this);
        return this;
    }

    public GrammarUser removeDetailsGrammarUser(DetailsGrammarUser detailsGrammarUser) {
        this.detailsGrammarUsers.remove(detailsGrammarUser);
        detailsGrammarUser.setGrammarUser(null);
        return this;
    }

    public GrammarTopic getGrammarTopic() {
        return this.grammarTopic;
    }

    public void setGrammarTopic(GrammarTopic grammarTopic) {
        this.grammarTopic = grammarTopic;
    }

    public GrammarUser grammarTopic(GrammarTopic grammarTopic) {
        this.setGrammarTopic(grammarTopic);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GrammarUser)) {
            return false;
        }
        return id != null && id.equals(((GrammarUser) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GrammarUser{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", score=" + getScore() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
