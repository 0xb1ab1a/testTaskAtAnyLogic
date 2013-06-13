package util;

import java.awt.*;

public class UIUtil {
    private UIUtil() {

    }

    public static void adjustPositionToCenter(Window window) {
        Dimension dim = window.getToolkit().getScreenSize();
        int screenWidth = dim.width;
        int screenHeight = dim.height;
        final Dimension framePreferredSize = window.getPreferredSize();
        int frameWidth = framePreferredSize.width;
        int frameHeight = framePreferredSize.height;
        window.setLocation((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2);
    }
}
