package com.mashibing.tank.bean.bullet;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.mashibing.tank.TankFrame;
import com.mashibing.tank.bean.explode.Explode;
import com.mashibing.tank.bean.tank.Tank;
import com.mashibing.tank.enums.Direction;
import com.mashibing.tank.enums.Group;
import com.mashibing.tank.resouce.ResourceMgr;

public class Bullet {
	private int siteX, siteY;// 子弹坐标
	private int moveSpeed = 5;// 移动速度
	private TankFrame tankFrame = null;// 子弹出现的画布
	private Direction dir;// 子弹方向
	private boolean liveing = true;
	public static int WIDETH = ResourceMgr.commonBulletU.getWidth();
	public static int HIGHER = ResourceMgr.commonBulletU.getHeight();
	private Group group;
	Rectangle rect = new Rectangle();

	public Bullet(int siteX, int siteY, int moveSpeed, TankFrame tankFrame, Direction dir, Group group) {
		super();
		this.siteX = siteX;
		this.siteY = siteY;
		if (this.moveSpeed < moveSpeed) {
			this.moveSpeed = moveSpeed;
		}
		this.tankFrame = tankFrame;
		this.dir = dir;
		this.group = group;
		rect.x = this.siteX;
		rect.y = this.siteY;
		rect.height = HIGHER;
		rect.width = WIDETH;
	}

	public void paint(Graphics g) {
		if (!liveing) {
			tankFrame.bulletList.remove(this);
		}
		BufferedImage bullet = ResourceMgr.goodTankU;
		switch (dir) {
		case UP:
			bullet = ResourceMgr.commonBulletU;
			break;
		case DOWN:
			bullet = ResourceMgr.commonBulletD;
			break;
		case LEFT:
			bullet = ResourceMgr.commonBulletL;
			break;
		case RIGHT:
			bullet = ResourceMgr.commonBulletR;
			break;
		}
		g.drawImage(bullet, siteX, siteY, WIDETH, HIGHER, null);
		move();
	}

	private void move() {
		if (dir == Direction.LEFT) {
			this.siteX -= moveSpeed;
		}
		if (dir == Direction.RIGHT) {
			this.siteX += moveSpeed;
		}
		if (dir == Direction.UP) {
			this.siteY -= moveSpeed;
		}
		if (dir == Direction.DOWN) {
			this.siteY += moveSpeed;
		}
		rect.x = this.siteX;
		rect.y = this.siteY;
		if (siteX < 0 || siteY < 0 || siteX > tankFrame.WIDETH || siteY > tankFrame.HIGHER) {
			liveing = false;
		}
	}

	public void collideWith(Tank tank) {
		if (this.group == tank.getGroup()) {
			return;
		}
		if (rect.intersects(tank.rect)) {
			tank.die();
			Explode explode = new Explode(tank.WIDETH / 2 + tank.getSiteX() - Explode.WIDETH / 2,
					tank.HIGHER / 2 + tank.getSiteY() - Explode.HIGHER / 2, this.tankFrame);
			tankFrame.explodeList.add(explode);
			die();
		}
	}

	private void die() {
		liveing = false;
	}
}
