package com.example.mywidgets;

import java.util.Random;

public class NumberGenerator {
	
	public static int Generate(int max){
		Random random = new Random();
		return random.nextInt(max); // Return angka scr random dari 0 hingga input parameter
	}
}
