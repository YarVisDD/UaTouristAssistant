package com.main.uatouristassistant.repository;

import com.main.uatouristassistant.entity.CommentAndRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentAndRatingRepository extends JpaRepository<CommentAndRating,Long> {
    CommentAndRating findCommentAndRateByUserLogin(String userLogin);
    CommentAndRating findCommentAndRatingByCommentId(Long commentId);
    CommentAndRating findCommentAndRateByPlaceId(Long id);
}
