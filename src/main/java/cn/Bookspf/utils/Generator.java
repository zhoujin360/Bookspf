package cn.Bookspf.utils;

import cn.Bookspf.model.RO.CaptchaResponse;
import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;



public class Generator {
	
	//生成用户ID
	public static int generateUid() {
		String uid =String.valueOf(System.currentTimeMillis()).substring(1, 8);
		return Integer.parseInt(uid);
	}

	//生成订单ID
	public static Long generateId(){
		String uid =String.valueOf(System.currentTimeMillis());
		return  Long.parseLong(uid);
	}

	//生成当前时间String
	public static String generateTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(new Date().getTime());
	}

	//生成验证码图片JSON
	public static CaptchaResponse generateCaptchaImg() throws IOException {
		char[] chars = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
				'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
				'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		String captcha = genCaptcha(chars);
		BufferedImage image = genCaptchaImg(captcha,chars);
		ByteArrayOutputStream out= new ByteArrayOutputStream();
		ImageIO.write(image,"jpg",out);
		Base64 base64 = new Base64();
		String imgJSON = base64.encodeToString(out.toByteArray());
		return new CaptchaResponse(captcha,imgJSON);
	}

	//生成4位随机数
	public static String genCaptcha(char[] chars) {
		StringBuilder captcha = new StringBuilder();

		for(int i=0; i<4; i++){
			char c = chars[ThreadLocalRandom.current().nextInt(chars.length)];//随机选取一个字母或数字
			captcha.append(c);
		}
		return captcha.toString();
	}


	//生成图片
	public static BufferedImage genCaptchaImg(String captcha,char[] chars){
		ThreadLocalRandom r = ThreadLocalRandom.current();

		int count=captcha.length();
		int fontSize = 80;//字体大小
		int fontMargin=fontSize/4;//字体间隔
		int width=(fontSize+fontMargin)*count+fontMargin;//图片宽度
		int height= (int) (fontSize*1.2);//图片高度
		int avgWidth=width/count;
		int maxDegree=26;

		//背景颜色
		Color bkColor = Color.WHITE;
		//验证码颜色
		Color[] captchaColor={Color.MAGENTA, Color.BLACK, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE, Color.PINK};

		BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics2D g=image.createGraphics();

		//填充底色灰白
		g.setColor(bkColor);
		g.fillRect(0,0,width,height);

		//画边框
		g.setColor(Color.BLACK);
		g.drawRect(0,0,width-1,height-1);

		//画干扰字母,数字
		int dSize=fontSize/3;//调整分母大小以调整干扰字符大小
		Font font = new Font("Fixedsys",Font.PLAIN,dSize);
		g.setFont(font);
		int dNumber=width*height/dSize/dSize;//根据面积计算干扰字母的个数
		for (int i=0;i<dNumber;i++){
			char d_code = chars[r.nextInt(chars.length)];
			g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
			g.drawString(String.valueOf(d_code),r.nextInt(width),r.nextInt(height));
		}


		//画验证码

		//创建字体
		font = new Font(Font.MONOSPACED,Font.ITALIC|Font.BOLD, fontSize);
		//设置字体
		g.setFont(font);

		for(int i=0;i<count;i++){
			char c=captcha.charAt(i);

			//随机转角度
			int degree=r.nextInt(-maxDegree,maxDegree);

			//偏移系数,和旋转角度成反比,以避免字符在图片中越出边框
			double offsetFactor=1-(Math.abs(degree)/(maxDegree+1.0));//加1避免0

			g.rotate(degree*Math.PI/180);//旋转角度
			int x=(int) (fontMargin+r.nextInt(avgWidth-fontSize)*offsetFactor);//横向偏移的距离
			int y = (int) (fontSize + r.nextInt(height-fontSize)*offsetFactor); //上下偏移的距离

			g.drawString(String.valueOf(c),x,y);//x,y是字符的左下角，偏离原点的距离

			g.rotate(-degree*Math.PI/180);//画完一个字符之后，旋转回原来的角度
			g.translate(avgWidth,0);//移动到下一个画画的原点

		}
		g.dispose();
		return image;
	}
}
