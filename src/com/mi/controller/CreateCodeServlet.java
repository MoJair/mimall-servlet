package com.mi.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/code")
public class CreateCodeServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 0);
		//获取验证码
		String code = getRandomCode();
		//存验证码
		HttpSession session = req.getSession();
		session.setAttribute("validatecode", code);
		//创建图片并返回
		BufferedImage  image = getCodeImage(code,110,28);
		ImageIO.write(image, "JPEG",resp.getOutputStream());
		
	}
	private Color getRandomColor(int start, int bound) {
		Random rd = new Random();
		int r = start +rd.nextInt(bound);
		int g = start +rd.nextInt(bound);
		int b = start +rd.nextInt(bound);
		return  new Color(r,g,b);
	}
	
	private String getRandomCode() {
		Random rd = new Random();
		StringBuffer sbf = new StringBuffer();
		int flag = 0;
		for (int i = 0; i < 4; i++) {
			flag = rd.nextInt(3);
			switch (flag) {
			case 0:
				sbf.append(rd.nextInt(10));
				break;
			case 1:
				sbf.append((char)(rd.nextInt(26)+97));
				break;
			case 2:
				sbf.append((char)(rd.nextInt(26)+65));
				break;
				
			}
		}
		return sbf.toString();
	}
	
	private BufferedImage getCodeImage(String code, int width, int height) {
		BufferedImage image =new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(getRandomColor(225, 30));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Couriew New",Font.ITALIC,20));
		int x1,x2,y1,y2;
		Random rd = new Random();
		for (int i = 0; i < 30; i++) {
			g.setColor(getRandomColor(150,40));
			x1 = rd.nextInt(width);
			y1 = rd.nextInt(height);
			x2 = rd.nextInt(width);
			y2 = rd.nextInt(height);
			g.drawLine(x1, y1, x2, y2);
		}
			char[] codes = code.toCharArray();
			for (int i = 0; i < codes.length; i++) {
				g.setColor(getRandomColor(50, 80));
				g.drawString(String.valueOf(codes[i]), 10+24*i, 20);
			}
			g.dispose();
		return image;
	}

}
