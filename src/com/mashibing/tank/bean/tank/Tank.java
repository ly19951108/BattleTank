package com.mashibing.tank.bean.tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.mashibing.tank.TankFrame;
import com.mashibing.tank.bean.bullet.Bullet;
import com.mashibing.tank.enums.Direction;
import com.mashibing.tank.enums.Group;
import com.mashibing.tank.resouce.ResourceMgr;

public class Tank {
	private int siteX, siteY;// 坦克坐标
	private int moveSpeed = 2;// 移动速度
	private TankFrame tankFrame = null;// 坦克出现的画布
	private Direction dir;// 坦克方向
	private boolean moveFlag = true;// 是否移动
	public int WIDETH = ResourceMgr.goodTankU.getWidth();
	public int HIGHER = ResourceMgr.goodTankU.getHeight();
	private boolean living = true;
	private Group group;
	private Random r = new Random();
	public Rectangle rect = new Rectangle();

	public Tank(int siteX, int siteY, int moveSpeed, TankFrame tankFrame, Direction dir, Group group) {
		super();
		this.siteX = siteX;
		this.siteY = siteY;
		if (tankFrame.HIGHER % moveSpeed == 0 && tankFrame.WIDETH % moveSpeed == 0) {
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
		if (!living) {
			tankFrame.tankList.remove(this);
		}
		BufferedImage tank = this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU;
		switch (dir) {
		case UP:
			tank = this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU;
			break;
		case DOWN:
			tank = this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD;
			break;
		case LEFT:
			tank = this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL;
			break;
		case RIGHT:
			tank = this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR;
			break;
		}
		g.drawImage(tank, siteX, siteY, WIDETH, HIGHER, null);
		move();
	}

	private void move() {
		if (!moveFlag) {
			return;
		}
		if (this.group == Group.BAD && r.nextInt(100) > 95) {
			rondomDir();
		}
		int upBorder = 30;
		int downBorder = tankFrame.HIGHER - this.HIGHER;
		int leftBorder = 0;
		int rightBorder = tankFrame.WIDETH - this.WIDETH;
		if (dir == Direction.LEFT && this.siteX > leftBorder) {
			this.siteX -= moveSpeed;
			return;
		}
		if (dir == Direction.RIGHT && this.siteX < rightBorder) {
			this.siteX += moveSpeed;
			return;
		}
		if (dir == Direction.UP && this.siteY > upBorder) {
			this.siteY -= moveSpeed;
			return;
		}
		if (dir == Direction.DOWN && this.siteY < downBorder) {
			this.siteY += moveSpeed;
		}
		if (r.nextInt(100) > 95 && this.group == Group.BAD) {
			this.fire();
		}
		rect.x = this.siteX;
		rect.y = this.siteY;
	}

	private void rondomDir() {
		this.dir = Direction.values()[r.nextInt(4)];
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}

	public void setMoveFlag(boolean moveFlag) {
		this.moveFlag = moveFlag;
	}

	public void fire() {
		Bullet bullet = new Bullet(this.WIDETH / 2 + this.siteX - Bullet.WIDETH / 2,
				this.HIGHER / 2 + this.siteY - Bullet.HIGHER / 2, 3, this.tankFrame, this.dir, group);
//		this.tankFrame.bulletList.add(bullet);
	}

	public int getSiteX() {
		return siteX;
	}

	public int getSiteY() {
		return siteY;
	}

	public void die() {
		this.living = false;
	}

	public Group getGroup() {
		return group;
	}

}
