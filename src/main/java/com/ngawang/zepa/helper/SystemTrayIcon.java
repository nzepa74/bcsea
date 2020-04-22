package com.ngawang.zepa.helper;

import javax.swing.*;
import java.awt.*;

public class SystemTrayIcon {
	public static void displayTray(String message){
		if(!SystemTray.isSupported()){
			System.out.println("System Tray not supported");
			return;
		}
		SystemTray tray = SystemTray.getSystemTray();
		ImageIcon icon = null;
		icon = new ImageIcon("/resources/images/logo.png");
		Image image = icon.getImage();
		
		TrayIcon trayIcon = new TrayIcon(image,"BCSEA");
		try{
			tray.add(trayIcon);
		} catch (AWTException e){
			e.printStackTrace();
		}
		trayIcon.displayMessage("BCSEA Service",message,TrayIcon.MessageType.INFO);
		
		try{
			Thread.sleep(10000);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
		tray.remove(trayIcon);
	}
}