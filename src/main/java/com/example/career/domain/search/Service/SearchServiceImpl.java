package com.example.career.domain.search.Service;

import com.example.career.domain.community.Dto.Brief.UserBrief;
import com.example.career.domain.community.Dto.Brief.UserBriefWithRate;
import com.example.career.domain.community.Dto.response.ArticleDto;
import com.example.career.domain.community.Entity.Article;
import com.example.career.domain.community.Entity.Comment;
import com.example.career.domain.community.Entity.Recomment;
import com.example.career.domain.community.Repository.ArticleRepository;
import com.example.career.domain.community.Repository.CommentRepository;
import com.example.career.domain.community.Repository.RecommentRepository;
import com.example.career.domain.community.Service.ArticleService;
import com.example.career.domain.search.Dto.CommunitySearchRespDto;
import com.example.career.domain.user.Entity.TutorDetail;
import com.example.career.domain.user.Repository.FAQRepository;
import com.example.career.domain.user.Repository.SchoolRepository;
import com.example.career.domain.user.Repository.TutorDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService{
    private final ArticleRepository articleRepository;
    private final ArticleService articleService;
    private final TutorDetailRepository tutorDetailRepository;
    private final SchoolRepository schoolRepository;
    private final FAQRepository faqRepository;
    @Override
    public List<ArticleDto> getArticlesByKeyWord(Long userId, String keyWord, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Article> articles = articleRepository.findAllBySearchKeyWord(keyWord, pageable);

        return articleService.convertToArticleDtoList(articles,userId);
    }

    @Override
    public List<UserBriefWithRate> getUserByTags(String keyword, String type, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<UserBriefWithRate> userBriefWithRates;
        if (type.equals("rank")) {
            userBriefWithRates = tutorDetailRepository.findByKeywordOrderByRateCountDesc(keyword, pageable).getContent();
        } else if (type.equals("rate")) {
            userBriefWithRates = tutorDetailRepository.findByKeywordOrderByRateAvgDesc(keyword, pageable).getContent();
        } else {
            throw new IllegalArgumentException("Invalid type: " + type);
        }
        if (userBriefWithRates != null) {
            for(int i=0; i< userBriefWithRates.size(); i++) {
                try{
                    userBriefWithRates.get(i).setSchoolList(schoolRepository.findAllByTutorIdOrderByIdxAsc(userBriefWithRates.get(i).getId()));

                }catch(NullPointerException e) {
                    userBriefWithRates.get(i).setSchoolList(null);
                }
            }
        }
        return userBriefWithRates;
    }

    @Override
    public List<UserBriefWithRate> getUserByRecently(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<UserBriefWithRate> userBriefWithRates;
        userBriefWithRates = tutorDetailRepository.findByUserRecently(pageable).getContent();

        if (userBriefWithRates != null) {
            for(int i=0; i< userBriefWithRates.size(); i++) {
                try{
                    userBriefWithRates.get(i).setSchoolList(schoolRepository.findAllByTutorIdOrderByIdxAsc(userBriefWithRates.get(i).getId()));

                }catch(NullPointerException e) {
                    userBriefWithRates.get(i).setSchoolList(null);
                }
            }
        }
        return userBriefWithRates;
    }
}
