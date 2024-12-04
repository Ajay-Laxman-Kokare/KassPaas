package bbpsQR;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class QRCodeWithGradientAndLogo {

    public static void main(String[] args) throws Exception {
        String qrContent = "https://example.com"; // The URL or text you want to encode
        int size = 400;
        
        // Step 1: Generate the QR Code using ZXing
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 1);
        
        BitMatrix bitMatrix = new MultiFormatWriter().encode(qrContent, BarcodeFormat.QR_CODE, size, size, hints);
        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        
        // Step 2: Apply Gradient
        BufferedImage gradientQrImage = applyGradient(qrImage);

        // Step 3: Add Circular Logo to the QR Code
        String logoPath = "/Users/ajaykokare/Documents/KasProject/bharatConnect.png"; // Logo path
        BufferedImage finalImage = addCircularLogo(gradientQrImage, logoPath);

        // Step 4: Save the resulting image
        File outputfile = new File("/Users/ajaykokare/Documents/KasProject/QrCodes/bbpsQR.png");
        ImageIO.write(finalImage, "png", outputfile);
        
        System.out.println("QR Code generated successfully!");
    }
    
    // Method to apply gradient (red at the top, blue at the bottom)
    private static BufferedImage applyGradient(BufferedImage qrImage) {
        int width = qrImage.getWidth();
        int height = qrImage.getHeight();

        BufferedImage gradientImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = gradientImage.createGraphics();
        
        // Gradient colors (top: red, bottom: blue)
        GradientPaint gradient = new GradientPaint(0, 0, Color.RED, 0, height, Color.BLUE);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height);
        
        // Draw the QR code on top of the gradient
        g2d.setComposite(AlphaComposite.SrcIn);
        g2d.drawImage(qrImage, 0, 0, null);
        g2d.dispose();

        return gradientImage;
    }
    
    // Method to add a circular logo at the center of the QR code
    private static BufferedImage addCircularLogo(BufferedImage qrImage, String logoPath) throws Exception {
        BufferedImage logo = ImageIO.read(new File(logoPath));
        
        int width = qrImage.getWidth();
        int height = qrImage.getHeight();
        
        // Size of the logo (scaled to 1/5 of the QR code's width and height)
        int logoSize = Math.min(width, height) / 5;
        
        // Create a new image to combine the QR code and the logo
        BufferedImage combinedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = combinedImage.createGraphics();
        
        // Draw the QR code
        g2d.drawImage(qrImage, 0, 0, null);

        // Create a circular mask for the logo
        int x = (width - logoSize) / 2;
        int y = (height - logoSize) / 2;
        
        // Mask for circular logo
        g2d.setClip(new Ellipse2D.Double(x, y, logoSize, logoSize));
        
        // Draw the logo, but clipped to the circle
        g2d.drawImage(logo, x, y, logoSize, logoSize, null);
        g2d.dispose();

        return combinedImage;
    }
}
