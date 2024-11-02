package com.thorben.janssen.repository;

import java.util.List;

import com.thorben.janssen.model.ChessMove;

import com.thorben.janssen.model._ChessMove;
import jakarta.data.Limit;
import jakarta.data.Sort;
import jakarta.data.page.CursoredPage;
import jakarta.data.page.Page;
import jakarta.data.page.PageRequest;
import jakarta.data.repository.By;
import jakarta.data.repository.Find;
import jakarta.data.repository.OrderBy;
import jakarta.data.repository.Repository;
import org.hibernate.annotations.processing.Pattern;

@Repository
public interface ChessMoveRepository {

	@Find
	List<ChessMove> findByNumber(int moveNumber);

	@Find
	List<ChessMove> findByMove(@Pattern String move);

	@Find
	List<ChessMove> findByPlayerWhite(String game_playerWhite);

	@Find
	List<ChessMove> findByMoveSorted(String move, Sort<ChessMove> sort);

	@Find
	@OrderBy(_ChessMove.MOVE_NUMBER)
	List<ChessMove> findByMoveOrderedByNumber(String move);

//	@Find
//	Page<ChessMove> findByMovePaged(String move, PageRequest<ChessMove> pageRequest);

	@Find
	List<ChessMove> findByMoveLimit(String move, Limit limit);
}
