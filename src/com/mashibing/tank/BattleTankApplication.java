package com.mashibing.tank;

import com.mashibing.tank.bean.tank.BadTank;
import com.mashibing.tank.enums.Direction;
import com.mashibing.tank.enums.Group;
import com.mashibing.tank.resouce.PropertiesMgr;
import com.mashibing.tank.util.AudioUtil;

public class BattleTankApplication {
	public static void main(String[] args) {
		TankFrame tankFrame = new TankFrame();
		new Thread(() -> new AudioUtil("audio/war1.wav").loop()).start();
		Integer initTankCount = PropertiesMgr.getInt("initTankCount");
		for (int i = 0; i < initTankCount; i++) {
			tankFrame.tankList.add(new BadTank(i * 80, 200, 5, tankFrame, Direction.DOWN, Group.BAD, true));
		}
		while (true) {
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			tankFrame.repaint();
		}
	}

}
