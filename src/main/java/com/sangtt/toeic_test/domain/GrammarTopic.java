package com.sangtt.toeic_test.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A GrammarTopic.
 */
@Entity
@Table(name = "grammar_topic")
public class GrammarTopic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name_topic", length = 100, nullable = false)
    private String nameTopic;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "view", nullable = false)
    private Long view;

    @NotNull
    @Column(name = "test", nullable = false)
    private Long test;

    @Column(name = "level")
    private Long level;

    @Column(name = "file_practice")
    private String filePractice;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "grammarTopic")
    @JsonIgnoreProperties(value = { "detailsGrammarUsers", "grammarTopic" }, allowSetters = true)
    private Set<GrammarUser> grammarUsers = new HashSet<>();

    @OneToMany(mappedBy = "grammarTopic")
    @JsonIgnoreProperties(value = { "grammarAnswer", "grammarTopic" }, allowSetters = true)
    private Set<GrammarQuestion> grammarQuestions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public GrammarTopic id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameTopic() {
        return this.nameTopic;
    }

    public GrammarTopic nameTopic(String nameTopic) {
        this.setNameTopic(nameTopic);
        return this;
    }

    public void setNameTopic(String nameTopic) {
        this.nameTopic = nameTopic;
    }

    public String getDescription() {
        return this.description;
    }

    public GrammarTopic description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getView() {
        return this.view;
    }

    public GrammarTopic view(Long view) {
        this.setView(view);
        return this;
    }

    public void setView(Long view) {
        this.view = view;
    }

    public Long getTest() {
        return this.test;
    }

    public GrammarTopic test(Long test) {
        this.setTest(test);
        return this;
    }

    public void setTest(Long test) {
        this.test = test;
    }

    public Long getLevel() {
        return this.level;
    }

    public GrammarTopic level(Long level) {
        this.setLevel(level);
        return this;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getFilePractice() {
        return this.filePractice;
    }

    public GrammarTopic filePractice(String filePractice) {
        this.setFilePractice(filePractice);
        return this;
    }

    public void setFilePractice(String filePractice) {
        this.filePractice = filePractice;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public GrammarTopic createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public GrammarTopic updatedAt(LocalDate updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<GrammarUser> getGrammarUsers() {
        return this.grammarUsers;
    }

    public void setGrammarUsers(Set<GrammarUser> grammarUsers) {
        if (this.grammarUsers != null) {
            this.grammarUsers.forEach(i -> i.setGrammarTopic(null));
        }
        if (grammarUsers != null) {
            grammarUsers.forEach(i -> i.setGrammarTopic(this));
        }
        this.grammarUsers = grammarUsers;
    }

    public GrammarTopic grammarUsers(Set<GrammarUser> grammarUsers) {
        this.setGrammarUsers(grammarUsers);
        return this;
    }

    public GrammarTopic addGrammarUser(GrammarUser grammarUser) {
        this.grammarUsers.add(grammarUser);
        grammarUser.setGrammarTopic(this);
        return this;
    }

    public GrammarTopic removeGrammarUser(GrammarUser grammarUser) {
        this.grammarUsers.remove(grammarUser);
        grammarUser.setGrammarTopic(null);
        return this;
    }

    public Set<GrammarQuestion> getGrammarQuestions() {
        return this.grammarQuestions;
    }

    public void setGrammarQuestions(Set<GrammarQuestion> grammarQuestions) {
        if (this.grammarQuestions != null) {
            this.grammarQuestions.forEach(i -> i.setGrammarTopic(null));
        }
        if (grammarQuestions != null) {
            grammarQuestions.forEach(i -> i.setGrammarTopic(this));
        }
        this.grammarQuestions = grammarQuestions;
    }

    public GrammarTopic grammarQuestions(Set<GrammarQuestion> grammarQuestions) {
        this.setGrammarQuestions(grammarQuestions);
        return this;
    }

    public GrammarTopic addGrammarQuestion(GrammarQuestion grammarQuestion) {
        this.grammarQuestions.add(grammarQuestion);
        grammarQuestion.setGrammarTopic(this);
        return this;
    }

    public GrammarTopic removeGrammarQuestion(GrammarQuestion grammarQuestion) {
        this.grammarQuestions.remove(grammarQuestion);
        grammarQuestion.setGrammarTopic(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GrammarTopic)) {
            return false;
        }
        return id != null && id.equals(((GrammarTopic) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GrammarTopic{" +
            "id=" + getId() +
            ", nameTopic='" + getNameTopic() + "'" +
            ", description='" + getDescription() + "'" +
            ", view=" + getView() +
            ", test=" + getTest() +
            ", level=" + getLevel() +
            ", filePractice='" + getFilePractice() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
