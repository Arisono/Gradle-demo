package com.gradle.api.github;

import java.io.IOException;

import com.gradle.android.retrofit.OkhttpUtils;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class GithubApi {
	
	
	private static String baseUrl="https://api.github.com/";
	private static String page="1";
	private static String per_page="30";
	
	
	//Search repositories
	private static String search_repositories=baseUrl+"search/repositories";
	private static String q="stars:>5+language:java+created:>=2000-12-14";
	private static String sort="stars";
	private static String order="desc";
	

	public static void main(String[] args) {
		search_repositories();
	}
	
	
	
	/**
	 * 查询github repositories
	 * 
	 */
	public static void search_repositories() {
        search_repositories+="?q="+q+"&sort="+sort+"&order="+order+"&page="+page+"&per_page="+per_page;
		Request request = new Request.Builder()
				.url(search_repositories)
				.build();
		OkhttpUtils.client.newCall(request).enqueue(new Callback() {
			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				OkhttpUtils.println(OkhttpUtils.getResponseString(response));
				OkhttpUtils.println(search_repositories);
			}

			@Override
			public void onFailure(Call call, IOException e) {
				OkhttpUtils.onFailurePrintln(e);
			}
		});
	}

}
