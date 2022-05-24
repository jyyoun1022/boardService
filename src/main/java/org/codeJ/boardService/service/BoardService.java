package org.codeJ.boardService.service;

import lombok.RequiredArgsConstructor;
import org.codeJ.boardService.entity.Board;
import org.codeJ.boardService.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
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
    boardRepository.save(board);
    }
    public List<Board> getList(){
        List<Board> result = boardRepository.findAll();
        return result;
    }
    public Board get(Integer id){
        Optional<Board> result = boardRepository.findById(id);
        return result.get();
    }
    public void delete(Integer id){
        boardRepository.deleteById(id);
    }
}
