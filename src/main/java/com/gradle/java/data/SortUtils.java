package com.gradle.java.data;

import com.alibaba.fastjson.JSON;

/**
 * @author Arison
 * 排序算法
 */
public class SortUtils {

	public static void main(String[] args) {
		int [] array={2,1,123,432,2343,7,54,89,98,67,665,3454,23412};
		bubbleSort(array);
		System.out.println(JSON.toJSONString(array));
	}

	
	/**
	 * 冒泡排序<br>
	 * 时间复杂度: 平均情况与最差情况都是O(n^2)<br>
	 * 空间复杂度: O(1)
	 * @author weijielu
	 * @see ISort
	 * @see SortTest
	 */
	public static void bubbleSort(int[] array) {
		int temp = 0;
		for(int i = 0; i < array.length; i++){
			for(int j = 0; j < array.length - 1; j++){
				if(array[j] > array[j + 1]){
					temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
	}
}
