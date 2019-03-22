package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

class ScoreTest {
	private Score score;
	
	private void setUpScenary2() {}
	@Test
	public void testScore(){
		
		setUpScenary2();
		try {
			score = new Score("julio",10);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		assertNotNull("The Game Couldn't be created, its value is null", score != null);
		
	}
	@Test
	public void testScoreCreated() {
		
		setUpScenary2();
		
		score = new Score("andres",8);
		
		assertNotNull("The Object is null", score != null);
		
		assertTrue("The name is not andres", score.getName().equals("andres"));
		assertTrue("The score is equals 0", score.getScore() == 8);
		;
	}

}
