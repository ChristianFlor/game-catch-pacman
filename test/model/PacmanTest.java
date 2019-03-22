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
		
		double radius=30;
		double posX=0;
		double posY=0;
		char direction='v';
		int subDirection=1;
		long wait=20;
		int bounces=1;
		boolean moving=true;
		double limitX=500;
		double limitY=500;
		
		
		pm = new Pacman(radius,posX,posY,direction, subDirection, wait,bounces,moving,limitX,limitY);
		
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
		double radius=50;
		double posX=88;
		double posY=44;
		char direction='h';
		int subDirection=8;
		long wait=50;
		int bounces=4;
		boolean moving=false;
		double limitX=200;
		double limitY=600;
		try {
			pm = new Pacman(radius,posX,posY,direction, subDirection, wait,bounces,moving,limitX,limitY);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		assertNotNull("The Game Couldn't be created, its value is null", pm != null);
		
	}

}
