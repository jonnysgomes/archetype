package com.web.archetype.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

public class ReflectionUtil {
	@SuppressWarnings("rawtypes")
	public static String getFieldNameID(Class clazz) {
		if (clazz != Object.class) {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(Id.class)) {
					field.setAccessible(true);
					try {
						return field.getName();
					} catch (Exception e) {
						return null;
					}
				}
			}
		}
		if (clazz.getSuperclass() != Object.class) {
			return getFieldNameID(clazz.getSuperclass());
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static String getFieldNameCodigo(Class clazz) {
		if (clazz != Object.class) {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				if (field.getName().equals("codigo")) {
					field.setAccessible(true);
					try {
						return field.getName();
					} catch (Exception e) {
						return null;
					}
				}
			}
		}
		if (clazz.getSuperclass() != Object.class) {
			return getFieldNameCodigo(clazz.getSuperclass());
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static Object getValueField(Class clazz, Object object,
			String fieldName) {
		if (clazz != Object.class) {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				if (field.getName().equals(fieldName)) {
					field.setAccessible(true);
					try {
						return field.get(object);
					} catch (Exception e) {
						return null;
					}
				}
			}
		}
		if (clazz.getSuperclass() != Object.class) {
			return getValueField(clazz.getSuperclass(), object, fieldName);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static String getQueryString(String namedQuery, Class class1) {
		try {
			@SuppressWarnings("unchecked")
			NamedQueries namedQueries = (NamedQueries) class1
					.getAnnotation(NamedQueries.class);
			NamedQuery[] naQueries = namedQueries.value();
			String queryString = null;
			for (NamedQuery namedQuery2 : naQueries) {
				if (namedQuery2.name().equals(namedQuery)) {
					queryString = namedQuery2.query();
					break;
				}
			}
			return queryString;
		} catch (Exception e) {
			throw new IllegalArgumentException("NamedQuery not found:"
					+ class1.getCanonicalName());
		}
	}

	@SuppressWarnings("rawtypes")
	public static Object getValueID(Class clazz, Object object) {
		if (clazz != Object.class) {
			Field[] campos = clazz.getDeclaredFields();
			for (Field field : campos) {
				if (field.isAnnotationPresent(Id.class)) {
					field.setAccessible(true);
					try {
						return field.get(object);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		if (clazz.getSuperclass() != Object.class) {
			return getValueID(clazz.getSuperclass(), object);
		}
		return null;
	}

	public static void setMethodValue(Object object, String method,
			Object value, Class<?>... parameterTypes) throws Exception {
		try {
			Method methodObject = getMethod(object.getClass(), method,
					parameterTypes);
			methodObject.setAccessible(true);
			methodObject.invoke(object, value);
		} catch (Exception e) {
			throw e;
		}
	}

	public static void setFieldValue(Object object, String field, Object value)
			throws Exception {
		try {
			Field fieldObject = getField(object.getClass(), field);
			fieldObject.setAccessible(true);
			fieldObject.set(object, value);
		} catch (Exception e) {
			throw e;
		}
	}

	private static Field getField(Class<?> clazz, String field) {
		if (clazz != Object.class) {
			try {
				Field fieldObject = clazz.getDeclaredField(field);
				return fieldObject;
			} catch (Exception e) {
			}
		}
		if (clazz.getSuperclass() != Object.class) {
			return getField(clazz.getSuperclass(), field);
		}
		return null;

	}

	private static Method getMethod(Class<?> clazz, String method,
			Class<?>... parameterTypes) {
		if (clazz != Object.class) {
			try {
				Method methodObject = clazz.getDeclaredMethod(method,
						parameterTypes);
				return methodObject;
			} catch (Exception e) {
			}
		}
		if (clazz.getSuperclass() != Object.class) {
			return getMethod(clazz.getSuperclass(), method, parameterTypes);
		}
		return null;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Field> getAnnotatedFields(Class clazzAnnotation,
			Object entity) {
		List<Field> list = new ArrayList<Field>();
		if (entity.getClass() != Object.class) {
			Field[] campos = entity.getClass().getDeclaredFields();
			for (Field field : campos) {
				if (field.isAnnotationPresent(clazzAnnotation)) {
					field.setAccessible(true);
					try {
						list.add(field);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		if (entity.getClass().getSuperclass() != Object.class) {
			list.addAll(getAnnotatedFields(entity.getClass().getSuperclass(),
					entity));
		}
		return list;
	}

}
