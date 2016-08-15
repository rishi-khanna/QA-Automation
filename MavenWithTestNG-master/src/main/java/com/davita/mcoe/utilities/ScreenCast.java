package com.davita.mcoe.utilities;

import org.monte.media.math.Rational;
import org.monte.media.Format;
import org.monte.screenrecorder.ScreenRecorder;

import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

import java.awt.*;
import java.io.IOException;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public class ScreenCast {

	private ScreenRecorder screenRecorder;

	public void startRecording() throws IOException, AWTException {
		GraphicsConfiguration gc = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();

		// Create a instance of ScreenRecorder with the required configurations
		screenRecorder = new ScreenRecorder(gc, new Format(MediaTypeKey,
				MediaType.FILE, MimeTypeKey, MIME_AVI), new Format(
				MediaTypeKey, MediaType.VIDEO, EncodingKey,
				ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, CompressorNameKey,
				ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, (int) 24,
				FrameRateKey, Rational.valueOf(15), QualityKey, 1.0f,
				KeyFrameIntervalKey, (int) (15 * 60)), new Format(MediaTypeKey,
				MediaType.VIDEO, EncodingKey, "black", FrameRateKey,
				Rational.valueOf(30)), null);

	}

	public void stopRecording() throws Exception {
		this.screenRecorder.stop();
	}
}
