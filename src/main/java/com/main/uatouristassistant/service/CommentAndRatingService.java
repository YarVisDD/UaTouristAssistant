package com.main.uatouristassistant.service;

import com.main.uatouristassistant.entity.CommentAndRating;
import com.main.uatouristassistant.repository.CommentAndRatingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Service
public class CommentAndRatingService {
    @Autowired
    private CommentAndRatingRepository commentAndRatingRepository;

    public void saveCommentAndRate(Long userId, String comment, String rate) {
        CommentAndRating commentAndRating = new CommentAndRating();
        commentAndRating.setUserId(userId);
        commentAndRating.setComment(comment);
        commentAndRating.setRate(rate);
        commentAndRatingRepository.save(commentAndRating);
        log.info("INFO!!! Comment and rate has been added. Comment and rate {}", commentAndRating);
    }

    public void getCommentAndRate(Long userId) {
        commentAndRatingRepository.findCommentAndRateByUserId(userId);
    }

    public List<CommentAndRating> getAllCommentsAndRate() {
        return commentAndRatingRepository.findAll();
    }

    public String delCommentAndRate(Long commendId) {
        try {
            CommentAndRating commentAndRating = commentAndRatingRepository.findCommentAndRatingByCommentId(commendId);
            commentAndRatingRepository.delete(commentAndRating);

            log.info("INFO!!! Comment and rate has been deleted for commentId{}", commendId);

            return "The comment " + commendId + " has been deleted";
        } catch (Exception ex) {
            log.error("ERROR!!! Tried to delete comment and rate which does not exist: {}", commendId);
            return "The comment " + commendId + " does not exist!";
        }
    }

    public String updCommentAndRatee(Long userId, String comment, String rate) {
        try {
            CommentAndRating commentAndRating = commentAndRatingRepository.findCommentAndRateByUserId(userId);
            commentAndRating.setComment(comment);
            commentAndRating.setRate(rate);
            commentAndRatingRepository.save(commentAndRating);
            log.info("INFO!!! Comment and rate has been updated for userId{}", userId);
            return "Comment and rate updated";
        } catch (NullPointerException ex) {
            log.error("ERROR!!! Update comment adn rate for incorrect User Id {}", userId);
            return "The user with id " + userId + " does not exist!";
        }
    }
}