package com.example.career.domain.community.Controller;

import com.example.career.domain.community.Dto.ArticleDto;
import com.example.career.domain.community.Dto.HeartDto;
import com.example.career.domain.community.Entity.Article;
import com.example.career.domain.community.Entity.Heart;
import com.example.career.domain.community.Repository.HeartRepository;
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
    private final HeartRepository heartRepository;

    @Authenticated
    @GetMapping("all_article")
    public ResponseEntity<List<Article>> allThumbsUpArticles(@RequestParam int page, @RequestParam int size, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Article> result = heartRepository.findArticlesByUserIdAndType(userId, 0, pageable);
        List<Article> entities = result.getContent(); // 실제 데이터 리스트
        return ResponseEntity.ok(entities);
    }

    @Authenticated
    @PostMapping("/add")
    public ResponseEntity<Heart> addHeart(@RequestBody HeartDto heartDto, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Heart heart = heartRepository.save(heartDto.toHeartEntity(userId));
        return ResponseEntity.ok(heart);
    }

    @Authenticated
    @Transactional
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteArticle(@RequestBody HeartDto heartDto, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        heartRepository.deleteByUserIdAndTypeIdAndType(userId, heartDto.getTypeId(), heartDto.getType());
        return ResponseEntity.ok().build();
    }

}
