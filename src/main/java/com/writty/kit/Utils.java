package com.writty.kit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.blade.web.http.Request;
import com.writty.ext.Funcs;
import com.writty.ext.markdown.BlockEmitter;
import com.writty.ext.markdown.Configuration;
import com.writty.ext.markdown.Processor;

import blade.kit.StringKit;

/**
 * 工具类
 */
public class Utils {

	/**
	 * 获取ip地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(Request request) {
		if (null == request) {
			return "0.0.0.0";
		}
		String ip = request.header("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.header("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.header("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.address();
		}
		return ip;
	}
	
	public static boolean isEmail(String str){
		if(StringKit.isBlank(str)){
			return false;
		}
		String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 判断用户是否可以注册
	 * @param user_name
	 * @return
	 */
	public static boolean isSignup(String user_name){
		if(StringKit.isNotBlank(user_name)){
			user_name = user_name.toLowerCase();
			if(user_name.indexOf("admin") != -1 ||
					user_name.indexOf("test") != -1 ||
					user_name.indexOf("support") != -1){
				return false;
			}
			return true;
		}
		return false;
	}
	
	public static boolean isLegalName(String str){
		if(StringKit.isNotBlank(str)){
			Pattern pattern = Pattern.compile("^[a-zA-Z_0-9]{4,16}$");
			if(!pattern.matcher(str).find()){
				return false;
			}
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("resource")
	public static void copyFileUsingFileChannels(File source, File dest) throws IOException {    
        FileChannel inputChannel = null;    
        FileChannel outputChannel = null;    
	    try {
	        inputChannel = new FileInputStream(source).getChannel();
	        outputChannel = new FileOutputStream(dest).getChannel();
	        outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
	    } finally {
	        inputChannel.close();
	        outputChannel.close();
	    }
	}
	
	public static Integer getTodayTime() {
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		return Integer.valueOf(String.valueOf(today.getTimeInMillis()).substring(0, 10));
	}
	
	public static Integer getYesterdayTime() {
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, -24);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		return Integer.valueOf(String.valueOf(today.getTimeInMillis()).substring(0, 10));
	}

	public static Integer getTomorrowTime() {
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.set(Calendar.HOUR_OF_DAY, 24);
		tomorrow.set(Calendar.MINUTE, 0);
		tomorrow.set(Calendar.SECOND, 0);
		return Integer.valueOf(String.valueOf(tomorrow.getTimeInMillis()).substring(0, 10));
	}
	
	final static Configuration config = Configuration.builder()
			.setSafeMode(true)
            .setCodeBlockEmitter(new CodeBlockEmitter())
            // Fenced code blocks are only available in 'extended mode'
            .forceExtentedProfile()
            .build();
	
	public static class CodeBlockEmitter implements BlockEmitter {
		@Override
		public void emitBlock(final StringBuilder out, final List<String> lines, final String meta) {
			out.append("<pre><code");
			if (meta != null && !meta.isEmpty()) {
//				out.append(" class=\"language-");
				out.append(" class=\"");
				out.append(meta);
				out.append('"');
			}
			out.append(">\r\n");
			for (final String l : lines) {
				escapedAdd(out, l);
				out.append('\n');
			}
			out.append("</code></pre>");
		}
	}
	
	public static void escapedAdd(final StringBuilder sb, final String str) {
		for (int i = 0; i < str.length(); i++) {
			final char ch = str.charAt(i);
			if (ch < 33 || Character.isWhitespace(ch) || Character.isSpaceChar(ch)) {
				sb.append(' ');
			} else {
				switch (ch) {
				case '"':
					sb.append("&quot;");
					break;
				case '\'':
					sb.append("&apos;");
					break;
				case '<':
					sb.append("&lt;");
					break;
				case '>':
					sb.append("&gt;");
					break;
				case '&':
					sb.append("&amp;");
					break;
				default:
					sb.append(ch);
					break;
				}
			}
		}
	}
	
	public static String markdown2html(String content) {
		if(StringKit.isBlank(content)){
			return content;
		}
		
		String processed = Processor.process(content, config);
		
		if(processed.indexOf("[mp3:") != -1){
			processed = processed.replaceAll("\\[mp3:(\\d+)\\]", "<iframe frameborder='no' border='0' marginwidth='0' marginheight='0' width=330 height=86 src='http://music.163.com/outchain/player?type=2&id=$1&auto=0&height=66'></iframe>");
		}
		
		return Funcs.emoji(processed);
	}
	
	/**
	 * 计算帖子权重
	 * 
	 * 根据点赞数、收藏数、评论数、下沉数、创建时间计算
	 * 
	 * @param loves			点赞数：权重占比1
	 * @param favorites 	点赞数：权重占比2
	 * @param comment		点赞数：权重占比2
	 * @param sinks			点赞数：权重占比-1
	 * @param create_time	创建时间，越早权重越低
	 * @return
	 */
	public static double getWeight(Long loves, Long favorites, Long comment, Long sinks, Long create_time) {
		
		long score = Math.max(loves - 1, 1) + favorites * 2 + comment * 2 - sinks;
		
		// 投票方向
		int sign = (score == 0) ? 0 : (score > 0 ? 1 : -1);
		
		// 帖子争议度
		double order = Math.log10(Math.max(Math.abs(score), 1));
		
		// 1459440000是项目创建时间
		double seconds = create_time - 1459440000;
		return Double.parseDouble(String.format("%.2f", order + sign * seconds / 45000));
	}
	
	/** 
	 * 随机指定范围内N个不重复的数 
	 * 最简单最基本的方法 
	 * @param min 指定范围最小值 
	 * @param max 指定范围最大值 
	 * @param n 随机数个数 
	 */  
	public static int[] randomCommon(int min, int max, int n) {
		if (n > (max - min + 1) || max < min) {
			return null;
		}
		int[] result = new int[n];
		int count = 0;
		while (count < n) {
			int num = (int) (Math.random() * (max - min)) + min;
			boolean flag = true;
			for (int j = 0; j < n; j++) {
				if (num == result[j]) {
					flag = false;
					break;
				}
			}
			if (flag) {
				result[count] = num;
				count++;
			}
		}
		return result;
	}
	
	public static void runTask(Runnable runnable){
		if(null != runnable){
			ExecutorService executor = Executors.newSingleThreadExecutor();
			executor.submit(runnable);
		}
	}
}