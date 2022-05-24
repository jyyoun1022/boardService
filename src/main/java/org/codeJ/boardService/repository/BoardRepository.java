package org.codeJ.boardService.repository;

import org.codeJ.boardService.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BoardRepository extends JpaRepository<Board,Integer>{

}