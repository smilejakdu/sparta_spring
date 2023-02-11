package com.example.sparta.service;

import com.example.sparta.domain.MemoRequestDto;
import com.example.sparta.memo.Memo;
import com.example.sparta.repository.MemoRepository;
import lombok.AllArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;

    @Transactional(readOnly = true)
    public List<Memo> getMemoList() {
        LocalDateTime beforeOneDay = LocalDateTime.now().minusDays(1);
        LocalDateTime nowDay = LocalDateTime.now();
        return memoRepository.findAllByModifiedAtBeforeOrderByModifiedAtDesc(
                nowDay,
                beforeOneDay
        );
    }

    @Transactional
    public Memo createMemo(MemoRequestDto requestDto) {
        Memo memo = new Memo();
        memo.setTitle(requestDto.getTitle());
        memo.setContents(requestDto.getContents());

        return memoRepository.save(memo);
    }

    @Transactional
    public Long delete(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );

        memoRepository.delete(memo);
        return id;
    }

    @Transactional
    public Long update(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );

        memo.setTitle(requestDto.getTitle());
        memo.setContents(requestDto.getContents());

        memoRepository.save(memo);
        return id;
    }
}
