// 代码生成时间: 2025-10-02 02:13:26
package com.example.demo.service;

import com.example.demo.exception.RankListException;
import com.example.demo.model.Rank;
import com.example.demo.repository.RankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RankListService {

    @Autowired
    private RankRepository rankRepository;

    // 获取排行榜列表
    public List<Rank> getRankList() throws RankListException {
        try {
            return rankRepository.findAll();
        } catch (Exception e) {
            throw new RankListException("Error fetching rank list");
        }
    }

    // 添加新的排名
    public Rank addRank(Rank rank) throws RankListException {
        try {
            return rankRepository.save(rank);
        } catch (Exception e) {
            throw new RankListException("Error adding new rank");
        }
    }
}

// RankListService的异常类
package com.example.demo.exception;

public class RankListException extends Exception {

    public RankListException(String message) {
        super(message);
    }
}

// Rank实体类
package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int score;

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

// RankRepository接口
package com.example.demo.repository;

import com.example.demo.model.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RankRepository extends JpaRepository<Rank, Long> {

    List<Rank> findAllByOrderByIdDesc();
}

// RankListController控制器
package com.example.demo.controller;

import com.example.demo.model.Rank;
import com.example.demo.service.RankListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.exception.RankListException;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/ranks")
public class RankListController {

    @Autowired
    private RankListService rankListService;

    // 获取排行榜列表
    @GetMapping
    public ResponseEntity<?> getRankList() {
        try {
            return ResponseEntity.ok(rankListService.getRankList());
        } catch (RankListException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // 添加新的排名
    @PostMapping
    public ResponseEntity<?> addRank(@RequestBody Rank rank) {
        try {
            return ResponseEntity.ok(rankListService.addRank(rank));
        } catch (RankListException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
