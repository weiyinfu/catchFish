package com.mypro.model.componets;

import java.util.HashMap;

import com.mypro.graphics.Bitmap;
import com.mypro.graphics.Canvas;
import com.mypro.graphics.Paint;
import com.mypro.manager.ImageManager;
import com.mypro.model.GamingInfo;
import com.mypro.tools.Log;

/**
 * 金币显示组件
 * @author Xiloerfan
 *
 */
public class BottomGold extends Componet{
	private int gold;//这个值记录当前组件应显示的金币数
	private int[] num_index = new int[1];//所有数字的索引，这里第一个元素代表得分的最大位数，往后类推
	private Bitmap pic;
	private Bitmap[] num;
	private int numShowX,numShowY;//数字显示的X和Y坐标
	private int numPicWidth;	 //数字宽度，所有数字宽度是一样的
	public BottomGold(int layoutX,int layoutY){
		try {
			initNum();
			pic = ImageManager.getImageMnagaer().getscaleImageByScreenFromAssets("componet/bottom_gold.png");
			this.setLayoutX(layoutX);
			this.setLayoutY(layoutY);
			numShowX = layoutX+pic.getWidth()/3;
			numShowY = layoutY+pic.getHeight()/4;
			numPicWidth = num[0].getWidth();
			this.getPicMatrix().setTranslate(this.getLayoutX(),this.getLayoutY());
		} catch (Exception e) {
			Log.doLogForException(e);
		}
	
	}
	/**
	 * 初始化显示的数字
	 */
	private void initNum(){
		HashMap<String,Bitmap> allNum = ImageManager.getImageMnagaer().getImagesMapByImageConfig(ImageManager.getImageMnagaer().createImageConfigByPlist("componet/num_gold"),ImageManager.getImageMnagaer().scaleNum);
		//效果图全名(num_0.png)
		StringBuffer numFullName = new StringBuffer();
		String numName = "num_";
		num = new Bitmap[10];
		for(int num = 0;num<10&&GamingInfo.getGamingInfo().isGaming();num++){
			numFullName.delete(0, numFullName.length());
			numFullName.append(numName+num+".png");
			this.num[num] =  allNum.get(numFullName.toString());
		}
	}
	@Override
	public void onDraw(Canvas canvas, Paint paint) {
		super.onDraw(canvas, paint);
		if(GamingInfo.getGamingInfo().getScore()!=gold){
			gold = GamingInfo.getGamingInfo().getScore();
			updateNumIndex();
		}
		for(int i=0;i<num_index.length;i++){
			canvas.drawBitmap(num[num_index[i]], numShowX+(i*numPicWidth), numShowY, paint);
		}
	}
	/**
	 * 更新数字索引
	 */
	private void updateNumIndex(){
		String num = gold+"";
		num_index = new int[num.length()];
		int index = 0;
		for(char n:num.toCharArray()){
			num_index[index] = n-48;
			index++;
		}		
	}	
	
	public Bitmap getCurrentPic() {
		return pic;
	}

	public int getPicWidth() {
		return pic.getWidth();
	}

	public int getPicHeight() {
		return pic.getHeight();
	}

}
