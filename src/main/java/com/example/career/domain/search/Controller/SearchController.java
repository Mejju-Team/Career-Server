package com.example.career.Controller;

import com.example.career.domain.community.Entity.Article;
import com.example.career.domain.community.Repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("search")
public class SearchController {
    private final ArticleRepository articleRepository;

    @GetMapping("community")
    public List<Article> searchCommunityList(@RequestParam String keyWord) {
        return articleRepository.findAllByTitleContainingOrContentContaining(keyWord, keyWord);
    }
}
