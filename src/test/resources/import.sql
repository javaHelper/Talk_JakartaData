INSERT INTO ChessGame(id, round, playerWhite, playerBlack, version) VALUES (nextval('ChessGame_SEQ'), 1, 'Thorben Janssen', 'A player', 0);
INSERT INTO ChessGame(id, round, playerWhite, playerBlack, version) VALUES (nextval('ChessGame_SEQ'), 2, 'Thorben Janssen', 'A player', 0);


INSERT INTO ChessMove(id, moveNumber, color, move, game_id, version) VALUES(nextval('ChessMove_SEQ'), 1, 0, 'e4', 1, 0);

INSERT INTO ChessMove(id, moveNumber, color, move, game_id, version) VALUES(nextval('ChessMove_SEQ'), 1, 0, 'c4', 51, 0);
INSERT INTO ChessMove(id, moveNumber, color, move, game_id, version) VALUES(nextval('ChessMove_SEQ'), 1, 1, 'e5', 51, 0);
INSERT INTO ChessMove(id, moveNumber, color, move, game_id, version) VALUES(nextval('ChessMove_SEQ'), 2, 0, 'e4', 51, 0);
