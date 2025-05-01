package com.thorben.janssen.repository;

import java.util.List;

import jakarta.data.repository.*;

import com.thorben.janssen.model.ChessGame;

@Repository
public interface ChessGameRepositoryCrud
//            extends BasicRepository<ChessGame, Long> {
        extends CrudRepository<ChessGame, Long> {

    @Query("SELECT g FROM ChessGame g WHERE g.playerWhite = :player OR g.playerBlack = :player")
    // @HQL("SELECT g FROM ChessGame g WHERE g.playerWhite = :player OR g.playerBlack = :player")
    // @SQL("SELECT * FROM ChessGame g WHERE g.playerWhite = :player OR g.playerBlack = :player")
    List<ChessGame> findGamesByPlayer(String player);

    @Find
    List<ChessGame> generatedFind(String playerWhite);

}
