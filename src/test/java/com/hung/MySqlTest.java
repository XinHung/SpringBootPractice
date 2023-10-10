package com.hung;

import java.sql.*;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

import com.hung.ch11.incrementer.MaxValueIncrementer;
import com.hung.ch11.persistence.Book;
import com.hung.ch11.persistence.Category;

@SpringBootTest
public class MySqlTest {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//@Test
	void testStamentCallback() {
		jdbcTemplate.execute((Statement stmt) -> {
			String sql = "insert into category(id, name, root, parent_id) values (1, 'Java EE', 1, null)";
			return stmt.executeUpdate(sql);
		});
	}
	
	//@Test
	void testPreparedStamentCallback() {
		jdbcTemplate.execute((Connection conn) -> {
			String sql = "insert into category(id, name, root, parent_id) values (?, ?, ?, ?)";
			return conn.prepareStatement(sql);
		}, (PreparedStatement ps) -> {
			ps.setInt(1, 2);
			ps.setString(2, "程式設計");
			ps.setBoolean(3, true);
			ps.setNull(4, Types.NULL);
			return ps.executeUpdate();
		});
	}
	
	//@Test
    void testPreparedStatementSetter() {
        String sql = "insert into category(id, name, root, parent_id) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, (PreparedStatement ps) -> {
            ps.setInt(1, 3);
            ps.setString(2, "Servlet/JSP");
            ps.setBoolean(3, false);
            ps.setInt(4, 1);
        });
    }
    
    //@Test
    void testResultSetExtractor() {
        String sql = "select * from category where id = ?";
        Category cat = jdbcTemplate.query(sql, (ResultSet rs) -> {
            rs.next();
            Category category  = new Category();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            category.setRoot(rs.getBoolean("root"));
            Object parentId = rs.getObject("parent_id");
            if(parentId == null)
                category.setParentId(null);
            else
                category.setParentId((Integer)parentId);
            return category;
        }, 1);
        System.out.println(cat);
    }
    
    //@Test
    void testRowCallbackHandler() {
        String sql = "select * from category where id = ?";
        Category cat = new Category();
        jdbcTemplate.query(sql, (ResultSet rs) -> {
            cat.setId(rs.getInt("id"));
            cat.setName(rs.getString("name"));
            cat.setRoot(rs.getBoolean("root"));
            Object parentId = rs.getObject("parent_id");
            if(parentId == null)
                cat.setParentId(null);
            else
                cat.setParentId((Integer)parentId);
        }, 1);
        System.out.println(cat);
    }
    
    //@Test
    void testRowMapper() {
        String sql = "select * from category";
        List<Category> categories = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Category category  = new Category();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            category.setRoot(rs.getBoolean("root"));
            Object parentId = rs.getObject("parent_id");
            if(parentId == null)
                category.setParentId(null);
            else
                category.setParentId((Integer)parentId);
            return category;
        });
        System.out.println(categories);
    }
    
    //@Test
    void testIncrementer() {
        String sql = "select current_id from id_sequence where table_name = 'category' for update";
        int id = jdbcTemplate.queryForObject(sql, Integer.class);
        id++;
        String sqlUpdate = "update id_sequence set current_id = ? where table_name = 'category'";
        jdbcTemplate.update(sqlUpdate, id);
        String sqlInsert = "insert into category(id, name, root, parent_id) values (?, ?, ?, ?)";
        int finalId = id;
        jdbcTemplate.update(sqlInsert, (PreparedStatement ps) -> {
            ps.setInt(1, finalId);
            ps.setString(2, "MVC框架");
            ps.setBoolean(3, false);
            ps.setInt(4, 1);
        });
    }
    
    @Autowired
    private MaxValueIncrementer maxValueIncrementer;
    //@Test
    void testMaxValueIncrementer() {
        String sqlInsert = "insert into category(id, name, root, parent_id) values (?, ?, ?, ?)";
        int id = maxValueIncrementer.getNextValue("category");
        jdbcTemplate.update(sqlInsert, (PreparedStatement ps) -> {
            ps.setInt(1, id);
            ps.setString(2, "Java");
            ps.setBoolean(3, false);
            ps.setInt(4, 2);
        });
    }
    
    @Test
    void testKeyHolder() {
        String sqlInsert = "insert into books(title, author, book_concern, publish_date, price, category_id) values(?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement ps =
                    conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, " SpringBoot 從實戰中快速上手");
            ps.setString(2, "孫鑫");
            ps.setString(3, "電子工業出版社");
            ps.setDate(4, Date.valueOf("2019-06-01"));
            ps.setFloat(5, 168.00f);
            ps.setInt(6, 6);
            return ps;
        }, keyHolder);

        System.out.println("New kye id: " + keyHolder.getKey());
    }
}
