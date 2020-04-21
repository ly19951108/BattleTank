package com.mashibing.tank.bean.bullet.missile;

import com.mashibing.tank.bean.bullet.Fire;
import com.mashibing.tank.bean.tank.TankBase;
import com.mashibing.tank.enums.Direction;
import com.mashibing.tank.enums.Group;
import com.mashibing.tank.util.AudioUtil;

public class MissileFourBulletFile implements Fire<MissileBullet> {
//	private static MissileFourBulletFile INSTANCE = null;
//
//	private MissileFourBulletFile() {
//	}
//
//	public static MissileFourBulletFile getInstance() {
//		if (INSTANCE == null) {
//			synchronized (MissileFourBulletFile.class) {
//				if (INSTANCE == null) {
//					INSTANCE = new MissileFourBulletFile();
//				}
//			}
//		}
//		return INSTANCE;
//	}

	@Override
	public void fire(TankBase tank) {
		Direction[] values = Direction.values();
		for (int i = 0; i < values.length; i++) {
			new MissileBullet(tank.getX() + tank.WIDETH / 2 - MissileBullet.WIDETH / 2,
					tank.getY() + tank.HIGHER / 2 - MissileBullet.HIGHER / 2, 10, tank.tankFrame, values[i],
					tank.group);
		}
		if (tank.group == Group.GOOD) {
			new Thread(() -> new AudioUtil("audio/tank_fire.wav").play()).start();
		}
	}

}
