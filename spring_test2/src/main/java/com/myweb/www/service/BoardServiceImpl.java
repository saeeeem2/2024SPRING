package com.myweb.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.BoardDAO;
import com.myweb.www.repository.FileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

	private final BoardDAO bdao;
	private final FileDAO fdao;

	@Transactional
	@Override
	public int insert(BoardDTO bdto) {
		log.info("insert service in");
		//bvo boardMapper / flist fileMapper로 등록
		int isOk=bdao.insert(bdto.getBvo());
		if(bdto.getFlist()==null) {
			return isOk;			
		}
		
		//bvo insert후 파일도 있다면
		 if(isOk>0&&bdto.getFlist().size()>0) {
			 //bno setting
			 long bno=bdao.selectOneBno(); //가장 마지막에 등록된 bno
			 for(FileVO fvo : bdto.getFlist()) {
				 fvo.setBno(bno);
				 fdao.insertFile(fvo);
			 }
		 }
		 return isOk;
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		
		return bdao.getList(pgvo);
	}

	@Override
	public BoardDTO getDetail(int bno) {
		bdao.readcount(bno,1); 
		BoardVO bvo = bdao.getDetail(bno);
		List<FileVO> flist = fdao.getFileList(bno);
		BoardDTO bdto = new BoardDTO(bvo,flist);
		
		return bdto;
	}

	@Transactional
	@Override
	public int modify(BoardDTO bdto) {
		bdao.readcount(bdto.getBvo().getBno(), -2);
		int isOk=bdao.modify(bdto.getBvo());
		if(bdto.getFlist()==null) {
			return isOk;
		}
		if(isOk>0&&bdto.getFlist().size()>0) {
			long bno=bdao.selectOneBno();
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bno);
				fdao.insertFile(fvo);
			}
		}
		return isOk;
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.getTotalCount(pgvo);
	}

	@Override
	public void delete(int bno) {
		bdao.delete(bno);		
	}

	@Override
	public int deleteFile(String uuid) {
		return fdao.delete(uuid);
	}
}
