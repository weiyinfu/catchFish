package com.mypro.model.componets;

import com.mypro.graphics.Canvas;
import com.mypro.graphics.Paint;
import com.mypro.model.DrawableAdapter;
/**
 * 组件的父类
 * @author Xiloerfan
 *
 */
public abstract class Componet extends DrawableAdapter{
	/**
	 * 组件所在屏幕的x,y坐标
	 */
	private float layoutX;
	private float layoutY;
	
	/**
	 * 获取组件在屏幕的X坐标
	 * @return
	 */
	public float getLayoutX() {
		return layoutX;
	}
	/**
	 * 设置组件在屏幕的X坐标
	 * @param layoutX
	 */
	public void setLayoutX(float layoutX) {
		this.layoutX = layoutX;
	}
	/**
	 * 获取组件在屏幕的Y坐标
	 * @return
	 */
	public float getLayoutY() {
		return layoutY;
	}
	/**
	 * 设置组件在屏幕的Y坐标
	 * @param layoutY
	 */
	public void setLayoutY(float layoutY) {
		this.layoutY = layoutY;
	}
	
	public void onDraw(Canvas canvas, Paint paint) {
		canvas.drawBitmap(this.getCurrentPic(),
				layoutX, layoutY, paint);
	}
}
