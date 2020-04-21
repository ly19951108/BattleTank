package com.mashibing.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import com.mashibing.tank.bean.bullet.BulletBase;
import com.mashibing.tank.bean.explode.Explode;
import com.mashibing.tank.bean.tank.GoodTank;
import com.mashibing.tank.bean.tank.TankBase;
import com.mashibing.tank.enums.Direction;
import com.mashibing.tank.enums.Group;

public class TankFrame extends Frame {
	public static final int tankInitSiteX = 0;
	public static final int tankInitSiteY = 30;
	public final int WIDETH = 800;
	public final int HIGHER = 600;
	private static final long serialVersionUID = 1L;
	GoodTank tank = new GoodTank(tankInitSiteX, tankInitSiteY, 2, this, Direction.UP, Group.GOOD, false);
	public List<BulletBase> bulletList = new ArrayList<BulletBase>();
	public List<TankBase> tankList = new ArrayList<TankBase>();
	public List<Explode> explodeList = new ArrayList<Explode>();

	public TankFrame() {
		setSize(WIDETH, HIGHER);// 设置窗口尺寸
		setVisible(true);// 设置可见
		setResizable(false);// 设置窗口尺寸不可变化
		setTitle("̹坦克大战v1.0");// 设置标题
		this.addKeyListener(new MyKeyListener());
		addWindowListener(new WindowAdapter() {// 窗口可关闭
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	Image offScreenImage = null;

	@Override
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(WIDETH, HIGHER);
		}
		Graphics graphics = offScreenImage.getGraphics();
		Color color = graphics.getColor();
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, WIDETH, HIGHER);
		graphics.setColor(color);
		paint(graphics);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	// param g:画笔
	@Override
	public void paint(Graphics g) {
		Color color = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("子弹的数量:" + bulletList.size(), 700, 50);
		g.drawString("坦克的数量:" + tankList.size(), 700, 100);
		g.drawString("爆炸的数量:" + explodeList.size(), 700, 150);
		g.setColor(color);
		tank.paint(g);
		for (int i = 0; i < bulletList.size(); i++) {
			bulletList.get(i).paint(g);
		}
		for (int i = 0; i < tankList.size(); i++) {
			tankList.get(i).paint(g);
		}
		for (int i = 0; i < explodeList.size(); i++) {
			explodeList.get(i).paint(g);
		}
		// 碰撞检测
		for (int i = 0; i < bulletList.size(); i++) {
			for (int j = 0; j < tankList.size(); j++) {
				bulletList.get(i).collideWith(tankList.get(j));
			}
		}
		for (int i = 0; i < bulletList.size(); i++) {
			bulletList.get(i).collideWith(tank);
		}
	}

	class MyKeyListener extends KeyAdapter {
		boolean bl = false;
		boolean br = false;
		boolean bu = false;
		boolean bd = false;

		// 键盘按下事件
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			if (keyCode == KeyEvent.VK_UP) {
				bu = true;
			}
			if (keyCode == KeyEvent.VK_DOWN) {
				bd = true;
			}
			if (keyCode == KeyEvent.VK_RIGHT) {
				br = true;
			}
			if (keyCode == KeyEvent.VK_LEFT) {
				bl = true;
			}
			setTankMove();
		}

		// 键盘松开事件
		@Override
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode();
			if (keyCode == KeyEvent.VK_LEFT) {
				bl = false;
			}
			if (keyCode == KeyEvent.VK_UP) {
				bu = false;
			}
			if (keyCode == KeyEvent.VK_RIGHT) {
				br = false;
			}
			if (keyCode == KeyEvent.VK_DOWN) {
				bd = false;
			}
			if (keyCode == KeyEvent.VK_CONTROL) {
				tank.fire();
			}
			setTankMove();
		}

		private void setTankMove() {
			if (!bd && !br && !bu && !bl) {
				tank.setMoveFlag(false);
			} else {
				tank.setMoveFlag(true);
			}
			if (bl) {
				tank.setDir(Direction.LEFT);
				if (bu) {
					tank.setDir(Direction.UP);
					return;
				}
				if (bd) {
					tank.setDir(Direction.DOWN);
					return;
				}
			}
			if (br) {
				tank.setDir(Direction.RIGHT);
				if (bu) {
					tank.setDir(Direction.UP);
					return;
				}
				if (bd) {
					tank.setDir(Direction.DOWN);
					return;
				}
			}
			if (bu) {
				tank.setDir(Direction.UP);
				if (bl) {
					tank.setDir(Direction.LEFT);
					return;
				}
				if (br) {
					tank.setDir(Direction.RIGHT);
					return;
				}
			}
			if (bd) {
				tank.setDir(Direction.DOWN);
				if (bl) {
					tank.setDir(Direction.LEFT);
					return;
				}
				if (br) {
					tank.setDir(Direction.RIGHT);
					return;
				}
			}
		}

	}
}
