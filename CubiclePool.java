/**
 * 
 */
package piscine;

import java.util.NoSuchElementException;

/**
 * @author vabois
 * 
 */
public class CubiclePool<R extends Ressource> extends RessourcePool<R> {
	protected static int cpt;
	protected int index;
	
	public CubiclePool (int index) {
		super();
		this.index = index;
		for (int i = 0; i < index; i++) {
			this.pool.add((R) new Cubicle());
		}
		CubiclePool.cpt++;
	}
	
	public R provideRessource () {
		if (index > 0) {
			for (R c : this.pool) {
				if (c.isGiven() == false) {
					c.setStatus(true);
					this.index--;
					return c;
				}
			}
		} else {
			throw new NoSuchElementException("No available ressource in " + this.description());
		}
		return null;
	}
	
	public void recoverRessource (R r) {
		for (R c : this.pool) {
			if (c==r) {
				c.setStatus(false);
				this.index++;
				return;
			}
		}
		throw new IllegalArgumentException("Recovered ressource doesn't belong to " + this.description());
	}
	
	public String description () { return "pool cubicle"; }
	
}
