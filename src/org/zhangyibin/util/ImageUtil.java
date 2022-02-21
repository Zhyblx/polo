package org.zhangyibin.util;

import javax.swing.*;
import java.io.File;

/**
 * 图片工具
 *
 * @author zhangyibin
 */
public class ImageUtil {
    private static final String imgFolder = "resources/image/";

    public static ImageIcon getImageIcon(String imgName) {
        ImageIcon icon = new ImageIcon((new File(imgFolder, imgName)).getAbsolutePath());
        return icon;

    }
}
