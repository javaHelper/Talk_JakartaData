package com.thorben.janssen.repository;

import java.util.List;

import jakarta.data.repository.BasicRepository;
import jakarta.data.repository.Repository;
import org.hibernate.annotations.processing.Find;

import com.thorben.janssen.model.ChessGame;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Query;

//@Repository
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
