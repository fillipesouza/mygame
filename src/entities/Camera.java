package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private float distanceFromPlayer = 80;
	private float angleAroundPlayer = 0;
	
	private Vector3f position = new Vector3f(0.0f,80.0f,0.0f);
	private float pitch = 20;
	private float yaw = 0;
	private float roll;
	
	private Player player;
	
	public Camera(Player player){
		this.player = player;
	}

	public void move(){
		
		calculateZoom();
		calculatePitch();
		calculateAngleAroundPlayer();
		float horizontalDistance = calculateHorizontalDistance();
		float verticalDistance = calculateVerticallDistance();
		calculateCameraPosition(horizontalDistance, verticalDistance);
		this.yaw = 180 - (player.getRotY() + angleAroundPlayer);
		
		/*
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			position.z -= 0.2f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			position.z += 0.2f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			position.x += 0.2f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			position.x -= 0.2f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
			position.y += 0.02f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
			position.y -= 0.02f;
		}
		*/
	}
	
	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}
	
	public void invertPitch(){
		this.pitch = -this.pitch;
	}
	
	public void invertRoll(){
		this.roll = -this.roll;
	}

	public float getYaw() {
		return yaw;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public float getRoll() {
		return roll;
	}

	public void setRoll(float roll) {
		this.roll = roll;
	}
	
	private void calculateCameraPosition(float horizDistance, float vertDistance){
		float theta = player.getRotY() + angleAroundPlayer;
		float offsetX = (float)(horizDistance * Math.sin(Math.toRadians(theta)));
		float offsetZ = (float)(horizDistance * Math.cos(Math.toRadians(theta)));
		position.x = player.getPosition().x - offsetX;
		position.z = player.getPosition().z - offsetZ;
		position.y = player.getPosition().y + vertDistance;
	}
	
	public float calculateHorizontalDistance(){
		return (float) (distanceFromPlayer*Math.cos((Math.toRadians(this.pitch))));
	}
	
	public float calculateVerticallDistance(){
		return (float) (distanceFromPlayer*Math.sin((Math.toRadians(this.pitch))));
	}
	
	
	
	public void calculateZoom(){
		//float zoomLevel = Mouse.getDWheel() * 0.1f;
		float zoomLevel = 0.0f;
		if(Keyboard.isKeyDown(Keyboard.KEY_Z)){
			 zoomLevel +=  0.7f;
		} else if(Keyboard.isKeyDown(Keyboard.KEY_X)){	
			 zoomLevel -=  0.7f;
		}
		distanceFromPlayer -= zoomLevel;
	}
	
	public void calculatePitch(){
		if(Mouse.isButtonDown(1)){
			float pitchChange = Mouse.getDY() * 0.1f;
			pitch -= pitchChange;
		}
	}
	
	public void calculateAngleAroundPlayer(){
		if(Mouse.isButtonDown(0)){
			float angleChange = Mouse.getDX() * 0.3f;
			angleAroundPlayer -= angleChange;
		}
	}
	
	
}
