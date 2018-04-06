package com.mypro.main;

import com.mypro.manager.CannonManager;
import com.mypro.manager.GameInitManager;
import com.mypro.manager.LayoutManager;
import com.mypro.model.GamingInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AwtMainComponet {
public static void main(String[] args) throws Exception {
    /**
     * 使窗口居中有两种方法：
     * 1、可以通过toolkit获取屏幕大小从而使得窗口居中
     * 2、可以通过setRelativeTo(null)，相对位置为空
     */
    Toolkit tool = Toolkit.getDefaultToolkit();
    Dimension d = tool.getScreenSize();

    JFrame frame = new JFrame();
    //初始化GamingInfo
    GamingInfo.getGamingInfo().setGaming(true);
    GamingInfo.getGamingInfo().setScreenWidth(900);
    GamingInfo.getGamingInfo().setScreenHeight(600);

    frame.setSize(GamingInfo.getGamingInfo().getScreenWidth(), GamingInfo.getGamingInfo().getScreenHeight());
    //frame.setUndecorated(true); // 去掉窗口的装饰
    frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);//采用指定的窗口装饰风格
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //使窗口居中的简单方法，直接设置relativeTo为null
    frame.setLocationRelativeTo(null);
    MainSurface pane = new MainSurface();
    GamingInfo.getGamingInfo().setSurface(pane);
    frame.setContentPane(pane);
    //是否使窗口始终在最上方
    //frame.setAlwaysOnTop(true);
    frame.setVisible(true);
    frame.addMouseListener(new MouseListener() {
        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mousePressed(MouseEvent e) {
            //如果游戏还没开始，直接返回
            if (GameInitManager.getGameInitManager().isIniting()) {
                return;
            }
            //屏幕被触摸
            //先看布局管理器是否有相应目标，如果有目标，则发射子弹
            if (!LayoutManager.getLayoutManager().onClick(e.getX(), e.getY())) {
                //发射子弹
                CannonManager.getCannonManager().shot(e.getX(), e.getY());
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub

        }
    });
//		frame.pack();
    pane.action();
    /**
     * 创建一个线程来异步初始化游戏内容
     */
    new Thread(new Runnable() {

        public void run() {
            //使用游戏初始化管理器初始化游戏
            GameInitManager.getGameInitManager().init();
        }

    }).start();
}

}
