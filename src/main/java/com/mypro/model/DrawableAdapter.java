package com.mypro.model;

import com.mypro.graphics.Canvas;
import com.mypro.graphics.Matrix;
import com.mypro.graphics.Paint;
import com.mypro.graphics.JMatrix;
import com.mypro.model.interfaces.Drawable;

public abstract class DrawableAdapter implements Drawable{
	private Matrix matrix = new JMatrix();
	
	public Matrix getPicMatrix() {
		// TODO Auto-generated method stub
		return matrix;
	}

	public void onDraw(Canvas canvas, Paint paint) {
		canvas.drawBitmap(this.getCurrentPic(),
				this.getPicMatrix(), paint);		
	}

	
}
