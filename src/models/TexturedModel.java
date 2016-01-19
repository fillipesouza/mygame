package models;

import textures.ModelTexture;

public class TexturedModel {
	
	private RawModel rawModel;
	private ModelTexture textures;
	
	public TexturedModel(RawModel model, ModelTexture texture){
		this.rawModel = model;
		this.textures = texture;
	}
	
	public RawModel getRawModel(){
		return rawModel;
	}
	
	public ModelTexture getTexture(){
		return textures;
	}

}
