package com.kaas.kaasPass.CommonUtils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;

public class QrUtils {
	public static void generateQr(String key, String path, String logoPath) throws Exception{
		QRCodeWriter writer = new QRCodeWriter();
		
		BitMatrix matrix = writer.encode(key, BarcodeFormat.QR_CODE, 250, 250);
		 Color foregroundColor = new Color(0, 102, 204); // Blue color for QR code
	        Color backgroundColor = new Color(255, 255, 255); // White background

	        // Create a MatrixToImageConfig with custom foreground and background colors
	        MatrixToImageConfig config = new MatrixToImageConfig(
	            foregroundColor.getRGB(),  // Set foreground color (QR code color)
	            backgroundColor.getRGB()   // Set background color (empty space color)
	        );
	        
	        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(matrix, config);

	        // Step 1: Load the logo image
	        
	        BufferedImage logo = ImageIO.read(new File(logoPath));

	        // Step 2: Calculate the logo size (resize it to fit within the QR code)
	        int logoWidth = qrImage.getWidth() / 5;  // logo will be 1/5th of the QR code's width
	        int logoHeight = qrImage.getHeight() / 5; // logo will be 1/5th of the QR code's height

	        // Step 3: Calculate the position to place the logo (center of the QR code)
	        int x = (qrImage.getWidth() - logoWidth) / 2;
	        int y = (qrImage.getHeight() - logoHeight) / 2;

	        // Step 4: Resize the logo image
	        Image logoImage = logo.getScaledInstance(logoWidth, logoHeight, Image.SCALE_SMOOTH);

	        // Step 5: Draw the logo over the QR code image
	        Graphics2D g = qrImage.createGraphics();
	        g.drawImage(logoImage, x, y, null);
	        g.dispose();

	        // Step 6: Save the final QR code with the logo
	        ImageIO.write(qrImage, "PNG", new File(path));
	        
		/*Path filePath = FileSystems.getDefault().getPath(path);
		MatrixToImageWriter.writeToPath(matrix, "PNG", filePath, config);*/
	}
	
	public static String readQr(String filePath) throws IOException, NotFoundException {
		
		BufferedImage bufferedImage = ImageIO.read(new File(filePath));
		LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
		BinaryBitmap bitMap = new BinaryBitmap(new HybridBinarizer(source));
		
		MultiFormatReader reader = new MultiFormatReader();
		
		Result result = reader.decode(bitMap);
		
		return result.getText();
	}
}
