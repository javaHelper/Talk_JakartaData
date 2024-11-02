package com.thorben.janssen.repository;

import java.util.List;

import com.thorben.janssen.model._ChessGame;
import jakarta.data.Sort;
import jakarta.data.page.CursoredPage;
import jakarta.data.page.Page;
import jakarta.data.page.PageRequest;
import jakarta.data.repository.*;

import com.thorben.janssen.model.ChessGame;
import org.hibernate.StatelessSession;

@Repository
public interface ChessGameRepositoryCustom {

    @Insert
    void insert(ChessGame game);

    @Update
    void update(ChessGame game);

    @Update
    void updateAll(List<ChessGame> game);

    @Save
    void save(ChessGame game);

    @Delete
    void delete(ChessGame game);

    @Delete
    void delete(@By(_ChessGame.ID) Long gameId, int version);

    @Query("UPDATE ChessGame g SET g.playerBlack = :playerBlack, version = :version+1 WHERE g.id = :id AND version = :version")
    void updatePlayerBlack(Long id, String playerBlack, int version);

    @Query("SELECT g FROM ChessGame g WHERE g.playerWhite = :player OR g.playerBlack = :player")
    // @HQL("SELECT g FROM ChessGame g WHERE g.playerWhite = :player OR g.playerBlack = :player")
    // @SQL("SELECT * FROM ChessGame g WHERE g.playerWhite = :player OR g.playerBlack = :player")
    List<ChessGame> findGamesByPlayers(String player);

    StatelessSession session();

    @Query("SELECT g FROM ChessGame g LEFT JOIN FETCH g.moves WHERE g.playerWhite = :player OR g.playerBlack = :player")
    List<ChessGame> findGamesWithMovesByPlayer(String player);

    @Query("SELECT g FROM ChessGame g WHERE g.playerWhite = :player OR g.playerBlack = :player")
    List<ChessGame> findGamesByPlayersSorted(String player, Sort<ChessGame>... sort);

    @Query("SELECT g FROM ChessGame g WHERE g.playerWhite = :player OR g.playerBlack = :player")
    @OrderBy(_ChessGame.ROUND)
    List<ChessGame> findGamesByPlayersOrdered(String player);

//    @Query("SELECT g FROM ChessGame g WHERE g.playerWhite = :player OR g.playerBlack = :player")
//    Page<ChessGame> findGamesByPlayersPaged(String player, PageRequest<ChessGame> pageRequest);
}
