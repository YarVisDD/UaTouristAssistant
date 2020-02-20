package com.main.uatouristassistant.controller;

import com.main.uatouristassistant.entity.CommentAndRating;
import com.main.uatouristassistant.entity.User;
import com.main.uatouristassistant.repository.CommentAndRatingRepository;
import com.main.uatouristassistant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/comment")

public class CommentAndRatingController {
    @Autowired
    private CommentAndRatingRepository commentAndRatingRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/addCommentAndRate")
    public @ResponseBody
    boolean addCommentAndRate(String comment, String rate) {
        CommentAndRating commentAndRating = new CommentAndRating();
        User user = new User();

        commentAndRating.setComment(comment);
        commentAndRating.setRate(rate);
        commentAndRating.setUserId(user.getUserId());
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

}
