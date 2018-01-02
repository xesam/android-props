package io.github.xesam.android.props;

/**
 * Created by xe on 18-1-2.
 */

public interface IProps {
    String getString(String key);

    <T> T getValue(String key, T clazz);
}
