package com.example.sparta.controller;

import com.example.sparta.domain.MemoRequestDto;
import com.example.sparta.memo.Memo;
import com.example.sparta.service.MemoService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/memo")
public class MemoController {
    private final MemoService memoService;

    @PostMapping()
    public Memo createMemo(
            @RequestBody MemoRequestDto requestDto
    ) {
        return memoService.createMemo(requestDto);
    }

    @GetMapping()
    public List<Memo> getMemo() {
        return memoService.getMemoList();
    }

    @PutMapping("/{id}")
    public Long updateMemo(
            @PathVariable Long id,
            @RequestBody MemoRequestDto requestDto
    ) {
        return memoService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        return memoService.delete(id);
    }
}
