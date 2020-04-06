package com.main.uatouristassistant.service;

import com.main.uatouristassistant.entity.CommentAndRating;
import com.main.uatouristassistant.entity.RateType;
import com.main.uatouristassistant.repository.CommentAndRatingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CommentAndRatingService {
    @Autowired
    private CommentAndRatingRepository commentAndRatingRepository;
    @Autowired
    private PlaceService placeService;

    public void saveCommentAndRate(String userLogin, String comment, RateType rateType, Long placeId) {
        CommentAndRating commentAndRating = new CommentAndRating();
        commentAndRating.setUserLogin(userLogin);
        commentAndRating.setComment(comment);
        commentAndRating.setRateType(rateType);
        commentAndRating.setPlace(placeService.getPlaceById(placeId));
        commentAndRatingRepository.save(commentAndRating);
        log.info("INFO!!! Comment and rate has been added. Comment and rate {}", commentAndRating);
    }

    public List<CommentAndRating> getCommentByPlace(Long id) {
        return commentAndRatingRepository.findByPlaceIdPlace(id);
    }

    public void getCommentAndRate(String userLogin) {
        commentAndRatingRepository.findCommentAndRateByUserLogin(userLogin);
    }

    public List<CommentAndRating> getAllCommentsAndRate() {
        return commentAndRatingRepository.findAll();
    }

    public String delCommentAndRate(Long commentId) {
        try {
            CommentAndRating commentAndRating = commentAndRatingRepository.findCommentAndRatingByCommentId(commentId);
            commentAndRatingRepository.delete(commentAndRating);

            log.info("INFO!!! Comment and rate has been deleted for commentId{}", commentId);

            return "The comment " + commentId + " has been deleted";
        } catch (Exception ex) {
            log.error("ERROR!!! Tried to delete comment and rate which does not exist: {}", commentId);
            return "The comment " + commentId + " does not exist!";
        }
    }

    public String updCommentAndRatee(String userLogin, String comment, RateType rateType, Long placeId) {
        try {
            CommentAndRating commentAndRating = commentAndRatingRepository.findCommentAndRateByUserLogin(userLogin);
            commentAndRating.setComment(comment);
            commentAndRating.setRateType(rateType);
            commentAndRating.setPlace(placeService.getPlaceById(placeId));
            commentAndRatingRepository.save(commentAndRating);
            log.info("INFO!!! Comment and rate has been updated for userLogin {}", userLogin);
            return "Comment and rate updated";
        } catch (NullPointerException ex) {
            log.error("ERROR!!! Update comment adn rate for incorrect User Login {}", userLogin);
            return "The user with login " + userLogin + " does not exist!";
        }
    }
}