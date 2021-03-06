package com.amittaigames.engine.graphics;

public class TexturedRect extends Renderable {

	private float x;
	private float y;
	private float width;
	private float height;
	private TexturedMesh mesh;
	
	private float angle;
	private float scale = 1f;
	
	/**
	 * @param x X position for the texture
	 * @param y Y position for the texture
	 * @param width Width for the texture to be rendered at
	 * @param height Height for the texture to be rendered at
	 * @param path Path to texture
	 * @param external Is the texture external to the JAR file or not?
	 */
	public TexturedRect(float x, float y, float width, float height, String path, boolean external) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		init(path, external);
	}

	/**
	 * Initialize a TexturedRect with given information
	 * @param path Path to texture
	 * @param external Internal or external image
	 */
	private void init(String path, boolean external) {
		float[] pos = {
			x, y,
			x + width, y,
			x + width, y + height,
			x, y + height
		};
		
		float[] color = {
			1, 1, 1,
			1, 1, 1,
			1, 1, 1,
			1, 1, 1
		};
		
		float[] coords = {
			0, 0,
			1, 0,
			1, 1,
			0, 1
		};
		
		int[] list = {
			0, 1, 2,
			0, 3, 2
		};
		
		mesh = new TexturedMesh(path, pos, color, coords, list, external);
	}

	/**
	 * Rotates TexturedRect by a specified angle
	 * @param angle Angle to increment by
	 */
	public void rotate(float angle) {
		this.angle += angle;
	}

	/**
	 * Translate position of the texture
	 * @param x X component
	 * @param y Y component
	 */
	public void translate(float x, float y) {
		this.x += x;
		this.y += y;
		
		float[] pos = {
			this.x, this.y,
			this.x + this.width, this.y,
			this.x + this.width, this.y + this.height,
			this.x, this.y + this.height
		};
		
		mesh.updatePosition(pos);
	}

	/**
	 * Directly set position of texture
	 * @param x X position
	 * @param y Y position
	 */
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
		
		float[] pos = {
			this.x, this.y,
			this.x + this.width, this.y,
			this.x + this.width, this.y + this.height,
			this.x, this.y + this.height
		};

		mesh.updatePosition(pos);
	}

	/**
	 * Set the color of the texture
	 * @param r Red component
	 * @param g Green component
	 * @param b Blue component
	 */
	public void setColor(int r, int g, int b) {
		float rr = (float)r/255.0f;
		float gg = (float)g/255.0f;
		float bb = (float)b/255.0f;
		
		float[] color = new float[16];
		for (int i = 0; i < 16; i += 4) {
			color[i] = rr;
			color[i + 1] = gg;
			color[i + 2] = bb;
			color[i + 3] = 1;
		}
		
		mesh.updateColor(color);
	}
	
	/**
	 * Set the color of the texture
	 * @param r Red component
	 * @param g Green component
	 * @param b Blue component
	 * @param a Alpha component
	 */
	public void setColor(int r, int g, int b, int a) {
		float rr = (float)r/255.0f;
		float gg = (float)g/255.0f;
		float bb = (float)b/255.0f;
		float aa = (float)a/255.0f;
		
		float[] color = new float[16];
		for (int i = 0; i < 16; i += 4) {
			color[i] = rr;
			color[i + 1] = gg;
			color[i + 2] = bb;
			color[i + 3] = aa;
		}
		
		mesh.updateColor(color);
	}
	
	/**
	 * Set UV coordinates to a portion of the full image
	 * @param xoff X offset coordinate on image
	 * @param yoff Y offset coordinate on image
	 * @param dwidth Width offset coordinate on image
	 * @param dheight Height offset coordinate on image
	 */
	public void subImage(int xoff, int yoff, int dwidth, int dheight) {
		int imgWidth = mesh.getImageWidth();
		int imgHeight = mesh.getImageHeight();
		
		float x = (float)xoff/(float)imgWidth;
		float y = (float)yoff/(float)imgHeight;
		float w = (float)dwidth/(float)imgWidth;
		float h = (float)dheight/(float)imgHeight;
		
		float[] uv = {
			x, y,
			x + w, y,
			x + w, y + h,
			x, y + h
		};
		
		mesh.updateCoords(uv);
	}

	/**
	 * Scale the texture
	 * @param scale Scale factor
	 */
	public void scale(float scale) {
		this.scale += scale;
	}

	/**
	 * Call mesh deletion
	 */
	public void delete() {
		this.mesh.delete();
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public void setWidth(float width) {
		this.width = width;

		float[] pos = {
			this.x, this.y,
			this.x + this.width, this.y,
			this.x + this.width, this.y + this.height,
			this.x, this.y + this.height
		};

		mesh.updatePosition(pos);
	}

	public void setHeight(float height) {
		this.height = height;

		float[] pos = {
			this.x, this.y,
			this.x + this.width, this.y,
			this.x + this.width, this.y + this.height,
			this.x, this.y + this.height
		};

		mesh.updatePosition(pos);
	}

	public TexturedMesh getMesh() {
		return mesh;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public float getScale() {
		return scale;
	}
	
}