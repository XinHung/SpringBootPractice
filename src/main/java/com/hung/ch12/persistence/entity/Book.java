package com.hung.ch12.persistence.entity;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

import jakarta.persistence.*;

@Data
@ToString
// 設定此類別為實體類別（和資料庫 table 相對應）
@Entity
// 設定資料表名稱，如果没有設定，預設為book
@Table(name = "bookinfo")
public class Book {
    // 設定此欄位為 primary key
    @Id
    // 指定主鍵的產生方式
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 主鍵

    // 指定資料表欄位，長度為 100，不可為空，欄位名稱預設為變數名稱
    @Column(length=100, nullable = false)
    private String title; // 書名

    @Column(length=100, nullable = false)
    private String author; // 作者

    // name 指定欄位名稱
    @Column(name="bookconcern", length=100, nullable = false)
    private String bookConcern; // 出版社

    @Column(nullable = false)
    private LocalDate publishDate;  // 出版日期

    // columnDefinition 元素指定為欄位生成 DDL 時使用 SQL 片段，用於某些特殊情況
    @Column(columnDefinition = "decimal(6,2)")
    private Float price; // 價格
    
    // 沒有使用 @Column，將按照預設值新增到資料表中
    private Integer inventory; // 庫存

    @Column(length=500)
    private String brief;  // 簡介
}
