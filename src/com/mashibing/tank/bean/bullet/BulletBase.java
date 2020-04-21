package com.mashibing.tank.bean.bullet;

import java.awt.Rectangle;

import com.mashibing.tank.TankFrame;
import com.mashibing.tank.bean.base.Base;
import com.mashibing.tank.bean.explode.Explode;
import com.mashibing.tank.bean.tank.TankBase;
import com.mashibing.tank.enums.Direction;
import com.mashibing.tank.enums.Group;

public abstract class BulletBase extends Base {
	public Rectangle rect = new Rectangle();

	public BulletBase(int X, int Y, int moveSpeed, TankFrame tankFrame, Direction dir, Group group) {
		super();
		this.X = X;
		this.Y = Y;
		if (this.moveSpeed < moveSpeed) {
			this.moveSpeed = moveSpeed;
		}
		this.tankFrame = tankFrame;
		this.dir = dir;
		this.group = group;
	}

	public void collideWith(TankBase tank) {
		if (this.group == tank.group) {
			return;
		}
		if (rect.intersects(tank.rect)) {
			tank.die();
			int explodeX = TankBase.WIDETH / 2 + tank.getX() - Explode.WIDETH / 2;
			int explodeY = TankBase.HIGHER / 2 + tank.getY() - Explode.HIGHER / 2;
			Explode explode = new Explode(explodeX, explodeY, this.tankFrame);
			tankFrame.explodeList.add(explode);
			die();
		}
	}

	public void kill() {
		if (!liveing) {
			tankFrame.bulletList.remove(this);
		}
	}

	@Override
	public void move() {
		if (dir == Direction.LEFT) {
			this.X -= moveSpeed;
		}
		if (dir == Direction.RIGHT) {
			this.X += moveSpeed;
		}
		if (dir == Direction.UP) {
			this.Y -= moveSpeed;
		}
		if (dir == Direction.DOWN) {
			this.Y += moveSpeed;
		}
		this.rect.x = this.X;
		this.rect.y = this.Y;
		System.out.println(X + "x子弹位置");
		System.out.println(Y + "y子弹位置");
		borderDie();
	}

	private void borderDie() {
		if (X < 0 || Y < 0 || X > tankFrame.WIDETH || Y > tankFrame.HIGHER) {
			liveing = false;
		}
	}

	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}

}
