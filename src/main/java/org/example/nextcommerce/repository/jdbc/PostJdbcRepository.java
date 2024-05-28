package org.example.nextcommerce.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.example.nextcommerce.dto.PostDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
public class PostJdbcRepository {
    private final JdbcTemplate jdbcTemplate;

    private RowMapper<PostDto> postDtoRowMapper(){
        return ( (rs, rowNum) -> {
            PostDto dto = PostDto.builder()
                    .postId(rs.getLong("post_id"))
                    .memberId(rs.getLong("member_id"))
                    .productId(rs.getLong("product_id"))
                    .content(rs.getString("content"))
                    .category(rs.getString("category"))
                    .title(rs.getString("title"))
                    .build();
            return dto;
        });
    }

    public Long save(PostDto dto){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO posts (member_id,product_id, content, category, created_time , modified_time ,title )"
                + "VALUES (?, ?, ?, ?, now() , now() ,?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement( sql, new String[] {"post_id"} );
                pstmt.setLong(1, dto.getMemberId());
                pstmt.setLong(2, dto.getProductId());
                pstmt.setString(3, dto.getContent());
                pstmt.setString(4, dto.getCategory());
                pstmt.setString(5, dto.getTitle());
                return pstmt;
            }
        }, keyHolder);
        dto.updatePostId(keyHolder.getKey().longValue());
        return dto.getPostId();
    }


}
