package com.mashibing.tank.bean.explode;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.mashibing.tank.TankFrame;
import com.mashibing.tank.resouce.ResourceMgr;
import com.mashibing.tank.util.AudioUtil;

public class Explode {
	private int X, Y;// 爆炸坐标
	private TankFrame tankFrame = null;// 画布
	public static BufferedImage[] explodes = ResourceMgr.explodes;
	public static int WIDETH = explodes[0].getWidth();
	public static int HIGHER = explodes[0].getHeight();
	private int step = 0;

	public Explode(int X, int Y, TankFrame tankFrame) {
		super();
		this.X = X;
		this.Y = Y;
		this.tankFrame = tankFrame;
	}

	public void paint(Graphics g) {
		g.drawImage(explodes[step++], X, Y, WIDETH, HIGHER, null);
		new AudioUtil("audio/explode.wav").start();
		if (step == explodes.length) {
			tankFrame.explodeList.remove(this);
		}
	}

}
