package net.mizobo.namegen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static net.mizobo.namegen.EnumSex.*;

public enum EnumSyllable {
	GLI(NO_PREFERENCE, EnumWordPart.START_ONLY),
	SIN(NO_PREFERENCE, EnumWordPart.START_ONLY),
	GA(NO_PREFERENCE, EnumWordPart.EARLY),
	GI(NO_PREFERENCE, EnumWordPart.EARLY),
	JA(NO_PREFERENCE, EnumWordPart.EARLY),
	JU(NO_PREFERENCE, EnumWordPart.EARLY),
	MOR(NO_PREFERENCE, EnumWordPart.EARLY),
	AE(NO_PREFERENCE, EnumWordPart.ALL),
	BE(NO_PREFERENCE, EnumWordPart.ALL),
	BU(NO_PREFERENCE, EnumWordPart.ALL),
	CH(NO_PREFERENCE, EnumWordPart.ALL),
	DE(NO_PREFERENCE, EnumWordPart.ALL),
	E(NO_PREFERENCE, EnumWordPart.ALL),
	EL(NO_PREFERENCE, EnumWordPart.ALL),
	GE(NO_PREFERENCE, EnumWordPart.ALL),
	HA(NO_PREFERENCE, EnumWordPart.ALL),
	HE(NO_PREFERENCE, EnumWordPart.ALL),
	I(NO_PREFERENCE, EnumWordPart.ALL),
	IA(NO_PREFERENCE, EnumWordPart.ALL),
	KA(NO_PREFERENCE, EnumWordPart.ALL),
	KE(NO_PREFERENCE, EnumWordPart.ALL),
	KI(NO_PREFERENCE, EnumWordPart.ALL),
	KO(NO_PREFERENCE, EnumWordPart.ALL),
	KU(NO_PREFERENCE, EnumWordPart.ALL),
	LI(NO_PREFERENCE, EnumWordPart.ALL),
	MA(NO_PREFERENCE, EnumWordPart.ALL),
	ME(NO_PREFERENCE, EnumWordPart.ALL),
	NA(NO_PREFERENCE, EnumWordPart.ALL),
	PE(NO_PREFERENCE, EnumWordPart.ALL),
	ON(NO_PREFERENCE, EnumWordPart.ALL),
	QU(NO_PREFERENCE, EnumWordPart.ALL),
	RA(NO_PREFERENCE, EnumWordPart.ALL),
	RE(NO_PREFERENCE, EnumWordPart.ALL),
	RI(NO_PREFERENCE, EnumWordPart.ALL),
	RY(NO_PREFERENCE, EnumWordPart.ALL),
	SAR(NO_PREFERENCE, EnumWordPart.ALL),
	SHU(NO_PREFERENCE, EnumWordPart.ALL),
	SWA(NO_PREFERENCE, EnumWordPart.ALL),
	THO(MALE, EnumWordPart.ALL),
	U(NO_PREFERENCE, EnumWordPart.ALL),
	VA(NO_PREFERENCE, EnumWordPart.ALL),
	VAS(NO_PREFERENCE, EnumWordPart.ALL),
	VE(NO_PREFERENCE, EnumWordPart.ALL),
	VI(NO_PREFERENCE, EnumWordPart.ALL),
	WA(NO_PREFERENCE, EnumWordPart.ALL),
	WI(NO_PREFERENCE, EnumWordPart.ALL),
	VSWA(NO_PREFERENCE, EnumWordPart.MIDDLE),
	KKA(NO_PREFERENCE, EnumWordPart.LATE),
	KKE(NO_PREFERENCE, EnumWordPart.LATE),
	KKI(NO_PREFERENCE, EnumWordPart.LATE),
	KKO(FEMALE, EnumWordPart.LATE),
	KKU(NO_PREFERENCE, EnumWordPart.LATE),
	RRA(NO_PREFERENCE, EnumWordPart.LATE),
	RRE(NO_PREFERENCE, EnumWordPart.LATE),
	AN(NO_PREFERENCE, EnumWordPart.LATE),
	CE(NO_PREFERENCE, EnumWordPart.LATE),
	CK(NO_PREFERENCE, EnumWordPart.LATE),
	A(FEMALE, EnumWordPart.END_ONLY),
	C(NO_PREFERENCE, EnumWordPart.END_ONLY),
	LL(NO_PREFERENCE, EnumWordPart.END_ONLY),
	MBLE(NO_PREFERENCE, EnumWordPart.END_ONLY),
	N(NO_PREFERENCE, EnumWordPart.END_ONLY),
	PNIL(NO_PREFERENCE, EnumWordPart.END_ONLY),
	R(MALE, EnumWordPart.END_ONLY),
	RYN(NO_PREFERENCE, EnumWordPart.END_ONLY),
	S(NO_PREFERENCE, EnumWordPart.END_ONLY),
	TIN(NO_PREFERENCE, EnumWordPart.START_ONLY, EnumWordPart.END_ONLY),
	TH(NO_PREFERENCE, EnumWordPart.END_ONLY),
	X(NO_PREFERENCE, EnumWordPart.END_ONLY);

	private EnumSex appropriateSex;
	private List<EnumWordPart> partOfWord;

	EnumSyllable(EnumSex sex, EnumWordPart... parts){
		appropriateSex = sex;
		if(parts.length < 1) {
			partOfWord = new ArrayList<>();
			partOfWord.add(EnumWordPart.MIDDLE);
		} else {
			partOfWord = Arrays.asList(parts);
		}
	}

	public EnumSex getAppropriateSex(){
		return appropriateSex;
	}

	public List<EnumWordPart> getPartOfWord(){
		return partOfWord;
	}

	public static List<EnumSyllable> getFilteredSyllablesSex(EnumSex sex){
		return Arrays.stream(EnumSyllable.values()).filter(entry -> entry.getAppropriateSex().equals(sex) || entry.getAppropriateSex().equals(NO_PREFERENCE)).collect(Collectors.toList());
	}

	public static List<EnumSyllable> getFilteredSyllablesWordPart(EnumWordPart part){
		return Arrays.stream(EnumSyllable.values()).filter(entry -> entry.getPartOfWord().contains(part) || entry.getPartOfWord().contains(EnumWordPart.ALL)).collect(Collectors.toList());
	}

	public static List<EnumSyllable> getFilteredSyllablesSexAndPart(EnumSex sex, EnumWordPart part){
		return Arrays.stream(EnumSyllable.values())
				.filter(entry -> entry.getPartOfWord().contains(part) || entry.getPartOfWord().contains(EnumWordPart.ALL))
				.filter(entry -> entry.getAppropriateSex().equals(sex) || entry.getAppropriateSex().equals(NO_PREFERENCE))
				.collect(Collectors.toList());
	}

	enum EnumWordPart{
		START_ONLY,
		EARLY,
		MIDDLE,
		LATE,
		END_ONLY,
		ALL
	}
}
