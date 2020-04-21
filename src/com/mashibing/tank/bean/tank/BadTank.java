package com.mashibing.tank.bean.tank;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.mashibing.tank.TankFrame;
import com.mashibing.tank.bean.bullet.Fire;
import com.mashibing.tank.bean.bullet.missile.MissileBulletFire;
import com.mashibing.tank.enums.Direction;
import com.mashibing.tank.enums.Group;
import com.mashibing.tank.resouce.PropertiesMgr;
import com.mashibing.tank.resouce.ResourceMgr;

public class BadTank extends TankBase {
	private Fire<?> fire = null;
	static BufferedImage badTank = ResourceMgr.badTankU;
	static {
		WIDETH = badTank.getWidth();
		HIGHER = badTank.getHeight();
	}

	public BadTank(int X, int Y, int moveSpeed, TankFrame tankFrame, Direction dir, Group group, boolean moveFlag) {
		super(X, Y, moveSpeed, tankFrame, dir, group, moveFlag);
		rect.height = HIGHER;
		rect.width = WIDETH;
	}

	@Override
	public void paint(Graphics g) {
		kill();
		switch (dir) {
		case UP:
			badTank = ResourceMgr.badTankU;
			break;
		case DOWN:
			badTank = ResourceMgr.badTankD;
			break;
		case LEFT:
			badTank = ResourceMgr.badTankL;
			break;
		case RIGHT:
			badTank = ResourceMgr.badTankR;
			break;
		}
		g.drawImage(badTank, X, Y, WIDETH, HIGHER, null);
		move();
	}

	@Override
	public void move() {
		if (!moveFlag) {
			return;
		}
		if (r.nextInt(100) > 95) {
			rondomDir();
		}
		if (dir == Direction.LEFT && this.X > leftBorder) {
			this.X -= moveSpeed;
			return;
		}
		if (dir == Direction.RIGHT && this.X < rightBorder) {
			this.X += moveSpeed;
			return;
		}
		if (dir == Direction.UP && this.Y > upBorder) {
			this.Y -= moveSpeed;
			return;
		}
		if (dir == Direction.DOWN && this.Y < downBorder) {
			this.Y += moveSpeed;
		}
		if (r.nextInt(100) > 95) {
			fire();
		}
		rect.x = this.X;
		rect.y = this.Y;
	}

	@Override
	public void fire() {
		String badFsName = PropertiesMgr.getString("badFs");
		try {
			fire = (Fire<?>) Class.forName(badFsName).newInstance();
			fire.fire(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
