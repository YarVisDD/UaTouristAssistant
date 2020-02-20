package com.main.uatouristassistant.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class CommentAndRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;
    private Long userId;
    private String comment;
    private String rate;
    private Long placeId;


}
