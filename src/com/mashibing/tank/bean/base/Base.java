package com.mashibing.tank.bean.base;

import java.awt.Graphics;

import com.mashibing.tank.TankFrame;
import com.mashibing.tank.enums.Direction;
import com.mashibing.tank.enums.Group;

public abstract class Base {
	protected int X, Y;
	public TankFrame tankFrame;
	protected int moveSpeed = 5;
	protected static int WIDETH;
	protected static int HIGHER;
	public Direction dir;
	protected boolean liveing = true;
	public Group group;

	public abstract void paint(Graphics g);

	public abstract void move();

	public void die() {
		liveing = false;
	}
}
