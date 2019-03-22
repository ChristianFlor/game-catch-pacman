package model;

import static org.junit.Assert.assertNotNull;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class GameTest {
	private Game game;
	private void setupScenary1() {}

	@Test
	public void testGame(){
		
		setupScenary1();
		int level =0;
		double width=400;
		double height=500;
		
		try {
			game = new Game(level, width, height);
		} catch (IllegalArgumentException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		assertNotNull("The Game Couldn't be created, its value is null", game != null);
		
	}

}
