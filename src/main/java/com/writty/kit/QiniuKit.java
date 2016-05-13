package com.writty.kit;

import java.io.File;
import java.util.Random;

import com.blade.Blade;
import com.qiniu.common.Config;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import blade.kit.StringKit;

public class QiniuKit {

	private static final Random R = new Random();
	private static String ACCESS_KEY = "";
	private static String SECRET_KEY = "";
	private static String BUCKET_NAME = "";
	private static String[] CDN = null;
	private static Auth AUTH = null;

	static {
		
		Config.zone = new Zone("http://up.qiniug.com", "http://up.qiniu.com");
		
		ACCESS_KEY = Blade.me().config().get("qiniu.ACCESS_KEY");
		SECRET_KEY = Blade.me().config().get("qiniu.SECRET_KEY");
		BUCKET_NAME = Blade.me().config().get("qiniu.BUCKET_NAME");
		
		CDN = Blade.me().config().get("qiniu.cdn").split(",");
		
		if(StringKit.isNotBlank(ACCESS_KEY) && StringKit.isNotBlank(SECRET_KEY)){
			AUTH = Auth.create(ACCESS_KEY, SECRET_KEY);
		}
	}

	public static String getUrl(String key) {
		int pos = R.nextInt(CDN.length);
		return CDN[pos] + "/" + key;
	}

	public static boolean upload(File file, String key) {
		
		// 创建上传对象
		UploadManager uploadManager = new UploadManager();
		try {
			// 调用put方法上传
			Response res = uploadManager.put(file, key, getUpToken(key));
			// 打印返回的信息
			System.out.println(res.bodyString());
			return true;
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			System.out.println(r.toString());
			try {
				// 响应的文本信息
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
		}
		return false;
	}

	public static String getUpToken(String key) {
		return AUTH.uploadToken(BUCKET_NAME, key, 3600, new StringMap()
				.put("scope", BUCKET_NAME+":"+key));
//		return AUTH.uploadToken(BUCKET_NAME);
	}

}
