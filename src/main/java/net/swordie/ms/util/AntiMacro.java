package net.swordie.ms.util;

import net.swordie.ms.ServerConstants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class AntiMacro {
    public enum NotificationType {
        Unknown(6), // decodes a string, when combined with MacroType.Admin also saves a screenshot from the user and sends to the server
        LieDetector(7),
        Detected(8),
        Passed(10);

        private int val;

        NotificationType(int val) {
            this.val = val;
        }

        public byte getVal() {
            return (byte)val;
        }
    }

    public enum AntiMacroType {
        Default(0),
        Timebomb(1),
        Admin(4);

        private int val;

        AntiMacroType(int val) {
            this.val = val;
        }

        public byte getVal() {
            return (byte)val;
        }
    }

    public static final String[] FONTS = {
        "AmaticSC", "Audiowide", "Caveat", "CoveredByYourGrace", "Rajdhani", "Srisakdi"
    };

    private Font font;
    private String text;

    public AntiMacro(String font, String text) throws IOException, FontFormatException {
        File file = new File(String.format("%s/antimacro_fonts/%s.ttf", ServerConstants.RESOURCES_DIR, font));
        InputStream stream = new FileInputStream(file);

        this.font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(30f);
        this.text = text;

        stream.close();
    }

    // MS default is 194x43
    public byte[] generateImage(int width, int height, Color background, Color foreground) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();

        // background
        graphics.setColor(background);
        graphics.fillRect(0, 0, width, height);

        // text
        graphics.setColor(foreground);
        graphics.setFont(this.font);
        graphics.drawString(this.text, 15, 30);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", stream);

        return stream.toByteArray();
    }

    public static Color getRandomColor() {
        switch (Util.getRandom(6)) {
            case 0: return Color.RED;
            case 1: return Color.YELLOW;
            case 2: return Color.CYAN;
            case 3: return Color.GREEN;
            case 4: return Color.ORANGE;
            case 5: return Color.PINK;
            default: return Color.WHITE;
        }
    }
}
