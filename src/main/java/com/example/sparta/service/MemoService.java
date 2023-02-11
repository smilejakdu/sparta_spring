package com.example.sparta.service;

import com.example.sparta.domain.MemoRequestDto;
import com.example.sparta.memo.Memo;
import com.example.sparta.repository.MemoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;

    @Transactional
    public List<Memo> getMemoList() {
        return memoRepository.findAllByOrderByModifiedAtDesc();
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
