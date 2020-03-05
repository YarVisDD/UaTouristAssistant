package com.main.uatouristassistant.controller;

import com.main.uatouristassistant.entity.CommentAndRating;
import com.main.uatouristassistant.entity.User;
import com.main.uatouristassistant.repository.CommentAndRatingRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping(path = "/comment")

public class CommentAndRatingController {
    @Autowired
    private CommentAndRatingRepository commentAndRatingRepository;


    @PostMapping(path = "/addCommentAndRate")
    public @ResponseBody
    boolean addCommentAndRate(Long userId, String comment, String rate) {
        CommentAndRating commentAndRating = new CommentAndRating();

        commentAndRating.setUserId(userId);
        commentAndRating.setComment(comment);
        commentAndRating.setRate(rate);

        commentAndRatingRepository.save(commentAndRating);
        log.info("INFO!!! Comment and rate has been added. Comment and rate {}", commentAndRating);
        return true;
    }

    @GetMapping(path = "/list")
    public @ResponseBody
    Iterable<CommentAndRating> getAllCommentsAndRate() {
        return commentAndRatingRepository.findAll();
    }

    @GetMapping(path = "/listCommentsAndRate")
    public @ResponseBody
    CommentAndRating getCommentAndRate(@RequestParam Long userId) {

        return commentAndRatingRepository.findCommentAndRateByUserId(userId);
    }

    @PostMapping(path = "/updateCommentAndRate")
    public @ResponseBody
    String updateCommentAndRate(@RequestParam Long userId, @RequestParam String comment, String rate) {
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

    @DeleteMapping(path = "/delCommentAndRate")
    public @ResponseBody
    String deleteCommentAndRate(@RequestParam Long commendId) {
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

}
