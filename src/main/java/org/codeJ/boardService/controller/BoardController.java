package org.codeJ.boardService.controller;

import lombok.RequiredArgsConstructor;
import org.codeJ.boardService.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.codeJ.boardService.service.BoardService;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    @GetMapping("/write") //localhost:8081/board/write
   public String boardWriteForm(){
       return "boardwrite";
   }
   @PostMapping("/writepro")
   public String boardWritePro(Board board, Model model, MultipartFile file)throws Exception{
       boardService.write(board,file);

        model.addAttribute("message","글작성이 완료되었습니다.");
        model.addAttribute("searchUrl","/board/list");

       return "message";
   }
    @GetMapping("/list")
    public String boardList(Model model,
                            @PageableDefault(page=0,size=10,sort = "id",direction = Sort.Direction.DESC) Pageable pageable,
                            String searchKeyword){

        Page<Board> list = null;

        if(searchKeyword == null){
            list = boardService.getList(pageable);
        }else{
            list = boardService.boardSearchList(searchKeyword, pageable);
        }


        int nowPage = list.getPageable().getPageNumber()+1;
        int startPage = Math.max(nowPage - 4,1);
        int endPage = Math.min(nowPage + 5,list.getTotalPages());

        model.addAttribute("list",list);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startpage",startPage);
        model.addAttribute("endpage",endPage);

        return "boardlist";
    }
    @GetMapping("/get")
    public String get(Integer id,Model model){
        Board board = boardService.get(id);
        model.addAttribute("board",board);
        return "boardview";
    }
    @GetMapping("/delete")
    public String boardDelete(Integer id){
        boardService.delete(id);
        return "redirect:/board/list";
    }
    @GetMapping("/modify/{id}")
    public String modify(@PathVariable("id")Integer id,Model model){
        model.addAttribute("board",boardService.get(id));

        return "boardmodify";
    }
    @PostMapping("/update/{id}")
    public String boardUpdate(@PathVariable("id")Integer id,Board board,Model model,MultipartFile file) throws Exception {

        Board boardTemp = boardService.get(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.write(boardTemp,file);

        model.addAttribute("message","수정이 완료되었습니다.");
        model.addAttribute("searchUrl","/board/list");
        return "message";
    }
}
