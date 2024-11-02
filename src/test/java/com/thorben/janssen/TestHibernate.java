package com.thorben.janssen;

import java.util.List;

import com.thorben.janssen.model.ChessMove;
import com.thorben.janssen.model._ChessGame;
import com.thorben.janssen.model._ChessMove;
import com.thorben.janssen.repository.ChessMoveRepository;
import com.thorben.janssen.repository.ChessMoveRepository_;
import jakarta.data.Limit;
import jakarta.data.Sort;
import jakarta.data.page.Page;
import jakarta.data.page.PageRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thorben.janssen.model.ChessGame;
import com.thorben.janssen.repository.ChessGameRepositoryCustom;
import com.thorben.janssen.repository.ChessGameRepositoryCustom_;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestHibernate {

	Logger log = LogManager.getLogger(this.getClass().getName());

	private EntityManagerFactory emf;
	private ChessGameRepositoryCustom chessGameCustomRepo;
	private ChessMoveRepository chessMoveRepo;

	private Long gameId;

	private StatelessSession statelessSession;

	
	@Test
	public void persistCustomRepo() {
		log.info("... persistCustomRepo ...");

		ChessGame game = new ChessGame();
		game.setPlayerWhite("Thorben Janssen");
		game.setPlayerBlack("Another Player");

		chessGameCustomRepo.insert(game);
	}

	@Test
	public void persistAndUpdate() {
		log.info("... repositoryCustomerQuery ...");

		ChessGame game = new ChessGame();
		game.setPlayerWhite("Thorben Janssen");
		game.setPlayerBlack("Another Player");
		
		chessGameCustomRepo.insert(game);
		
		game.setPlayerBlack("Someone Else");
		
		chessGameCustomRepo.update(game);
	}

	@Test
	public void save() {
		log.info("... save ...");

		ChessGame game = new ChessGame();
		game.setPlayerWhite("Thorben Janssen");
		game.setPlayerBlack("Another Player");

		chessGameCustomRepo.save(game);

		game.setPlayerBlack("Someone Else");

		chessGameCustomRepo.save(game);
	}

	@Test
	public void customUpdate() {
		log.info("... customUpdate ...");

		ChessGame game = new ChessGame();
		game.setPlayerWhite("Thorben Janssen");
		game.setPlayerBlack("Another Player");

		chessGameCustomRepo.save(game);

		chessGameCustomRepo.updatePlayerBlack(game.getId(),"Someone Else", game.getVersion());
	}

	@Test
	public void customDelete() {
		log.info("... customDelete ...");

		ChessGame game = new ChessGame();
		game.setPlayerWhite("Thorben Janssen");
		game.setPlayerBlack("Another Player");

		chessGameCustomRepo.save(game);

//		chessGameCustomRepo.delete(game);
		chessGameCustomRepo.delete(game.getId(), game.getVersion());
	}

	@Test
	public void findSimple() {
		log.info("... simpleFind ...");

		// Find game
		List<ChessMove> moves = chessMoveRepo.findByNumber(1);
		moves.forEach(m -> log.info(m.getMove() + " was played on move number " + m.getMoveNumber()));
	}

	@Test
	public void findRelationship() {
		log.info("... findRelationship ...");

		// Find moves
		List<ChessMove> moves = chessMoveRepo.findByPlayerWhite("Thorben Janssen");
		moves.forEach(m -> log.info(m.getMove() + " was played by " + m.getGame().getPlayerWhite() + " on move number " + m.getMoveNumber()));
	}

	// Hibernate
	@Test
	public void findPattern() {
		log.info("... findPattern ...");

		// Find moves
		List<ChessMove> moves = chessMoveRepo.findByMove("e%");
		moves.forEach(m -> log.info(m.getMove() + " was played on move number " + m.getMoveNumber()));
	}

	@Test
	public void findDynamicSorted() {
		log.info("... findDynamicSorted ...");

		// Find moves
		List<ChessMove> moves = chessMoveRepo.findByMoveSorted("e4", Sort.asc(_ChessMove.MOVE_NUMBER));
		moves.forEach(m -> log.info(m.getMove() + " was played on move number " + m.getMoveNumber() + " in game no. " + m.getGame().getId()));
	}

	@Test
	public void findOrdered() {
		log.info("... findOrdered ...");

		// Find moves
		List<ChessMove> moves = chessMoveRepo.findByMoveOrderedByNumber("e4");
		moves.forEach(m -> log.info(m.getMove() + " was played on move number " + m.getMoveNumber() + " in game no. " + m.getGame().getId()));
	}

//	@Test
//	public void findPaged() {
//		log.info("... findPaged ...");
//
//		// Find moves
//		PageRequest<ChessMove> pageRequest = PageRequest.of(ChessMove.class).size(2).page(2).ascIgnoreCase(_ChessMove.MOVE_NUMBER);
//		Page<ChessMove> moves = chessMoveRepo.findByMovePaged("e4", pageRequest);
//		moves.forEach(m -> log.info(m.getMove() + " was played on move number " + m.getMoveNumber() + " in game no. " + m.getGame().getId()));
//	}

	@Test
	public void findLimit() {
		log.info("... findLimit ...");

		// Find moves
		List<ChessMove> moves = chessMoveRepo.findByMoveLimit("e4", new Limit(2, 1));
		moves.forEach(m -> log.info(m.getMove() + " was played on move number " + m.getMoveNumber() + " in game no. " + m.getGame().getId()));
	}

	@Test
	public void customQuery() {
		log.info("... customQuery ...");

		// Find game
		List<ChessGame> games = chessGameCustomRepo.findGamesByPlayers("Thorben Janssen");
		games.forEach(g -> log.info(g.getPlayerWhite() + " - " + g.getPlayerBlack()));
	}

	@Test
	public void customQueryWithRelationship() {
		log.info("... customQueryWithRelationship ...");

		// Find game
//		List<ChessGame> games = chessGameCustomRepo.findGamesByPlayers("Thorben Janssen");
		List<ChessGame> games = chessGameCustomRepo.findGamesWithMovesByPlayer("Thorben Janssen");
		games.forEach(g -> {
//							chessGameCustomRepo.session().fetch(g.getMoves());
							log.info(g.getPlayerWhite() + " - " + g.getPlayerBlack()
								+ " played a game with " + g.getMoves().size() + " moves.");
		});
	}

	@Test
	public void customQuerySorted() {
		log.info("... customQuerySorted ...");

		// Find game
//		List<ChessGame> games = chessGameCustomRepo.findGamesByPlayersSorted("Thorben Janssen", Sort.asc(_ChessGame.ROUND), Sort.asc(_ChessGame.ID));
		List<ChessGame> games = chessGameCustomRepo.findGamesByPlayersOrdered("Thorben Janssen");
		games.forEach(g -> log.info(g.getPlayerWhite() + " - " + g.getPlayerBlack()));
	}

//	@Test
//	public void customQueryPaged() {
//		log.info("... customQueryPaged ...");
//
//		// Find game
//		PageRequest<ChessGame> pageRequest = PageRequest.of(ChessGame.class).size(2).page(2).ascIgnoreCase(_ChessGame.ROUND);
//		Page<ChessGame> games = chessGameCustomRepo.findGamesByPlayersPaged("Thorben Janssen", pageRequest);
//		games.forEach(g -> log.info(g.getPlayerWhite() + " - " + g.getPlayerBlack()));
//	}




	private Long prepareTestData() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		ChessGame game = new ChessGame();
		game.setPlayerWhite("Thorben Janssen");
		game.setPlayerBlack("Another player");
		game.setRound(1);
		em.persist(game);
		
		em.getTransaction().commit();
		em.close();

		return game.getId();
	}

	
	@Before
	public void init() {
		emf = Persistence.createEntityManagerFactory("my-persistence-unit");

		statelessSession = emf.unwrap(SessionFactory.class).openStatelessSession();
		statelessSession.beginTransaction();

		chessGameCustomRepo = new ChessGameRepositoryCustom_(statelessSession);
//		chessGameCrudRepo = new ChessGameRepositoryCrud_(statelessSession);
		chessMoveRepo = new ChessMoveRepository_(statelessSession);

		gameId = prepareTestData();
	}

	@After
	public void close() {
		statelessSession.getTransaction().commit();
		emf.close();
	}
}
