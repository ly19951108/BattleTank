package com.mashibing.tank.bean.bullet.missile;

import com.mashibing.tank.bean.bullet.Fire;
import com.mashibing.tank.bean.tank.TankBase;
import com.mashibing.tank.enums.Group;
import com.mashibing.tank.util.AudioUtil;

public class MissileBulletFire implements Fire<MissileBullet> {
//	private static MissileBulletFire INSTANCE = null;
//
//	private MissileBulletFire() {
//	}
//
//	public static MissileBulletFire getInstance() {
//		if (INSTANCE == null) {
//			synchronized (MissileBulletFire.class) {
//				if (INSTANCE == null) {
//					INSTANCE = new MissileBulletFire();
//				}
//			}
//		}
//		return INSTANCE;
//	}

	@Override
	public void fire(TankBase tank) {
		new MissileBullet(tank.getX() + tank.WIDETH / 2 - MissileBullet.WIDETH / 2,
				tank.getY() + tank.HIGHER / 2 - MissileBullet.HIGHER / 2, 10, tank.tankFrame, tank.dir, tank.group);
		if (tank.group == Group.GOOD) {
			new Thread(() -> new AudioUtil("audio/tank_fire.wav").play()).start();
		}
	}
}
