package com.jediminer543.util.render.model;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Jediminer543 on 13/08/2014.
 */
public class ObjectLoader
{
	static Random random = new Random();

	private static MTLMaterial currentMaterial;
	private static boolean currentSmooth = false;

	/**
	 * Parses an obj file and returns a model for that file
	 * The method also calls the MTLParser to add the material data to the model
	 *
	 * @param f The file to be read, intended to be an obj file.
	 *
	 * @return Returns a loaded model file.
	 *
	 * @throws IOException Thrown when either the file is invalid or if the MTL file is invalid.
	 */
	public static Model loadModel(File f) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(f));
		Model m = new Model();
		m.path = f;
		String line;
		while ((line = reader.readLine()) != null) {
			String prefix = line.split(" ")[0];
			if (prefix.equals("#")) {
			}
			else if (prefix.equals("mtllib"))
			{
				m.mtl = loadMTL(line, m);
			}
			else if (prefix.equals("v")) {
				m.vertices.add(loadVertex(line, m));
			}
			else if (prefix.equals("vn")) {
				m.normals.add(loadNormal(line, m));
			}
			else if (prefix.equals("vt")) {
				m.textures.add(loadTexture(line, m));
			}
			else if (prefix.equals("f")) {
				m.faces.add(loadFace(line, m));
			}
			else if (prefix.equals("usemtl")) {
				currentMaterial = loadMaterial(line, m);
			}
			/*else if (prefix.equals("s")) {
				//m.faces.add(loadFace(line, m));
			}*/
		}
		reader.close();
		return m;
	}

	/**
	 *
	 * Parses vertices
	 *
	 * @param s Line of file to be parsed.
	 * @param m Not used by this method.
	 * @return Vector3f with vertices.
	 */
	public static Vector3f loadVertex(String s, Model m)
	{
		String[] split = s.split(" ");
		float x = Float.valueOf(split[1]);
		float y = Float.valueOf(split[2]);
		float z = Float.valueOf(split[3]);
		return new Vector3f(x, y, z);
	}

	/**
	 *
	 * Parses normals
	 *
	 * @param s Line of file to be parsed.
	 * @param m Sets model hasNormals flag so that {@link #loadFace(String, Model)} knows weather to search for them or not.
	 * @return Vector3f containing normals
	 */
	public static Vector3f loadNormal(String s, Model m)
	{
		m.hasNormals = true;
		String[] split = s.split(" ");
		float x = Float.valueOf(split[1]);
		float y = Float.valueOf(split[2]);
		float z = Float.valueOf(split[3]);
		return new Vector3f(x, y, z);
	}

	/**
	 *
	 * Parses textureOld coords
	 *
	 * @param s Line of file to be parsed.
	 * @param m Sets model hasTextures flag so that {@link #loadFace(String, Model)} knows weather to search for them or not.
	 * @return Vector2f containing openGL compatible (Flipped obj) textureOld Coords.
	 */
	public static Vector2f loadTexture(String s, Model m)
	{
		m.hasTextures = true;
		String[] split = s.split(" ");
		float x = Float.valueOf(split[1]);
		float y = 1 - Float.valueOf(split[2]);
		return new Vector2f(x, y);
	}

	/**
	 *
 	 * @param s
	 * @param m
	 * @return
	 */
	public static Face loadFace(String s, Model m)
	{
		Face f = new Face();
		f.materialName = currentMaterial.name;
		f.material = currentMaterial;
		String[] split = s.split(" ");
		Vertex v1 = new Vertex();
		Vertex v2 = new Vertex();
		Vertex v3 = new Vertex();
		f.hasNormal = m.hasNormals;
		f.hasTexture = m.hasTextures;
		v1.vertex = m.vertices.get(Integer.parseInt(split[1].split("/")[0]) - 1);
		if (m.hasNormals & !m.hasTextures) {
			v1.texture = null;
			v1.normal = m.normals.get(Integer.parseInt(split[1].split("/")[1]) - 1);
		}
		else if (!m.hasNormals & m.hasTextures) {
			v1.texture = m.textures.get(Integer.parseInt(split[1].split("/")[1]) - 1);
			v1.normal = null;
		}
		else if (m.hasNormals & m.hasTextures) {
			v1.texture = m.textures.get(Integer.parseInt(split[1].split("/")[1]) - 1);
			v1.normal = m.normals.get(Integer.parseInt(split[1].split("/")[2]) - 1);
		}
		v2.vertex = m.vertices.get(Integer.parseInt(split[2].split("/")[0]) - 1);
		if (m.hasNormals & !m.hasTextures) {
			v2.texture = null;
			v2.normal = m.normals.get(Integer.parseInt(split[2].split("/")[1]) - 1);
		}
		else if (!m.hasNormals & m.hasTextures) {
			v2.texture = m.textures.get(Integer.parseInt(split[2].split("/")[1]) - 1);
			v2.normal = null;
		}
		else if (m.hasNormals & m.hasTextures) {
			v2.texture = m.textures.get(Integer.parseInt(split[2].split("/")[1]) - 1);
			v2.normal = m.normals.get(Integer.parseInt(split[2].split("/")[2]) - 1);
		}
		v3.vertex = m.vertices.get(Integer.parseInt(split[3].split("/")[0]) - 1);
		if (m.hasNormals & !m.hasTextures) {
			v3.texture = null;
			v3.normal = m.normals.get(Integer.parseInt(split[3].split("/")[1]) - 1);
		}
		else if (!m.hasNormals & m.hasTextures) {
			v3.texture = m.textures.get(Integer.parseInt(split[3].split("/")[1]) - 1);
			v3.normal = null;
		}
		else if (m.hasNormals & m.hasTextures) {
			v3.texture = m.textures.get(Integer.parseInt(split[3].split("/")[1]) - 1);
			v3.normal = m.normals.get(Integer.parseInt(split[3].split("/")[2]) - 1);
		}
		f.vertexes.add(v1);
		f.vertexes.add(v2);
		f.vertexes.add(v3);
		return f;
	}

	public static MTL loadMTL(String s, Model m) throws IOException
	{
		String[] split = s.split(" ");
		return MTLParser.loadMTL(new File(m.path.getParent() + "\\" + split[1]));
	}

	public static MTLMaterial loadMaterial(String s, Model m)
	{
		String[] split = s.split(" ");
		for (MTLMaterial material:m.mtl.materials)
		{
			if (material.name.equals(split[1]))
			{
				return material;
			}
		}
		return null;
	}
}
