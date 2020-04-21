package com.mashibing.tank.resouce;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mashibing.tank.util.ImageUtil;

public class ResourceMgr {
	public static BufferedImage goodTankL, goodTankU, goodTankR, goodTankD;
	public static BufferedImage badTankU, badTankL, badTankR, badTankD;
	public static BufferedImage missileBulletU, missileBulletD, missileBulletL, missileBulletR;
	public static BufferedImage commonBulletU, commonBulletL, commonBulletR, commonBulletD;
	public static BufferedImage[] explodes = new BufferedImage[16];
	private static ResourceMgr INSTANCE = new ResourceMgr();

	private ResourceMgr() {

	}

	public ResourceMgr getInstance() {
		return INSTANCE;
	}

	static {
		try {
			/**
			 * 蓝方坦克
			 */
			goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/goodTank1.png"));
			goodTankL = ImageUtil.rotateImage(goodTankU, -90);
			goodTankR = ImageUtil.rotateImage(goodTankU, 90);
			goodTankD = ImageUtil.rotateImage(goodTankU, 180);
			/**
			 * 红方坦克
			 */
			badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
			badTankL = ImageUtil.rotateImage(badTankU, -90);
			badTankR = ImageUtil.rotateImage(badTankU, 90);
			badTankD = ImageUtil.rotateImage(badTankU, 180);
			/**
			 * 普通子弹
			 */
			commonBulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
			commonBulletL = ImageUtil.rotateImage(commonBulletU, -90);
			commonBulletR = ImageUtil.rotateImage(commonBulletU, 90);
			commonBulletD = ImageUtil.rotateImage(commonBulletU, 180);
			/**
			 * 导弹
			 */
			missileBulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
			missileBulletL = ImageUtil.rotateImage(missileBulletU, -90);
			missileBulletR = ImageUtil.rotateImage(missileBulletU, 90);
			missileBulletD = ImageUtil.rotateImage(missileBulletU, 180);
			/**
			 * 爆炸画面
			 */
			for (int i = 0; i < explodes.length; i++) {
				explodes[i] = ImageIO
						.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
