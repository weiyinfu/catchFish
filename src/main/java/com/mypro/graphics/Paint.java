package com.mypro.graphics;

/*
 * 画笔属性
 */
public interface Paint {
void setTypeface(Object obj); // 设置Paint的字体

void setAntiAlias(boolean tf);

void setFilterBitmap(boolean tf);

void setDither(boolean tf);

void setTextSize(int size);  // 根据不同分辨率设置字体大小

void setColor(int color);
}
