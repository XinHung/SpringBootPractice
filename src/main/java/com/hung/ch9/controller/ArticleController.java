package com.hung.ch9.controller;

import com.hung.model.Article;
import com.hung.result.BaseResult;
import com.hung.result.DataResult;
import com.hung.utils.HttpStatusMap;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    static List<Article> articles = new ArrayList<>();
    static {
        articles.add(new Article(1, "《Spring Boot好簡單》"));
        articles.add(new Article(2, "《Java好棒棒》"));
        articles.add(new Article(3, "《Vue.js 3.0從入門到實戰》"));
    }


    // 保存新的文章
    @PostMapping
    public ResponseEntity<BaseResult> saveArticle(@RequestBody Article article){
        articles.add(article);
        System.out.println(articles);
        BaseResult result = new BaseResult(HttpStatusMap.get(HttpStatus.OK), "新增成功");
        return ResponseEntity.ok(result);
    }

    // 根据ID查詢文章
    @GetMapping("/{id}")
    public ResponseEntity<BaseResult> getArticleById(@PathVariable Integer id){
        Optional<Article> opArticle = articles.stream()
                .filter(art -> art.getId() == id).findFirst();

        try {
            Article article = opArticle.get();
            DataResult result = new DataResult();
            result.setCode(HttpStatusMap.get(HttpStatus.OK));
            result.setMsg("成功");
            result.setData(article);
            return ResponseEntity.ok(result);
        } catch(NoSuchElementException e){
            BaseResult result = new BaseResult(HttpStatusMap.get(HttpStatus.BAD_REQUEST), "參數不合法");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }

    // 取得所有文章
    @GetMapping
    public ResponseEntity<DataResult<List<Article>>> getAllArticles(){
        DataResult result = new DataResult();
        result.setCode(HttpStatusMap.get(HttpStatus.OK));
        result.setMsg("成功");
        result.setData(articles);
        return ResponseEntity.ok(result);
    }

    // 修改文章
    @PutMapping
    public ResponseEntity<BaseResult> updateArticle(@RequestBody Article article){
        Optional<Article> opArticle = articles.stream()
                .filter(art -> art.getId() == article.getId())
                .findFirst();

        try {
            Article updatedArticle = opArticle.get();
            BeanUtils.copyProperties(article, updatedArticle);
            System.out.println(articles);
            BaseResult result = new BaseResult(HttpStatusMap.get(HttpStatus.OK), "修改成功");
            return ResponseEntity.ok(result);
        } catch(NoSuchElementException e){
            BaseResult result = new BaseResult(HttpStatusMap.get(HttpStatus.BAD_REQUEST), "参數不合法");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }

    // 根据ID删除文章
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResult> deleteArticle(@PathVariable Integer id){
        Optional<Article> opArticle = articles.stream()
                .filter(art -> art.getId() == id).findFirst();
        try{
            Article article = opArticle.get();
            articles.remove(article);
            System.out.println(articles);
            BaseResult result = new BaseResult(HttpStatusMap.get(HttpStatus.OK), "刪除成功");
            return ResponseEntity.ok(result);
        } catch(NoSuchElementException e) {
            BaseResult result = new BaseResult(HttpStatusMap.get(HttpStatus.BAD_REQUEST), "参數不合法");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }
}

