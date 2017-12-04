package converter.render;

import converter.util.GlobalAxis;
import converter.util.MathHelper;
import converter.util.Matrix4f;
import converter.util.Quaternion;
import converter.util.Vector3f;

public class Camera {

	public static final float ROTATION_SENSITIVITY = 1.0F;
	public static final float ZOOM_SENSITIVITY = 0.05F;
	public static final float ZOOM_SCALAR = 0.0F;
	
	private Vector3f rotationPoint = new Vector3f(0,0,0);
	private Vector3f position = new Vector3f();
	private Quaternion quat = new Quaternion();
	
	private float distance = 10.0f;
	
	public Camera(){
		position = new Vector3f(0.0F, 5.0F, 10.0f);
	}
	
	public void moveCamera(float rotationX, float rotationY, float zoom){
		//Quaternion newRot = new Quaternion(new Vector3f((float)Math.toRadians(rotationY), (float)Math.toRadians(rotationX), 0.0f));
		
		distance = MathHelper.clampf(distance + zoom * ZOOM_SENSITIVITY, 1.0F, 1000.0F);
		
		Quaternion rotX = new Quaternion((float) Math.toRadians(rotationY), GlobalAxis.X.toVector());
		Quaternion rotY = new Quaternion((float) Math.toRadians(rotationX), GlobalAxis.Y.toVector());
		
		quat = rotY.mult(quat);
		quat = rotX.mult(quat);
		
		Quaternion inversed = quat.copy().inverse();
		position = inversed.mult(GlobalAxis.Z.toVector(distance));
	}
	
	public Matrix4f createViewMatrix(){
		return MathHelper.createViewMatrix(position, quat);
	}
}
