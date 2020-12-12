<<<<<<< HEAD:model/fleet/Movement.java
package model.fleet;

import model.planet.Planet;
=======
package org.iogame.model.fleet;

import org.iogame.model.planet.Planet;
>>>>>>> master:src/main/java/org/iogame/model/fleet/Movement.java

public class Movement {
	// can be null (e.g. with retreat) ?
	private Planet origin;
	private Planet target;


	public Movement(Planet origin, Planet target) {
		this.origin = origin;
		this.target = target;
	}


	public Planet getOrigin() {
		return origin;
	}

	public void setOrigin(Planet origin) {
		this.origin = origin;
	}

	public Planet getTarget() {
		return target;
	}

	public void setTarget(Planet target) {
		this.target = target;
	}


}
