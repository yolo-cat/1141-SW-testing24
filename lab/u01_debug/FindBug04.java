/**
 * Author: Sammy Chen, FCU
 */
package fcu.findbugs;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class FindBug04 {
	private List<String> dateList;

	/**
	 * 從 log 中取出日期的資訊，放到一個 list 中。
	 * 時間資訊的格式為 28/Aug/1995:00:00:34
	 */
	void parseLog() {
		String log = "204.249.225.59 --[28/Aug/1995:00:00:34 -0400] "
				+ "\"GET /pub/rmharris/catalogs/dawsocat/intro.html "
				+ "HTTP/1.0\" 200 3542";
		String dateInfo = getDateInfo(log);
		dateList.add(dateInfo);
	}

	/**
	 * 從每一行log中取出時間資訊
	 */
	String getDateInfo(String line) {
		String[] split = line.split(" ");

		String dateInfo = "";
		for (int i = 0; i < split.length; i++) {
			if (split[i].startsWith("[")) {
				dateInfo = split[i];
				break;
			}
		}

		if (dateInfo != null && dateInfo.length() >= 1) {
			dateInfo.substring(1, dateInfo.length());
		}
		return dateInfo;
	}

	/**
	 * 將所有蒐集到的時間資訊印出
	 */
	void showAllDateInfo() {
		for (String dateInfo : dateList) {
			System.out.println(dateInfo);
		}
	}

	public static void main(String[] args) {
		FindBug04 l3 = new FindBug04();
		l3.parseLog();
		l3.showAllDateInfo();
	}
}
