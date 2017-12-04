package converter.util;

public class Vector3f extends Vector2f{

	private float z;
	
	public Vector3f(){
		super();
		this.z = 0;
	}
	
	public Vector3f(float x, float y, float z) {
		super(x, y);
		this.z = z;
	}

	public float getZ(){
		return z;
	}
	
	public void setZ(float z){
		this.z = z;
	}
	
	public static Vector3f from(String[] args){
		float x = Float.parseFloat(args[0]);
		float y = Float.parseFloat(args[1]);
		float z = Float.parseFloat(args[2]);
		return new Vector3f(x, y, z);
	}
	
	public String toString(){
		return "(" + getX() + ", " + getY() + ", " + getZ() + ")";
	}
	
	public boolean equals(Object other){
		return other instanceof Vector3f ? equals((Vector3f) other) : false;
	}
	
	public boolean equals(Vector3f other){
		return super.equals(other) && z == other.z;
	}
	
	public Vector3f copy(){
		return new Vector3f(getX(), getY(), getZ());
	}
	
	public Vector3f translate(float x, float y, float z){
		super.translate(x, y);
		this.z += z;
		return this;
	}
	
	public Vector3f translate(Vector3f vector){
		return translate(vector.getX(), vector.getY(), vector.getZ());
	}
	
	public Vector3f normalize(){
		float length = length();
		float newX = getX() / length;
		float newY = getY() / length;
		float newZ = getZ() / length;
		set(newX, newY, newZ);
		return this;
	}
	
	public Vector3f set(float x, float y, float z){
		super.set(x, y);
		this.z = z;
		return this;
	}
	
	public float length(){
		return (float) Math.sqrt( (getX() * getX()) + (getY() * getY()) + (getZ() * getZ()) );
	}

	public Vector3f inverse() {
		setX(getX() * -1);
		setY(getY() * -1);
		setZ(getZ() * -1);
		return this;
	}
	
	public Vector3f add(Vector3f other){
		setX(getX() + other.getX());
		setX(getY() + other.getY());
		setX(getZ() + other.getZ());
		return this;
	}
}
