package com.sneha.jobtracker.repository;

import com.sneha.jobtracker.entity.Question;
import com.sneha.jobtracker.enums.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT q FROM Question q WHERE q.user.id = :userId OR q.user IS NULL")
    List<Question> findAllForUser(@Param("userId") Long userId);

    @Query("SELECT q FROM Question q WHERE (q.user.id = :userId OR q.user IS NULL) AND q.difficulty = :difficulty")
    List<Question> findByDifficulty(@Param("userId") Long userId, @Param("difficulty") Difficulty difficulty);

    @Query("SELECT q FROM Question q WHERE (q.user.id = :userId OR q.user IS NULL) AND LOWER(q.company) LIKE LOWER(CONCAT('%', :company, '%'))")
    List<Question> findByCompany(@Param("userId") Long userId, @Param("company") String company);
}