package com.example.career.domain.search.Controller;

import com.example.career.domain.community.Entity.Article;
import com.example.career.domain.community.Repository.ArticleRepository;
import com.example.career.domain.community.Repository.CommentRepository;
import com.example.career.domain.search.Dto.CommunitySearchRespDto;
import com.example.career.domain.search.Service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("search")
public class SearchController {
    private final SearchService searchService;

    @GetMapping("community")
    public List<CommunitySearchRespDto> searchCommunityList(@RequestParam String keyWord) {
        return searchService.getArticlesByKeyWord(keyWord);
  }
}
