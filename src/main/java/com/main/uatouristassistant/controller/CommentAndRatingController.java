package com.main.uatouristassistant.controller;

import com.main.uatouristassistant.entity.CommentAndRating;
import com.main.uatouristassistant.entity.User;
import com.main.uatouristassistant.repository.CommentAndRatingRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/comment")

public class CommentAndRatingController {
    @Autowired
    private CommentAndRatingRepository commentAndRatingRepository;


    @PostMapping(path = "/addCommentAndRate")
    public @ResponseBody
    boolean addCommentAndRate(Long userId,String comment, String rate) {
        CommentAndRating commentAndRating = new CommentAndRating();

        commentAndRating.setUserId(userId);
        commentAndRating.setComment(comment);
        commentAndRating.setRate(rate);

        commentAndRatingRepository.save(commentAndRating);
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
    String updateCommentAndRate(@RequestParam Long userId, @RequestParam String comment,String rate) {
        try {
            CommentAndRating commentAndRating=commentAndRatingRepository.findCommentAndRateByUserId(userId);
            commentAndRating.setComment(comment);
            commentAndRating.setRate(rate);
            commentAndRatingRepository.save(commentAndRating);

            return "Comment and rate updated";
        } catch (NullPointerException ex) {
            return "The user with id " + userId + " does not exist!";
        }
    }

    @DeleteMapping(path = "/delCommentAndRate")
    public @ResponseBody
    String deleteCommentAndRate(@RequestParam Long userId) {
        try {CommentAndRating commentAndRating=commentAndRatingRepository.findCommentAndRateByUserId(userId);
            commentAndRatingRepository.delete(commentAndRating);


            return "The comment and rate with userId " + userId + " has been deleted";
        } catch (Exception ex) {
            return "The user with userId " + userId + " does not exist!";
        }
    }

}
