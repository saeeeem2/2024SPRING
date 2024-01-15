package com.myweb.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	BoardVO getDetail(int bno);

	int modify(BoardVO bvo);

	int getTotalCount(PagingVO pgvo);

	long selectOneBno();

	void delete(int bno);

	void readcount(@Param("bno")long bno,@Param("cnt")int cnt);

	void commentCountUp(long bno);

	void commentCountDown(long bno);

}
