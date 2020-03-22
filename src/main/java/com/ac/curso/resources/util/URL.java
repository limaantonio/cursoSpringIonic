package com.ac.curso.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

//	public static Iterable<Long> decodeIntList(String string) {
//
//		String[] s = string.split(",");
//
//		return Arrays.asList(s).stream().map(obj -> Long.parseLong(obj)).collect(Collectors.toList());
//
//	}

	public static Iterable<Long> decodeIntList(String s) {
		String[] vet = s.split(",");
		Iterable<Long> list = new ArrayList<>();
		for (int i = 0; i < vet.length; i++) {
			((ArrayList<Long>) list).add((long) Integer.parseInt(vet[i]));
		}
		return list;
		// return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}

	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} 
		catch (UnsupportedEncodingException e) {
			return "";
		}
	}	
}
