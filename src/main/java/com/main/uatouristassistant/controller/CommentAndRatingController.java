package com.main.uatouristassistant.controller;

import com.main.uatouristassistant.entity.CommentAndRating;
import com.main.uatouristassistant.repository.CommentAndRatingRepository;
import com.main.uatouristassistant.service.CommentAndRatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping(path = "/comment")

public class CommentAndRatingController {
    @Autowired
    private CommentAndRatingRepository commentAndRatingRepository;
@Autowired
private CommentAndRatingService commentAndRatingService;

    @PostMapping(path = "/addCommentAndRate")
     @ResponseBody
   public  String addCommentAndRate(@RequestParam Long userId,
                              @RequestParam String comment,
                              @RequestParam String rate) {
        commentAndRatingService.saveCommentAndRate(userId,comment,rate);

        return "/place/show-places";
    }

    @GetMapping(path = "/list")
    public @ResponseBody
    Iterable<CommentAndRating> AllCommentsAndRate() {
        return commentAndRatingService.getAllCommentsAndRate();
    }

    @GetMapping(path = "/listCommentsAndRate")
     @ResponseBody
    public String getCommentAndRate(@RequestParam Long userId) {
        commentAndRatingService.getCommentAndRate(userId);
        return "/place/show-places";
    }

    @PostMapping(path = "/updateCommentAndRate")
    public @ResponseBody
    String updateCommentAndRate(@RequestParam Long userId,
                                @RequestParam String comment,
                                @RequestParam String rate){
        commentAndRatingService.updCommentAndRatee(userId,comment,rate);
        return "redirect:/place/show-places";
    }

    @DeleteMapping(path = "/delCommentAndRate")
    public @ResponseBody
    String deleteCommentAndRate(@RequestParam Long commendId) {
      commentAndRatingService.delCommentAndRate(commendId);
      return "redirect:/place/show-places";
    }

}
