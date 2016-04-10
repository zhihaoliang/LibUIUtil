package com.zhihaoliang.util.ui;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Date;

import org.json.JSONException;

import android.os.Environment;

public class Log {
    /**
     * 打印对象
     */
    public static void log(Object... args) {
        if (args == null || args.length == 0) {
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < args.length; i++) {
            stringBuilder.append(args[i]);
        }

        android.util.Log.e("----", stringBuilder.toString());
    }
    /**
     * 打印对象
     */
    public static void logObject(Object object) {
        if (object == null) {
            android.util.Log.e("----", "null");
        }

        try {
            wrapJson(object);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
    /**
     * 打印对象到同一行
     */
    public static void logObjectLine(Object object) {
        if (object == null) {
            android.util.Log.e("----", "null");
        }

        try {
            wrapJsonLine(object);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    /**
     * 打印对象到sdcard
     */
    public static void LogFile(String scanResult) {
        String sdCard = getSdCard();
        File result = new File(sdCard + "/resultCode.txt");
        if (result.exists()) {
            if (result.isDirectory()) {
                return;
            }
        } else {
            try {
                if (!result.createNewFile()) {
                    return;
                }
            } catch (IOException e) {
                return;
            }
        }
        try {
            PrintStream pr = new PrintStream(new BufferedOutputStream(new FileOutputStream(result,
                    true)));
            pr.println(scanResult);
            pr.close();
        } catch (IOException e) {
        }
    }


    private static void wrapJsonLine(Object object) throws JSONException,
            IllegalArgumentException, IllegalAccessException {
        final Field[] fields = object.getClass().getDeclaredFields();
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                AccessibleObject.setAccessible(fields, true);
                return null;
            }
        });
        StringBuffer stringBuffer = new StringBuffer();
        for (Field field : fields) {
            Class<?> type = field.getType();
            if (isSimpleType(type)) {
                Object obj = field.get(object);
                String name = field.getName();
                if (obj != null) {
                    stringBuffer.append(name + " : " + obj + "    ");
                }
            }

        }
        android.util.Log.e("----", stringBuffer.toString());

    }

    private static void wrapJson(Object object) throws JSONException,
            IllegalArgumentException, IllegalAccessException {
        final Field[] fields = object.getClass().getDeclaredFields();
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                AccessibleObject.setAccessible(fields, true);
                return null;
            }
        });
        for (Field field : fields) {
            Class<?> type = field.getType();
            if (isSimpleType(type)) {
                Object obj = field.get(object);
                String name = field.getName();
                android.util.Log.e("----", name + " : " + obj);
            }

        }

    }

    private static boolean isSimpleType(Class<?> type) {
        if (type.isPrimitive() || isWrappedPrimitive(type)
                || type.getName().equals(String.class.getName())
                || type.isEnum() || type.getName().equals(Date.class.getName())) {
            return true;
        }
        return false;
    }

    private static boolean isWrappedPrimitive(Class<?> type) {
        if (type.getName().equals(Boolean.class.getName())
                || type.getName().equals(Byte.class.getName())
                || type.getName().equals(Character.class.getName())
                || type.getName().equals(Short.class.getName())
                || type.getName().equals(Integer.class.getName())
                || type.getName().equals(Long.class.getName())
                || type.getName().equals(Float.class.getName())
                || type.getName().equals(Double.class.getName())) {
            return true;
        }
        return false;
    }


    private static String getSdCard() {
        String sdCardState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(sdCardState)) {
            return Environment.getExternalStorageDirectory().getPath();
        }
        return null;
    }

}
