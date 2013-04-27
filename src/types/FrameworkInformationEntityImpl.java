package types;

import types.interfaces.FrameworkInformationEntity;

public abstract class FrameworkInformationEntityImpl implements FrameworkInformationEntity{
	protected String name;
	protected String note;
	protected String descriptionText;
	protected int type;
	protected boolean isVolatile;
	
	
	
	
	
	public FrameworkInformationEntityImpl() {
		super();
		name=new String();
		note=new String();
		descriptionText=new String();
		type=FrameworkTypes.ABSTRACT;
		isVolatile=true;
		
		
		
	}


	public void addNote(String note) {
		this.note=note;
		
	}

	
	public String getName() {
		return name;
	}


	
	public int getType() {
		return this.type;
	}

	
	public boolean isPermanent() {
		return !isVolatile;
	}

	
	public void setName(String name) {
		this.name=name;
		
	}

	public String getDescription() {
		String description="<description>";
		description+=getSpecificDescription();
		description+="</description>";
		return description;
	}
	
	protected String getSpecificDescription() {
		String description=new String();
		description+="<name>"+name+"</name>\n";
		description+="<descriptionText>"+descriptionText+"</descriptionText>\n";
		description+="<type>"+FrameworkTypes.translate(type)+"</type>\n";
		description+="<note>"+note+"</note>\n";
		description+="<isVolatile>"+isVolatile+"</isVolatile>\n";
		
		return description;
	}


	
}
