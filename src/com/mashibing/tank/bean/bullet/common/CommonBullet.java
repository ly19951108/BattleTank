package com.mashibing.tank.bean.bullet.common;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.mashibing.tank.TankFrame;
import com.mashibing.tank.bean.bullet.BulletBase;
import com.mashibing.tank.enums.Direction;
import com.mashibing.tank.enums.Group;
import com.mashibing.tank.resouce.ResourceMgr;

/**
 * 普通子弹
 * 
 * @author liuyue
 *
 */
public class CommonBullet extends BulletBase {
	public static int WIDETH;
	public static int HIGHER;
	static BufferedImage commonBullet = ResourceMgr.commonBulletU;

	public CommonBullet(int X, int Y, int moveSpeed, TankFrame tankFrame, Direction dir, Group group) {
		super(X, Y, moveSpeed, tankFrame, dir, group);
		HIGHER = commonBullet.getHeight();
		WIDETH = commonBullet.getWidth();
		rect.height = HIGHER;
		rect.width = WIDETH;
		tankFrame.bulletList.add(this);
	}

	@Override
	public void paint(Graphics g) {
		kill();
		switch (dir) {
		case UP:
			commonBullet = ResourceMgr.commonBulletU;
			break;
		case DOWN:
			commonBullet = ResourceMgr.commonBulletD;
			break;
		case LEFT:
			commonBullet = ResourceMgr.commonBulletL;
			break;
		case RIGHT:
			commonBullet = ResourceMgr.commonBulletR;
			break;
		}
		g.drawImage(commonBullet, X, Y, CommonBullet.WIDETH, CommonBullet.HIGHER, null);
		move();
	}
	
}
