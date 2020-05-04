package com.main.uatouristassistant.service;

import com.main.uatouristassistant.entity.CommentAndRating;
import com.main.uatouristassistant.entity.Place;
import com.main.uatouristassistant.entity.RateType;
import com.main.uatouristassistant.repository.CommentAndRatingRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.Mockito.doNothing;

@RunWith(SpringRunner.class)
@SpringBootTest
class CommentAndRatingServiceTest {

    @Autowired
    private CommentAndRatingService commentAndRatingService;
    @MockBean
    private CommentAndRatingRepository mockCommentAndRatingRepository;
    @MockBean
    private PlaceService mockPlaceService;
    @MockBean
    private CommentAndRating mockCommentAndRating;

    private List<CommentAndRating> commentAndRatingList;

    @Test
    void saveCommentAndRate() {
        String userLogin = "testLogin";
        String comment = "testComment";
        RateType rateType = RateType.EXCELLENT;
        Place place = new Place();

        Mockito.when(mockPlaceService.getPlaceById(Mockito.anyLong())).thenReturn(place);
        Mockito.when(mockCommentAndRatingRepository.save(Mockito.any(CommentAndRating.class)))
                .thenReturn(Mockito.any(CommentAndRating.class));

        commentAndRatingService.saveCommentAndRate(userLogin, comment, rateType, place.getIdPlace());
    }

    @Test
    void getCommentByPlace() {
        Mockito.when(mockCommentAndRatingRepository.findByPlaceIdPlace(Mockito.anyLong())).
                thenReturn(commentAndRatingList);
        commentAndRatingService.getCommentByPlace(Mockito.anyLong());
    }

    @Test
    void getCommentAndRate() {
        Mockito.when(mockCommentAndRatingRepository.findCommentAndRateByUserLogin(Mockito.anyString())).
                thenReturn(mockCommentAndRating);
        commentAndRatingService.getCommentAndRate(Mockito.anyString());
    }

    @Test
    void getAllCommentAndRate() {
        Mockito.when(mockCommentAndRatingRepository.findAll()).
                thenReturn(commentAndRatingList);
        commentAndRatingService.getAllCommentsAndRate();
    }

    @Test
    void delCommentAndRate() {

        Mockito.when(mockCommentAndRatingRepository.existsByCommentId(Mockito.anyLong())).
                thenReturn(true);
        Mockito.when(mockCommentAndRatingRepository.findCommentAndRatingByCommentId(mockCommentAndRating.getCommentId())).
                thenReturn(mockCommentAndRating);
        doNothing().when(mockCommentAndRatingRepository).delete(Mockito.any(CommentAndRating.class));
        Assert.assertTrue(commentAndRatingService.delCommentAndRate(mockCommentAndRating.getCommentId()));
        Mockito.when(mockCommentAndRatingRepository.existsByCommentId(Mockito.anyLong())).
                thenReturn(false);
        Assert.assertFalse(commentAndRatingService.delCommentAndRate(mockCommentAndRating.getCommentId()));

    }

    @Test
    void updateCommentAndRate() {
        Place place = new Place();
        String userLogin="testLogin";
        String comment="testComment";
        RateType rateType=RateType.GOOD;

        Mockito.when(mockCommentAndRatingRepository.existsByUserLogin(Mockito.anyString())).
                thenReturn(true);
        Mockito.when(mockCommentAndRatingRepository.findCommentAndRateByUserLogin(Mockito.anyString())).
                thenReturn(mockCommentAndRating);
        Mockito.when(mockPlaceService.getPlaceById(Mockito.anyLong())).
                thenReturn(place);
        Mockito.when(mockCommentAndRatingRepository.save(mockCommentAndRating)).thenReturn(mockCommentAndRating);
        Assert.assertTrue(commentAndRatingService.updCommentAndRate(userLogin,
                comment,
                rateType,
                place.getIdPlace()));
        Mockito.when(mockCommentAndRatingRepository.existsByUserLogin(Mockito.anyString())).
                thenReturn(false);
        Assert.assertFalse(commentAndRatingService
                .updCommentAndRate(mockCommentAndRating
                                .getUserLogin(),
                        mockCommentAndRating
                                .getComment(),
                        mockCommentAndRating
                                .getRateType(),
                        place.getIdPlace()));
    }
}
