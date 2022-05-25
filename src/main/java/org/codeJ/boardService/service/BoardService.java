package org.codeJ.boardService.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.codeJ.boardService.entity.Board;
import org.codeJ.boardService.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardService {

    private final BoardRepository boardRepository;

    public void write(Board board, MultipartFile file) throws Exception{
        String path =System.getProperty("user.dir")+"/src/main/resources/static/files";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();
        File saveFile =new File(path,fileName);
        file.transferTo(saveFile);
        board.setFileName(fileName);
        board.setFilePath("/"+path.substring(path.lastIndexOf("/")+1)+"/"+fileName);
        log.info(path);
        log.info(fileName);
        log.info(saveFile);
        log.info(board);
    boardRepository.save(board);
    }
    public Page<Board> getList(Pageable pageable){

        Page<Board> result = boardRepository.findAll(pageable);
        return result;
    }
    public Board get(Integer id){
        Optional<Board> result = boardRepository.findById(id);
        return result.get();
    }
    public void delete(Integer id){
        boardRepository.deleteById(id);
    }
    //검색기능
    public Page<Board> boardSearchList(String searchKeyword,Pageable pageable){
        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }
}
