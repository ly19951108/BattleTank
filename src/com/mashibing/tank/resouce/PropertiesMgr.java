package com.mashibing.tank.resouce;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesMgr {
	static Properties p = new Properties();
	private static volatile PropertiesMgr mgr;

	private PropertiesMgr() {
	}

	static {
		try {
			p.load(new FileInputStream(new File("config/config.properties")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static PropertiesMgr getInstance() {
		// 解决线程不安全 ,最完美写法之一
		if (mgr == null) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (PropertiesMgr.class) {
				if (mgr == null) {
					mgr = new PropertiesMgr();
				}
			}
		}
		return mgr;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new Thread(() -> System.out.println(PropertiesMgr.getInstance().hashCode())).start();
		}
	}

	public static String getString(String key) {
		Object obj = get(key);
		if (obj == null) {
			return null;
		}
		return String.valueOf(obj);
	}

	public static Integer getInt(String key) {
		Object obj = get(key);
		if (obj == null) {
			return null;
		}
		return Integer.valueOf(obj.toString());
	}

	public static Object get(String key) {
		return p.get(key);

	}
}
