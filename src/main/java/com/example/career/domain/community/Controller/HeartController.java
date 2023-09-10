package com.example.career.domain.community.Controller;

import com.example.career.domain.community.Dto.ArticleDto;
import com.example.career.domain.community.Dto.HeartDto;
import com.example.career.domain.community.Entity.Article;
import com.example.career.domain.community.Entity.Heart;
import com.example.career.domain.community.Repository.ArticleRepository;
import com.example.career.domain.community.Repository.HeartRepository;
import com.example.career.domain.community.Service.ArticleService;
import com.example.career.domain.community.Service.HeartService;
import com.example.career.global.annotation.Authenticated;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/community/heart")
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;
    private final ArticleService articleService;

    @Authenticated
    @GetMapping("all_article")
    public ResponseEntity<List<Article>> allThumbsUpArticles(@RequestParam int page, @RequestParam int size, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        List<Article> articles = heartService.getAllThumbsUpArticles(userId, page, size);
        return ResponseEntity.ok(articles);
    }

    @Transactional
    @Authenticated
    @PostMapping("/add")
    public ResponseEntity<Heart> addHeart(@RequestBody HeartDto heartDto, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Heart heart = heartService.addHeart(heartDto, userId);
        //TODO: 댓글, 대댓글 좋아요 갯수 늘리기 구현
        int type = heartDto.getType();
        if (type == 0) {
            articleService.incrementThumbsUpCnt(heartDto.getTypeId());
        } else if (heartDto.getType() == 1) { // 댓글에 좋아요
        } else { // 대댓글에 좋아요
        }
        return ResponseEntity.ok(heart);
    }

    @Transactional
    @Authenticated
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteArticle(@RequestBody HeartDto heartDto, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        heartService.deleteHeart(heartDto, userId);
        //TODO: 댓글, 대댓글 좋아요 갯수 decrement 구현
        int type = heartDto.getType();
        if (type == 0) {
            articleService.decrementThumbsUpCnt(heartDto.getTypeId());
        } else if (heartDto.getType() == 1) { // 댓글에 좋아요
        } else { // 대댓글에 좋아요
        }
        return ResponseEntity.ok().build();
    }

}