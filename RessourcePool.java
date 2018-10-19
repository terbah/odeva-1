/**
 * 
 */
package piscine;

import java.util.List;
import java.util.ArrayList;

/**
 * @author vabois et terbah
 *
 */
public abstract class RessourcePool<R extends Ressource> {
	protected List<R> pool = new ArrayList<R>();
	
	public RessourcePool () {  }
	
	public abstract R provideRessource () ;
	
	public abstract void recoverRessource (R r) ;
	
}
