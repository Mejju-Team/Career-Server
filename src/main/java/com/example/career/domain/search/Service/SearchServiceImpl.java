package com.example.career.domain.search.Service;

import com.example.career.domain.community.Entity.Article;
import com.example.career.domain.community.Entity.Comment;
import com.example.career.domain.community.Entity.Recomment;
import com.example.career.domain.community.Repository.ArticleRepository;
import com.example.career.domain.community.Repository.CommentRepository;
import com.example.career.domain.community.Repository.RecommentRepository;
import com.example.career.domain.search.Dto.CommunitySearchRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService{
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final RecommentRepository recommentRepository;
    @Override
    public List<CommunitySearchRespDto> getArticlesByKeyWord(String keyWord) {
        List<Article> articles = articleRepository.findAllByTitleContainingOrContentContaining(keyWord, keyWord);
        List<Article> comments = commentRepository.searchArticlesByCommentContent(keyWord);
        List<Article> recomms = recommentRepository.searchArticlesByRecommentContent(keyWord);

        // Comment 및 Recomment의 Article 정보를 가져와서 articles에 추가
        articles.addAll(comments);
        articles.addAll(recomms);

        List<CommunitySearchRespDto> communitySearchRespDtos = articles.stream()
                .distinct()
                .map(Article::toDto)
                .sorted(Comparator.comparing(CommunitySearchRespDto::getId).reversed()) // 정렬 코드 추가
                .collect(Collectors.toList());
        return communitySearchRespDtos;
    }
}
