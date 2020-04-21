package com.mashibing.tank.bean.tank;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.mashibing.tank.TankFrame;
import com.mashibing.tank.bean.bullet.Fire;
import com.mashibing.tank.enums.Direction;
import com.mashibing.tank.enums.Group;
import com.mashibing.tank.resouce.PropertiesMgr;
import com.mashibing.tank.resouce.ResourceMgr;

public class GoodTank extends TankBase {
	static BufferedImage goodTank = ResourceMgr.goodTankU;

	static {
		WIDETH = goodTank.getWidth();
		HIGHER = goodTank.getHeight();
	}

	public GoodTank(int X, int Y, int moveSpeed, TankFrame tankFrame, Direction dir, Group group, boolean moveFlag) {
		super(X, Y, moveSpeed, tankFrame, dir, group, moveFlag);
		rect.height = HIGHER;
		rect.width = WIDETH;
	}

	@Override
	public void paint(Graphics g) {
		kill();
		switch (dir) {
		case UP:
			goodTank = ResourceMgr.goodTankU;
			break;
		case DOWN:
			goodTank = ResourceMgr.goodTankD;
			break;
		case LEFT:
			goodTank = ResourceMgr.goodTankL;
			break;
		case RIGHT:
			goodTank = ResourceMgr.goodTankR;
			break;
		}
		g.drawImage(goodTank, X, Y, WIDETH, HIGHER, null);
		move();
	}

	@Override
	public void move() {
		if (!moveFlag) {
			return;
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
		rect.x = this.X;
		rect.y = this.Y;
		System.out.println(X + "x坦克位置");
		System.out.println(Y + "y坦克位置");
	}

	@Override
	public void fire() {
		String goodFsName = PropertiesMgr.getString("goodFs");
		try {
			fire = (Fire<?>) Class.forName(goodFsName).newInstance();
			fire.fire(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
