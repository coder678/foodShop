package com.school.foodShop.util;

public class PathUtil {
	private static String seperator = System.getProperty("file.separator");  //1.获取系统的分隔符 win的分隔符是/
                                                                            //其他的系统分隔符是\
	public static String getImgBasePath() {
		String os = System.getProperty("os.name");
		String basePath = "";
		if (os.toLowerCase().startsWith("win")) {   //2.设置win系统和其他系统的根路径
			basePath = "D:/projectdev/image";
		} else {
			basePath = "/Users/baidu/work/image";
		}
		basePath = basePath.replace("/", seperator);
		return basePath;
	}

	public static String getShopImagePath(long shopId) {
		String imagePath = "/upload/images/item/shop/" + shopId + "/";    //设置店铺图片的位置
		return imagePath.replace("/", seperator);
	}
}
