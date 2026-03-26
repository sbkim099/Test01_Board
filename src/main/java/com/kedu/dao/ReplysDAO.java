package com.kedu.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kedu.dto.ReplysDTO;

@Repository
public class ReplysDAO {
	
	@Autowired
	private JdbcTemplate jdbc;

	public int insert(ReplysDTO dto, int parent_seq) {
		String sql = "insert into reply values(reply_seq.nextval,?,?,sysdate,?)";
		
		return jdbc.update(sql, dto.getWriter(), dto.getContents(), parent_seq);
	}
	
	public List<ReplysDTO> selectAll(){ 
		String sql = "select * from reply order by write_date desc";
		
		return jdbc.query(sql, new BeanPropertyRowMapper<ReplysDTO>(ReplysDTO.class));
	}
	
	public void delete(int seq, int parent_seq) {
		String sql = "delete from reply where seq=? and parent_seq=?";
		jdbc.update(sql,seq,parent_seq);
	}
	
	public int update(String contents, int seq, int parent_seq) {
		String sql = "update reply set contents = ? where seq =? and parent_seq=?";
		return jdbc.update(sql,contents,seq, parent_seq);
	}
}	
