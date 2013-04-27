/**
 * MemoryEntityInterface.java
 * An entity represented in memory (can be copied or deleted)
 * Copyright 2007 Andrea Splendiani
 * This software is released under LGPL
 * 
 * TODO rename ? 
 * 
 */
package types.interfaces;


public interface FrameworkInformationEntity extends FrameworkEntity {
	
	/**
	 * @deprecated
	 * Add a note to the description of the object: only the last note added is kept in memory.
	 * @param note
	 */
	
	public void addNote(String note);
	/**
	 * creates a copy of this object in db or files. The current object is not deleted.
	 * If the object is already in db, generates a new db object
	 * @return a new object implemented in db.
	 */
	

	public void setName(String name);
	
	/**
     * @return false if the object is volatile (in memory), true otherwise. Non-volatile objects live beyond the application.
	 * 
	 */
	public boolean isPermanent();  

	/**
	 * makes this item persistent in a database-backend
	 */
	public boolean makePersistent();
	/**
	 * delete this object (release all resources it uses)
	 */
	public  void delete();
	
	/**
	 * returns a copy of this object in memory
	 */
	public FrameworkInformationEntity getCopyInMemory();
	
	

	 
	
}
