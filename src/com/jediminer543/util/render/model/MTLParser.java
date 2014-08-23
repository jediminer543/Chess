package com.jediminer543.util.render.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Jediminer543 on 13/08/2014.
 */
public class MTLParser
{
	private static MTLMaterial currentMaterial;

	public static MTL loadMTL(File f) throws IOException
	{
		currentMaterial = null;
		BufferedReader reader = new BufferedReader(new FileReader(f));
		MTL m = new MTL();
		String line;
		while ((line = reader.readLine()) != null) {
			String prefix = line.split(" ")[0];
			if (prefix.equals("#")) {
			}
			else if (prefix.equals("newmtl")) {
				loadNewMaterial(line, m);
			}
			else if (prefix.equals("map_Kd")) {
				loadDiffuseTexture(line, m);
			}
		}
		m.materials.add(currentMaterial);
		reader.close();
		return m;
	}

	public static MTLMaterial loadNewMaterial(String s, MTL mtl)
	{
		if (!(currentMaterial == null)) {
			mtl.materials.add(currentMaterial);
		}
		String[] split = s.split(" ");
		MTLMaterial newmtl = new MTLMaterial(split[1]);
		currentMaterial = newmtl;
		return newmtl;
	}

	public static String loadDiffuseTexture(String s, MTL mtl)
	{
		String[] split = s.split(" ");
		String texture = split[1];
		currentMaterial.textureName = texture;
		return texture;
	}

}
