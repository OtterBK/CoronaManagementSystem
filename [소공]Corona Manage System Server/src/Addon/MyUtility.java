package Addon;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class MyUtility {

	public static void printLog(String logMsg) {
		System.out.println(logMsg);
	}
	
	public static ImageIcon resizeImage(ImageIcon baseIcon, int newWidth, int newHeight) {
		Image tmpImage = baseIcon.getImage();
		Image chgImage = tmpImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
		ImageIcon chgIcon = new ImageIcon(chgImage);
		return chgIcon;
	}
	
	public static String lineSpacing(String text, int stand) { //stand 값 기준으로 문자열 개행
		String str = text; //개행 작업을 위해 임시로 str에 text 복사
		if(text.length() > stand) { //문자열의 길이가 1줄 제한문자수를 초과했다면
			List<String> tmpStr = new ArrayList<>(); //개행 작업을 위한 문자열 list 선언
			int i = 0; //문자열 추출 작업을 위한 변수
			for(; i < text.length() - stand; i += stand) { //개행 기호를 붙일 문자열 개수만큼 반복
				int subStart = i; //추출 시작지점
				int subEnd = subStart + stand; //추출 끝지점
				tmpStr.add(text.substring(subStart, subEnd)); //추출한 문자열을 tmpStr에 추가
			}
			tmpStr.add(text.substring(i, text.length())); //남은 문자열 추출
			str = ""; //str 초기화
			for(i = 0; i < tmpStr.size() - 1; i++) //tmpStr에 들어있는 문자열중 마지막 문자열을 제외한 값에 개행 처리
				str += tmpStr.get(i) + "<br>"; //개행 기호 추가
			str += tmpStr.get(i); //마지막 문자열은 추가할 필요없음
		}
		str = str.replaceAll("(\r|\n|\r\n|\n\r)","<br>");//개행기호변환
		return str = "<html><body>" + str + "</body></html>"; //값 반환
	}
	
	//min ~ max 중 값 1개 반환
	public static int getRandom(int min, int max) {
		return (int)(Math.random() * (max - min + 1) + min);
	}
	
}
