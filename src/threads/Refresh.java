/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Icesi University (Cali - Colombia)
 * TIC Department- Algorithms and programming II
 * Third Lab
 * @Author: Christian Flor christian.flor1@correo.icesi.edu.co> 
 * @Date: 9 March 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package threads;

import ui.GameZone;

public class Refresh extends Thread{
	
	private GameZone zone;
	private boolean stop;
	
	public Refresh(GameZone zone) {
		this.zone = zone;
		this.stop = false;
	}

	@Override
	public void run() {
		long sleepTime = zone.getMinimumWaitTime();
		while(!stop) {
			zone.redraw();
			zone.verifyBounces();
			try {
				sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public GameZone getZone() {
		return zone;
	}

	public void setZone(GameZone zone) {
		this.zone = zone;
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}
}
