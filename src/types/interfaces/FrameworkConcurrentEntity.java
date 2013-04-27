package types.interfaces;

public interface FrameworkConcurrentEntity {
	/**
	 * In case of background operations, whether there is a lock on this object or not
	 * @return true if no other operations are being performed on this object.
	 */
	public boolean isAvailable();
	
	/**
	 * In case of background operations, set whether this object is available for use,
	 * or whether is is to be considered in preparation
	 * @param isAvailable
	 */
	public void setIsAvailble(boolean isAvailable);
}
