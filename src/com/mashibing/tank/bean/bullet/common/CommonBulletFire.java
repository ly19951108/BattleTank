package com.mashibing.tank.bean.bullet.common;

import com.mashibing.tank.bean.bullet.Fire;
import com.mashibing.tank.bean.tank.TankBase;
import com.mashibing.tank.enums.Group;
import com.mashibing.tank.util.AudioUtil;

public class CommonBulletFire implements Fire<CommonBullet> {
//	private static CommonBulletFire INSTANCE = null;
//
//	private CommonBulletFire() {
//	}
//
//	public static CommonBulletFire getInstance() {
//		if (INSTANCE == null) {
//			synchronized (CommonBulletFire.class) {
//				if (INSTANCE == null) {
//					INSTANCE = new CommonBulletFire();
//				}
//			}
//		}
//		return INSTANCE;
//	}

	@Override
	public void fire(TankBase tank) {
		new CommonBullet(tank.getX() + TankBase.WIDETH / 2 - CommonBullet.WIDETH / 2,
				tank.getY() + TankBase.HIGHER / 2 - CommonBullet.HIGHER / 2, 10, tank.tankFrame, tank.dir, tank.group);
		if (tank.group == Group.GOOD) {
			new Thread(() -> new AudioUtil("audio/tank_fire.wav").play()).start();
		}
	}

}
