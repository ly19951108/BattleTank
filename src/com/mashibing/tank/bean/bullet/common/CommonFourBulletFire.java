package com.mashibing.tank.bean.bullet.common;

import com.mashibing.tank.bean.bullet.Fire;
import com.mashibing.tank.bean.tank.TankBase;
import com.mashibing.tank.enums.Direction;
import com.mashibing.tank.enums.Group;
import com.mashibing.tank.util.AudioUtil;

public class CommonFourBulletFire implements Fire<CommonBullet> {
//	private static CommonFourBulletFire INSTANCE = null;
//
//	private CommonFourBulletFire() {
//	}
//
//	public static CommonFourBulletFire getInstance() {
//		if (INSTANCE == null) {
//			synchronized (CommonFourBulletFire.class) {
//				if (INSTANCE == null) {
//					INSTANCE = new CommonFourBulletFire();
//				}
//			}
//		}
//		return INSTANCE;
//	}

	@Override
	public void fire(TankBase tank) {
		Direction[] values = Direction.values();
		for (int i = 0; i < values.length; i++) {
			new CommonBullet(tank.getX() + tank.WIDETH / 2 - CommonBullet.WIDETH / 2,
					tank.getY() + tank.HIGHER / 2 - CommonBullet.HIGHER / 2, 10, tank.tankFrame, values[i], tank.group);
		}
		if (tank.group == Group.GOOD) {
			new Thread(() -> new AudioUtil("audio/tank_fire.wav").play()).start();
		}
	}

}
