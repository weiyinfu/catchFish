package com.mypro.model.interfaces;

/**
 * 按钮的接口
 *
 * @author Xiloerfan
 */
public interface Button extends Drawable {
/**
 * 是否可用
 *
 * @return
 */
boolean isEnable();

/**
 * 当按钮被点击
 */
void onClick();
}
