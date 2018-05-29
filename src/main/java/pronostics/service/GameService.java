package pronostics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pronostics.model.Game;
import pronostics.repository.GameRepository;

@Service
public class GameService {

	@Autowired
	GameRepository gameRepo;
	
	public void process() {
		System.out.println("Des trucs");
		
		List<Game> games = gameRepo.findAll();
		
		for(Game game : games) {
			System.out.println(game.toString());
		}
	}
}
