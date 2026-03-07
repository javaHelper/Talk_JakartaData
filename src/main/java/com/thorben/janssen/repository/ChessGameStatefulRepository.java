package com.thorben.janssen.repository;

import com.thorben.janssen.model.ChessGame;
import jakarta.data.repository.DataRepository;
import jakarta.data.repository.Repository;
import jakarta.data.repository.stateful.Merge;
import jakarta.data.repository.stateful.Persist;
import jakarta.data.repository.stateful.Remove;

@Repository
public interface ChessGameStatefulRepository extends DataRepository<ChessGame, Long> {

    @Persist
    ChessGame persistGame(ChessGame game);

    @Remove
    void removeGame(ChessGame game);

    @Merge
    ChessGame mergeGame(ChessGame game);
}
