package com.jediminer543.util.render;

import de.matthiasmann.twl.utils.PNGDecoder;
import org.lwjgl.BufferUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * Created by Ben on 21/08/2014.
 */
public class DeprecatedTexture
{
	private int width;
	private int height;

	private int id;

	ByteBuffer buffer;

	public DeprecatedTexture(String pngRef) throws IOException
	{
			this(new FileInputStream(pngRef));
	}

	public DeprecatedTexture(InputStream inputStream)
	{
		try {
			PNGDecoder decoder = new PNGDecoder(inputStream);

			width = decoder.getWidth();
			height = decoder.getHeight();

			buffer = BufferUtils.createByteBuffer(4 * decoder.getWidth() * decoder.getHeight());

			decoder.decode(buffer, 4 * decoder.getWidth(), PNGDecoder.Format.RGBA);

			buffer.flip();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				inputStream.close();
			}
			catch (IOException e)
			{
				//
			}
		}

		this.id = DeprecatedTextureHandeler.addTexture(buffer);

	}

	public void bind()
	{
		DeprecatedTextureHandeler.setCurrentTextureID(id);
	}


}
