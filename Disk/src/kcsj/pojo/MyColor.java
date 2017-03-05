package kcsj.pojo;

/**
 * Created by belong on 2017/2/25.
 */
import java.awt.*;
/**
 * 自定义颜色类，用于标识文件的类型，同一个颜色说明是一个文件
 * @author belong
 *
 */
public class MyColor {

    private Color[] colors = { Color.cyan, new Color(255, 185, 15),
            new Color(255, 182, 193), new Color(224, 102, 255),
            new Color(205, 205, 0), new Color(0, 201, 87),
            new Color(255, 128, 0), new Color(252, 230, 201),
            new Color(255, 127, 80), new Color(135, 38, 87),
            new Color(255, 0, 255), Color.magenta, Color.yellow,
            Color.red,new Color(255,99,71),new Color(160,32,240),new Color(138,43,226)};
    private static int index = 0;

    /**
     * 循环得到颜色每16个循环一次，用于区分不同的文件
     * @return
     */
    public Color getColor() {
        Color c = colors[index++];
        index %= colors.length;
        return c;
    }

}

