package com.example.career.domain.community.Service;

import com.example.career.domain.community.Dto.HeartDto;
import com.example.career.domain.community.Entity.Article;
import com.example.career.domain.community.Entity.Heart;
import com.example.career.domain.community.Repository.HeartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;

    public List<Article> getAllThumbsUpArticles(Long userId, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Article> result = heartRepository.findArticlesByUserIdAndType(userId, 0, pageable);
        return result.getContent();
    }

    public Heart addHeart(HeartDto heartDto, Long userId) {
        return heartRepository.save(heartDto.toHeartEntity(userId));
    }

    @Transactional
    public void deleteHeart(HeartDto heartDto, Long userId) {
        heartRepository.deleteByUserIdAndTypeIdAndType(userId, heartDto.getTypeId(), heartDto.getType());
    }
}