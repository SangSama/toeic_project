package com.sangtt.toeic_test.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sangtt.toeic_test.domain.ToeicUser} entity.
 */
public class ToeicUserDTO implements Serializable {

    private Long id;

    @NotNull
    private Long userId;

    private Long score;

    @NotNull
    private String reading;

    @NotNull
    private String listening;

    @NotNull
    private LocalDate createdAt;

    @NotNull
    private LocalDate updatedAt;

    private ToeicsDTO toeics;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    public String getListening() {
        return listening;
    }

    public void setListening(String listening) {
        this.listening = listening;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ToeicsDTO getToeics() {
        return toeics;
    }

    public void setToeics(ToeicsDTO toeics) {
        this.toeics = toeics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ToeicUserDTO)) {
            return false;
        }

        ToeicUserDTO toeicUserDTO = (ToeicUserDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, toeicUserDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ToeicUserDTO{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", score=" + getScore() +
            ", reading='" + getReading() + "'" +
            ", listening='" + getListening() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", toeics=" + getToeics() +
            "}";
    }
}
