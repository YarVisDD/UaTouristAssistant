package com.main.uatouristassistant.controller;

import com.main.uatouristassistant.entity.CommentAndRating;
import com.main.uatouristassistant.entity.RateType;
import com.main.uatouristassistant.service.CommentAndRatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping(path = "/comment")

public class CommentAndRatingController extends HttpServlet {

    @Autowired
    private CommentAndRatingService commentAndRatingService;


    @PostMapping(path = "/save-comment")

    public String addCommentAndRate(@RequestParam String userLogin,
                                    @RequestParam String comment,
                                    @RequestParam RateType rateType,
                                    @RequestParam Long idPlace,HttpServletRequest request) {
        String errorMessage=commentAndRatingService.errorMessage(comment);
        if(!errorMessage.isEmpty()){
            request.setAttribute("error",errorMessage);
            return "/comment/add-comment";
        }else {
        commentAndRatingService.saveCommentAndRate(userLogin, comment, rateType, idPlace);

            return "redirect:/place/show-place/" + idPlace;
        }
    }

    @RequestMapping("/add-comment")
    public String addCommentPage(HttpServletRequest request) {
        return "/comment/add-comment";
    }

    @GetMapping(path = "/list")
    public @ResponseBody
    Iterable<CommentAndRating> AllCommentsAndRate() {
        return commentAndRatingService.getAllCommentsAndRate();
    }

    @GetMapping(path = "/listCommentsAndRate")
    @ResponseBody
    public String getCommentAndRate(@RequestParam String userLogin) {
        commentAndRatingService.getCommentAndRate(userLogin);
        return "/place/show-places";
    }

    @PostMapping(path = "/updateCommentAndRate")
    public @ResponseBody
    String updateCommentAndRate(@RequestParam String userLogin,
                                @RequestParam String comment,
                                @RequestParam RateType rateType,
                                @RequestParam Long id) {
        commentAndRatingService.updCommentAndRate(userLogin, comment, rateType, id);
        return "redirect:/place/show-places";
    }

    @DeleteMapping(path = "/delCommentAndRate")
    public @ResponseBody
    String deleteCommentAndRate(@RequestParam Long commentId) {
        commentAndRatingService.delCommentAndRate(commentId);
        return "redirect:/place/show-places";
    }

}
