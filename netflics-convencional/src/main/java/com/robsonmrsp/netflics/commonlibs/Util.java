package com.robsonmrsp.netflics.commonlibs;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public class Util {

	public static String removeNonUnicodeCharAndSpaces(String input) {
		String localStr = input;
		localStr = Normalizer.normalize(localStr, Normalizer.Form.NFD);
		localStr = localStr.replaceAll("\\p{Space}", "_").replaceAll("[^\\p{ASCII}]", "");
		return localStr;
	}

	public static <T> T createObjectFrom(Class<T> clazz, Map<String, String> mapfields) {
		T newFilterObject = null;
		try {
			newFilterObject = clazz.newInstance();
			Field[] fields2 = newFilterObject.getClass().getDeclaredFields();
			for (String fieldName : mapfields.keySet()) {
				try {
					Field field;
					field = clazz.getDeclaredField(fieldName);
					field.setAccessible(true);
					field.set(newFilterObject, getValue(field, mapfields.get(fieldName)));
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
		}

		return newFilterObject;
	}

	private static Object getValue(Field field, String val) {
		try {
			if (field.getType().equals(Integer.class)) {
				return new Integer(val);
			} else if (field.getType().equals(Long.class)) {
				return new Long(val);
			} else if (field.getType().equals(String.class)) {
				return val.length() == 0 ? null : val;
			} else if (field.getType().equals(Double.class)) {
				return new Double(val);
			} else if (field.getType().equals(Boolean.class)) {
				if (val != null && val.length() > 0)
					return new Boolean(val);

			} else if (field.getType().equals(LocalDate.class)) {
				return DateUtil.stringAsLocalDate(val);
			} else if (field.getType().equals(LocalDateTime.class)) {
				return DateUtil.stringAsLocalDateTime(val);
			}
		} catch (

		Exception e) {
		}
		return null;
	}

	public static boolean elementHasProperty(Object filter, String string) {
		return getProperty(filter, string) != null;
	}

	public static Object getProperty(Object obj, String property) {
		Object returnValue = null;

		try {
			String methodName = "get" + property.substring(0, 1).toUpperCase() + property.substring(1, property.length());
			Class clazz = obj.getClass();
			Method method = clazz.getMethod(methodName, null);
			returnValue = method.invoke(obj, null);
		} catch (Exception e) {
			// Do nothing, we'll return the default value
		}

		return returnValue;
	}

	public static String wrapSufix(String input, Boolean exact) {
		return exact ? input : "%" + input + "%";
	}
}
