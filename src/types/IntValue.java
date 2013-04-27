/**
 * IntValue.java
 * A number
 * Copyright 2007 Andrea Splendiani
 * This software is released under LGPL
 * TODO rename ? They are not int, they are long!
 * 
 */
package types;

import types.interfaces.FrameworkInformationEntity;

public class IntValue extends FrameworkInformationEntityImpl implements Value  {
	long myValue=0;
	
	
	public IntValue(long i) {
		super();
		myValue=i;
		type=FrameworkTypes.INTVALUE;
	}
	private long getValue() {
		return myValue;
	}
	public boolean testEqualTo(Value reference) throws WrongType {
		try {
			IntValue compValue=(IntValue)reference;
			return (myValue==compValue.getValue());
		} catch (Exception e) {
			throw new WrongType(reference.getType()+" instead of "+"intvalue");
		}
		
		
	}

	

	public boolean testMajorThan(Value reference) throws WrongType {
		try {
			IntValue compValue=(IntValue)reference;
			return (myValue>compValue.getValue());
		} catch (Exception e) {
			throw new WrongType(reference.getType()+" instead of "+"intvalue");
		}
	}

	public boolean testMinorThan(Value reference) throws WrongType {
		try {
			IntValue compValue=(IntValue)reference;
			return (myValue<compValue.getValue());
		} catch (Exception e) {
			throw new WrongType(reference.getType()+" instead of "+"intvalue");
		}
	}

	public void addNote(String note) {
		// TODO Auto-generated method stub
		
	}

	public void delete() {
		
		
	}

	public FrameworkInformationEntity getCopyInMemory() {
		return new IntValue(myValue);
	}

	public boolean isPermanent() {
		return false;
	}

	public boolean makePersistent() {
		return false;
	}

	public void setName(String name) {
		this.name=name;
		
	}

	public String getDescription() {
		String description="<Description>"+name+"<name></name>\n" +
				"<type>"+type+"</type>\n" +
				"<value>"+myValue+"</value>\n" +
				"</Description>";
		return description;
	}

	public String getName() {
		return name;
	}

	public int getType() {
		return type;
	}

	public boolean isAvailable() {
		return true;
	}

}
