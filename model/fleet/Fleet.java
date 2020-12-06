package iogame.model.fleet;

import iogame.model.planet.Planet;

public class Fleet {

	private double x;
	private double y;
	private double dx;
	private double dy;
	private double speed;
	private boolean returned = false;
	
	private Movement movement = null;
	
	private Planet location= null;
	


	public Fleet(double speed,Planet location) {
		this.x = location.getX();
		this.y = location.getY();
		this.speed = speed;
		this.location = location;
	}

	public void move(double delta) {
		if(movement!=null) {
		double nextStep = Math.sqrt(Math.pow( dx*speed*delta,2) + Math.pow(dy*speed*delta,2));
		if(getTravelDistance()<= nextStep) {
			arrive();
			return;
		}
		x=x+dx*speed*delta;
		y=y+dy*speed*delta;
		System.out.println("x=" + x + " , y = " +y);
		}
	}
	
	public void arrive() {
		System.out.println("i have arrived!!");
		location = movement.getTarget();
		x= location.getX();
		y= location.getY();
		movement = null;
	}
	
	public Planet getLocation() {
		return location;
	}

	public void setLocation(Planet location) {
		this.location = location;
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Movement getMovement() {
		return movement;
	}

	public void setMovement(Movement movement) {
		this.movement = movement;
		double x1 =movement.getOrigin().getX();
		double y1 =movement.getOrigin().getY();
		double x2 =movement.getTarget().getX();
		double y2 =movement.getTarget().getY();
		
		double length = getTravelDistance();
		dx = (x2 - x1)  / length;
		dy = (y2 - y1) / length;
		System.out.println("dx=" + dx + " , dy = " +dy);
		location = null;
	}
	
	
	private double getTravelDistance() {
		

		double x2 =movement.getTarget().getX();
		double y2 =movement.getTarget().getY();
		
		return Math.sqrt((x2-x)*(x2-x) + (y2-y)*(y2-y));
		
	}

	
}
