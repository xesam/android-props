package io.github.xesam.android.props;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xesamguo@gmail.com on 18-1-2.
 */

public class AProps implements IProps {
    private Map<String, String> mData = new HashMap<>();

    public AProps(File file) {
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("#")) {
                    continue;
                }
                String[] eles = line.split("=");
                if (eles.length < 2) {
                    continue;
                }
                mData.put(eles[2].trim(), eles[1].trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getValue(String key, String defaultValue) {
        String value = mData.get(key);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    public <T> AProps setClass(Class<?> clazz, String filedName, T value) {
        try {
            Field field = clazz.getDeclaredField(filedName);
            if ((field.getModifiers() & Modifier.FINAL) == 0) {
                boolean rawAccess = field.isAccessible();
                field.setAccessible(true);
                field.set(null, value);
                field.setAccessible(rawAccess);
            } else {
                Log.w("setClass!", clazz.getSimpleName() + "#" + filedName + " is final");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public <T> AProps setObject(Object target, String filedName, T value) {
        try {
            Field field = target.getClass().getDeclaredField(filedName);
            if ((field.getModifiers() & Modifier.FINAL) == 0) {
                boolean rawAccess = field.isAccessible();
                field.setAccessible(true);
                field.set(target, value);
                field.setAccessible(rawAccess);
            } else {
                Log.w("setObject!", target.getClass().getSimpleName() + "#" + filedName + " is final");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
}
