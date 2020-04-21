package com.mashibing.tank.bean.bullet.missile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.mashibing.tank.TankFrame;
import com.mashibing.tank.bean.bullet.BulletBase;
import com.mashibing.tank.enums.Direction;
import com.mashibing.tank.enums.Group;
import com.mashibing.tank.resouce.ResourceMgr;

/**
 * 导弹子弹
 * 
 * @author liuyue
 *
 */
public class MissileBullet extends BulletBase {
	public static int WIDETH;
	public static int HIGHER;
	static BufferedImage missileBullet = ResourceMgr.missileBulletU;

	public MissileBullet(int X, int Y, int moveSpeed, TankFrame tankFrame, Direction dir, Group group) {
		super(X, Y, moveSpeed, tankFrame, dir, group);
		HIGHER = missileBullet.getHeight();
		WIDETH = missileBullet.getWidth();
		rect.height = HIGHER;
		rect.width = WIDETH;
		tankFrame.bulletList.add(this);
	}

	@Override
	public void paint(Graphics g) {
		kill();
		BufferedImage missileBullet = ResourceMgr.missileBulletU;
		switch (dir) {
		case UP:
			missileBullet = ResourceMgr.missileBulletU;
			break;
		case DOWN:
			missileBullet = ResourceMgr.missileBulletD;
			break;
		case LEFT:
			missileBullet = ResourceMgr.missileBulletL;
			break;
		case RIGHT:
			missileBullet = ResourceMgr.missileBulletR;
			break;
		}
		g.drawImage(missileBullet, X, Y, WIDETH, HIGHER, null);
		move();
	}
}
