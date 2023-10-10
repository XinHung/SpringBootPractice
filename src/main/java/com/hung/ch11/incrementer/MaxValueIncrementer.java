package com.hung.ch11.incrementer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MaxValueIncrementer {
    // 資料來源
    @Autowired
    private JdbcTemplate jdbcTemplate;
    // 資料表名稱
    @Value("${incrementer.tableName}")
    private String tableName;
    // 資料表的欄位名稱
    @Value("${incrementer.tableColumnName}")
    private String tableColumnName;
    // 序列值的欄位名稱
    @Value("${incrementer.valueColumnName}")
    private String valueColumnName;

    /**
     * 得到參數 queryTableName 指定的資料表明稱的下一個序列值
     * @param queryTableName 資料表名稱
     * @return 下一個 ID
     */
    public int getNextValue(String queryTableName) {
        String sqlQuery = "select " + valueColumnName + " from "
                + tableName + " where " + tableColumnName
                + " = '" + queryTableName + "' for update";

        Integer id = jdbcTemplate.queryForObject(sqlQuery, Integer.class);
        if(id == null)
            id = 0;
        id++;
        String sqlUpdate = "update " + tableName + " set " + valueColumnName
                + " = " + id + " where " + tableColumnName + " = " + "'"
                + queryTableName + "'";

        jdbcTemplate.update(sqlUpdate);
        return id;
    }
}
