package com.jediminer543.util.render.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jediminer543 on 23/08/2014.
 *
 * Used to load blender animated models
 */
public class AnimatedModel
{

	private ArrayList<Model> models = new ArrayList<Model>();
	private int startFrame;
	private int endFrame;
	private String basePath;

	/**
	 * Initialises model
	 *
	 * @param Path must contain bath and base file name e.g. res/model/model
	 *                where the files are called model_000001.obj model_000002.obj etc.
	 */
	public AnimatedModel(String Path, int StartFrame, int EndFrame) throws IOException
	{
		this.basePath = Path;
		this.startFrame = StartFrame;
		this.endFrame = EndFrame;
		init();
	}

	public void init() throws IOException
	{
		for (int i = startFrame; i == endFrame+1; i++)
		{
			File file = new File(basePath + "_" + String.format("%06d", i));
			models.add(i, ObjectLoader.loadModel(file));
		}
	}
}
