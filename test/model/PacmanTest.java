package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

class PacmanTest {
	private Pacman pm;
	
	private void setUpScenary3() {}

	@Test
	public void testPacmanCreated() {
		
		setUpScenary3();
		
		pm = new Pacman(30,0, 0,'v',1,20,1,true,500,500);
		
		assertNotNull("The Object is null", pm != null);
		
		assertTrue("The x coordinate is not 0", pm.getPosX() == 0);
		assertTrue("The y coordinate is not 0", pm.getPosY() == 0);
		assertTrue("The radius is not 30", pm.getRadius() == 30);
		assertTrue("The PacMan is stopped", pm.isMoving() == true);
		assertTrue("The direction movement is not towards up", pm.getDirection() =='v');
	}
	@Test
	public void testPacman(){
		
		setUpScenary3();
		
		try {
			pm = new Pacman(0, 0, 25,'v',1,20,1,true,500,500);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		assertNotNull("The Game Couldn't be created, its value is null", pm != null);
		
	}

}
