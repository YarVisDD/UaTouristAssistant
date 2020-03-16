package com.main.uatouristassistant.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CommentAndRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;
    //private Long userId;
    private  String userLogin;
    private String comment;
    //private String rate;
    private Long placeId;
    @Enumerated(EnumType.STRING)
    private RateType rateType;

}
