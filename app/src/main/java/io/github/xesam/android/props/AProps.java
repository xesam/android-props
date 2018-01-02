package io.github.xesam.android.props;

/**
 * Created by xe on 18-1-2.
 */

public class AProps implements IProps {
    @Override
    public String getString(String key) {
        return key + ":123";
    }

    @Override
    public <T> T getValue(String key, T clazz) {
        return null;
    }
}
