package com.mashibing.tank.bean.tank;

import java.awt.Rectangle;
import java.util.Random;

import com.mashibing.tank.TankFrame;
import com.mashibing.tank.bean.base.Base;
import com.mashibing.tank.bean.bullet.Fire;
import com.mashibing.tank.enums.Direction;
import com.mashibing.tank.enums.Group;

public abstract class TankBase extends Base {
	public static int WIDETH;
	public static int HIGHER;
	public boolean moveFlag;
	protected static Random r = new Random();
	protected final int upBorder = 30;
	protected int downBorder;
	protected final int leftBorder = 0;
	protected int rightBorder;
	protected Fire<?> fire = null;
	public Rectangle rect = new Rectangle();
	public TankBase(int X, int Y, int moveSpeed, TankFrame tankFrame, Direction dir, Group group, boolean moveFlag) {
		super();
		this.X = X;
		this.Y = Y;
		if (tankFrame.HIGHER % moveSpeed == 0 && tankFrame.WIDETH % moveSpeed == 0) {
			this.moveSpeed = moveSpeed;
		}
		this.tankFrame = tankFrame;
		this.dir = dir;
		this.group = group;
		this.rect.x = this.X;
		this.rect.y = this.X;
		this.rect.height = HIGHER;
		this.rect.width = WIDETH;
		this.moveFlag = moveFlag;
		downBorder = tankFrame.HIGHER - HIGHER;
		rightBorder = tankFrame.WIDETH - WIDETH;
	}

	public abstract void move();

	public abstract void fire();

	protected void kill() {
		if (!liveing) {
			tankFrame.tankList.remove(this);
		}
	}

	protected void rondomDir() {
		this.dir = Direction.values()[r.nextInt(4)];
	}

	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}

	public void setMoveFlag(boolean moveFlag) {
		this.moveFlag = moveFlag;
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}

}
