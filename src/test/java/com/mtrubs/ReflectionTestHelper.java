package com.mtrubs;

import java.lang.reflect.Field;

import static org.testng.Assert.fail;

/**
 * @author mrubino
 * @since 2014-07-20
 */
public class ReflectionTestHelper {

    private ReflectionTestHelper() {
    }

    public static <T> T getField(Object instance, String fieldName, Class<T> value) {
        return getField(instance, instance.getClass(), fieldName, value);
    }

    private static <T> T getField(Object instance, Class<?> type, String fieldName, Class<T> value) {
        try {
            Field field = type.getDeclaredField(fieldName);
            field.setAccessible(true);
            return value.cast(field.get(instance));
        } catch (IllegalAccessException e) {
            fail(e.getMessage(), e);
            // unreachable
            return null;
        } catch (NoSuchFieldException e) {
            Class<?> parent = type.getSuperclass();
            if (parent == null) {
                fail(e.getMessage(), e);
                // unreachable
                return null;
            } else {
                return getField(instance, parent, fieldName, value);
            }
        }
    }

    public static void setField(Object instance, String fieldName, Object value) {
        setField(instance, instance.getClass(), fieldName, value);
    }

    private static void setField(Object instance, Class<?> type, String fieldName, Object value) {
        try {
            Field field = type.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(instance, value);
        } catch (IllegalAccessException e) {
            fail(e.getMessage(), e);
        } catch (NoSuchFieldException e) {
            Class<?> parent = type.getSuperclass();
            if (parent == null) {
                fail(e.getMessage(), e);
            } else {
                setField(instance, parent, fieldName, value);
            }
        }
    }
}
