package net.mizobo.namegen;

import static net.mizobo.namegen.EnumSex.*;

public enum EnumNickname {
	LadyKiller(		NO_PREFERENCE,  "Lady Killer"	  	),
	TheHammer(		NO_PREFERENCE,  "The Hammer"	  	),
	Tiptoes(		NO_PREFERENCE,  "Tiptoes"		  	),
	Danger(			NO_PREFERENCE,  "Danger"		  	),
	TheMountain(	MALE,			"The Mountain"  	),
	TheRock(		MALE, 			"The Rock"	  	),
	ShadowWalker(	NO_PREFERENCE,  "Shadow Walker"	),
	SilverTongue(	NO_PREFERENCE,  "Silver-Tongue" 	),
	StickyFingers(	NO_PREFERENCE,  "Sticky-Fingers"	),
	GreenThumb(		NO_PREFERENCE,  "Green Thumb"		),
	Moonman(		MALE, 			"Moon Man"		),
	Tiny(			NO_PREFERENCE,  "Tiny"			),
	Siren(			FEMALE, 		"The Siren"		),
	Biggie(			NO_PREFERENCE, 	"Biggie"			),
	Maneater(		NO_PREFERENCE, 	"Man-Eater"		),
	Hotlips(		NO_PREFERENCE, 	"Hotlips"			);

	private EnumSex sex;
	private String name;

	EnumNickname(EnumSex sex, String name){
		this.sex = sex;
		this.name = name;
	}

	public EnumSex getSex(){
		return sex;
	}

	public String getName(){
		return name;
	}

}
