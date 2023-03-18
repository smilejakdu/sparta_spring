package com.example.sparta.service;

import com.example.sparta.domain.Reply;
import com.example.sparta.domain.Review;
import com.example.sparta.domain.User;
import com.example.sparta.dto.ReplyDto.CreateReplyRequestDto;
import com.example.sparta.dto.ReplyDto.CreateReplyResponseDto;
import com.example.sparta.repository.ReplyRepository;
import com.example.sparta.repository.ReviewRepository;
import com.example.sparta.shared.Exception.HttpException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public CreateReplyResponseDto createReply(
            User foundUser,
            CreateReplyRequestDto requestDto
    ) {
        Review foundReview = reviewRepository.findById(requestDto.getReviewId())
                .orElseThrow(() -> new HttpException("해당 리뷰가 없습니다.", HttpStatus.BAD_REQUEST));

        Reply reply  = Reply.builder()
                .content(requestDto.getContent())
                .review(foundReview)
                .user(foundUser)
                .build();

        Reply savedReply = replyRepository.save(reply);
        return CreateReplyResponseDto
                .builder()
                .reviewId(savedReply.getReview().getId())
                .reviewUserEmail(savedReply.getUser().getEmail())
                .reviewContent(savedReply.getContent())
                .reviewCreatedAt(savedReply.getReview().getCreatedAt())
                .replyId(savedReply.getId())
                .replyUserEmail(savedReply.getUser().getEmail())
                .replyContent(savedReply.getContent())
                .build();
    }
}
