/**
 * 
 */
package piscine;

import java.util.NoSuchElementException;

/**
 * @author vabois
 * 
 */
public class BasketPool<R extends Ressource> extends RessourcePool<R> {
	protected static int cpt;
	protected int index;
	
	public BasketPool (int index) {
		super();
		this.index = index;
		for (int i = 0; i < index; i++) {
			this.pool.add((R)new Basket());
		}
		BasketPool.cpt++;
	}
	
	public R provideRessource () {
		if (index > 0) {
			for (R b : this.pool) {
				if (b.isGiven() == false) {
					b.setStatus(true);
					this.index--;
					return b;
				}
			}
		} else {
			throw new NoSuchElementException("No available ressource in " + this.description());
		}
		return null;
	}
	
	public void recoverRessource (R r) {
		for (R b : this.pool) {
			if (b==r) {
				b.setStatus(false);
				this.index++;
				return;
			}
		}
		throw new IllegalArgumentException("Recovered ressource doesn't belong to " + this.description());
	}
	
	public String description () { return "pool basket"; }
	
}
