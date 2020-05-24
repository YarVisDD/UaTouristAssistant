package com.main.uatouristassistant.repository;

import com.main.uatouristassistant.entity.CommentAndRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentAndRatingRepository extends JpaRepository<CommentAndRating,Long> {
    CommentAndRating findCommentAndRateByUserLogin(String userLogin);
    CommentAndRating findCommentAndRatingByCommentId(Long commentId);
    List<CommentAndRating> findByPlaceIdPlace(Long id);
    boolean existsByCommentId(Long commentId);
    boolean existsByUserLogin(String userLogin);
}
