package com.main.uatouristassistant.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CommentAndRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    private String userLogin;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "place_id")
    private Place place;
    private String comment;


    @Enumerated(EnumType.STRING)
    private RateType rateType;

}
