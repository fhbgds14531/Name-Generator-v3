package net.mizobo.namegen;

import net.mizobo.namegen.util.RomanNumeral;
import net.mizobo.namegen.util.Util;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class NG {

	private static SecureRandom rand = new SecureRandom();

	private List<EnumSyllable> maleSyllablesStartEarly;
	private List<EnumSyllable> maleSyllablesMiddle;
	private List<EnumSyllable> maleSyllablesLateEnd;

	private List<EnumSyllable> femaleSyllablesStartEarly;
	private List<EnumSyllable> femaleSyllablesMiddle;
	private List<EnumSyllable> femaleSyllablesLateEnd;

	NG(){
		List<EnumSyllable> maleSyllableList = EnumSyllable.getFilteredSyllablesSex(EnumSex.MALE);
		maleSyllablesStartEarly = maleSyllableList.stream()
				.filter(enumSyllable -> enumSyllable.getPartOfWord().contains(EnumSyllable.EnumWordPart.START_ONLY) || enumSyllable.getPartOfWord().contains(EnumSyllable.EnumWordPart.EARLY) || enumSyllable.getPartOfWord().contains(EnumSyllable.EnumWordPart.ALL))
				.collect(Collectors.toList());
		maleSyllablesMiddle = maleSyllableList.stream()
				.filter(enumSyllable -> enumSyllable.getPartOfWord().contains(EnumSyllable.EnumWordPart.EARLY) || enumSyllable.getPartOfWord().contains(EnumSyllable.EnumWordPart.MIDDLE) || enumSyllable.getPartOfWord().contains(EnumSyllable.EnumWordPart.LATE) || enumSyllable.getPartOfWord().contains(EnumSyllable.EnumWordPart.ALL))
				.collect(Collectors.toList());
		maleSyllablesLateEnd = maleSyllableList.stream()
				.filter(enumSyllable -> enumSyllable.getPartOfWord().contains(EnumSyllable.EnumWordPart.LATE) || enumSyllable.getPartOfWord().contains(EnumSyllable.EnumWordPart.END_ONLY) || enumSyllable.getPartOfWord().contains(EnumSyllable.EnumWordPart.ALL))
				.collect(Collectors.toList());

		List<EnumSyllable> femaleSyllableList = EnumSyllable.getFilteredSyllablesSex(EnumSex.FEMALE);
		femaleSyllablesStartEarly = femaleSyllableList.stream()
				.filter(enumSyllable -> enumSyllable.getPartOfWord().contains(EnumSyllable.EnumWordPart.START_ONLY) || enumSyllable.getPartOfWord().contains(EnumSyllable.EnumWordPart.EARLY) || enumSyllable.getPartOfWord().contains(EnumSyllable.EnumWordPart.ALL))
				.collect(Collectors.toList());
		femaleSyllablesMiddle = femaleSyllableList.stream()
				.filter(enumSyllable -> enumSyllable.getPartOfWord().contains(EnumSyllable.EnumWordPart.EARLY) || enumSyllable.getPartOfWord().contains(EnumSyllable.EnumWordPart.MIDDLE) || enumSyllable.getPartOfWord().contains(EnumSyllable.EnumWordPart.LATE) || enumSyllable.getPartOfWord().contains(EnumSyllable.EnumWordPart.ALL))
				.collect(Collectors.toList());
		femaleSyllablesLateEnd = femaleSyllableList.stream()
				.filter(enumSyllable -> enumSyllable.getPartOfWord().contains(EnumSyllable.EnumWordPart.LATE) || enumSyllable.getPartOfWord().contains(EnumSyllable.EnumWordPart.END_ONLY) || enumSyllable.getPartOfWord().contains(EnumSyllable.EnumWordPart.ALL))
				.collect(Collectors.toList());
	}

	String generateSexedName(EnumSex sex){
		switch (sex){
			case MALE:
				return Util.capitalize(generateMaleName());
			case FEMALE:
				return Util.capitalize(generateFemaleName());
			case NO_PREFERENCE:
				return Util.capitalize(rand.nextBoolean() ? generateMaleName() : generateFemaleName());
			default:
				return "";
		}
	}

	private String generateMaleName(){
		float uniqueSyllableChance = 0.85f;
		float nicknameChance = 0.05f;
		float lastNameChance = 0.975f;
		float preOrSuffixChance = 0.05f;

		List<EnumNickname> maleNicknames = Arrays.stream(EnumNickname.values()).filter(entry -> entry.getSex().equals(EnumSex.MALE) || entry.getSex().equals(EnumSex.NO_PREFERENCE)).collect(Collectors.toList());

		String first = genMaleFirstName(uniqueSyllableChance);
		String middle = rand.nextFloat() < nicknameChance ? " \"" + maleNicknames.get(rand.nextInt(maleNicknames.size())).getName() + "\" " : "";
		String last = rand.nextFloat() < lastNameChance ? genMaleLastName(uniqueSyllableChance, 0.05f, 0.75f) : "";

		String prefix = rand.nextFloat() < preOrSuffixChance ?
				(rand.nextBoolean() ? (rand.nextBoolean() ? "Lord " : "Sir ") : (rand.nextBoolean() ? "Brother " : "Father ")) : "";
		String suffix = rand.nextFloat() < preOrSuffixChance ?
				(rand.nextBoolean() ? (rand.nextBoolean() ? " Jr." : " Sr.") : (rand.nextBoolean() ? " " + RomanNumeral.toRoman(rand.nextInt(13) + 2) : " the Brave")) : "";

		return prefix +  Util.capitalize(first) + (middle.isEmpty() ? " " : " " + Util.capitalize(middle) + " ") + Util.capitalize(last) + suffix;
	}

	private String genMaleFirstName(float uniqueSyllableChance) {
		//Get random start point
		String name = getRandomSyllableFromList(maleSyllablesStartEarly);

		//populate the middle of the name
		if (rand.nextFloat() < 0.9) {
			name += getRandomSyllableFromListWeighted(maleSyllablesMiddle, name, uniqueSyllableChance);
		}
		boolean apostrophe = false;
		if (rand.nextFloat() < 0.2) {
			if (rand.nextFloat() < 0.15) {
				name += "\'";
				apostrophe = true;
			}
			name += getRandomSyllableFromListWeighted(maleSyllablesMiddle, name, uniqueSyllableChance);
		}
		if (rand.nextFloat() < 0.05) {
			if (rand.nextFloat() < 0.1 && !apostrophe) {
				name += "\'";
			}
			name += getRandomSyllableFromListWeighted(maleSyllablesMiddle, name, uniqueSyllableChance);
		}
		if (rand.nextFloat() < 0.01) {
			if (rand.nextFloat() < 0.2 && !apostrophe) {
				name += "\'";
			}
			name += getRandomSyllableFromListWeighted(maleSyllablesMiddle, name, uniqueSyllableChance);
		}

		//finish it off with this
		if (rand.nextFloat() < 0.9) {
			name += getRandomSyllableFromListWeighted(maleSyllablesLateEnd, name, uniqueSyllableChance);
		}

		return name;
	}

	private String genMaleLastName(float uniqueSyllableChance, float prefixChance, float suffixChance) {
		String name = "";

		if (rand.nextFloat() < prefixChance) {
			name += "o\'";
		}
		//Get random start point
		name += getRandomSyllableFromList(maleSyllablesStartEarly);

		//populate the middle of the name
		if (rand.nextFloat() < 0.9) {
			name += getRandomSyllableFromListWeighted(maleSyllablesMiddle, name, uniqueSyllableChance);
		}
		if (rand.nextFloat() < 0.2) {
			name += getRandomSyllableFromListWeighted(maleSyllablesMiddle, name, uniqueSyllableChance);
		}

		//finish it off with this
		if (rand.nextFloat() < 0.9) {
			name += getRandomSyllableFromListWeighted(maleSyllablesLateEnd, name, uniqueSyllableChance);
		}

		if (rand.nextFloat() < suffixChance) {
			name += Arrays.asList(EnumLastNameSuffix.values()).get(rand.nextInt(EnumLastNameSuffix.values().length));
		}

		return name;
	}

	private String generateFemaleName(){
		float uniqueSyllableChance = 0.85f;
		float nicknameChance = 0.05f;
		float lastNameChance = 0.975f;
		float preOrSuffixChance = 0.05f;

		List<EnumNickname> femaleNicknames = Arrays.stream(EnumNickname.values()).filter(entry -> entry.getSex().equals(EnumSex.FEMALE) || entry.getSex().equals(EnumSex.NO_PREFERENCE)).collect(Collectors.toList());

		String first = genFemaleFirstName(uniqueSyllableChance);
		String middle = rand.nextFloat() < nicknameChance ? " \"" + femaleNicknames.get(rand.nextInt(femaleNicknames.size())).getName() + "\" " : "";
		String last = rand.nextFloat() < lastNameChance ? genFemaleLastName(uniqueSyllableChance, 0.05f, 0.75f) : "";

		String prefix = rand.nextFloat() < preOrSuffixChance ?
				(rand.nextBoolean() ? (rand.nextBoolean() ? "Lady " : "Madame ") : (rand.nextBoolean() ? "Sister " : "Mother ")) : "";
		String suffix = rand.nextFloat() < preOrSuffixChance ?
				(rand.nextBoolean() ? (rand.nextBoolean() ? " Jr." : " Sr.") : (rand.nextBoolean() ? " " + RomanNumeral.toRoman(rand.nextInt(13) + 2) : " the Brave")) : "";

		return prefix +  Util.capitalize(first) + (middle.isEmpty() ? " " : " " + Util.capitalize(middle) + " ") + Util.capitalize(last) + suffix;
	}

	private String genFemaleFirstName(float uniqueSyllableChance) {
		//Get random start point
		String name = getRandomSyllableFromList(femaleSyllablesStartEarly);

		//populate the middle of the name
		if (rand.nextFloat() < 0.9) {
			name += getRandomSyllableFromListWeighted(femaleSyllablesMiddle, name, uniqueSyllableChance);
		}
		boolean apostrophe = false;
		if (rand.nextFloat() < 0.2) {
			if (rand.nextFloat() < 0.15) {
				name += "\'";
				apostrophe = true;
			}
			name += getRandomSyllableFromListWeighted(femaleSyllablesMiddle, name, uniqueSyllableChance);
		}
		if (rand.nextFloat() < 0.05) {
			if (rand.nextFloat() < 0.1 && !apostrophe) {
				name += "\'";
			}
			name += getRandomSyllableFromListWeighted(femaleSyllablesMiddle, name, uniqueSyllableChance);
		}
		if (rand.nextFloat() < 0.01) {
			if (rand.nextFloat() < 0.2 && !apostrophe) {
				name += "\'";
			}
			name += getRandomSyllableFromListWeighted(femaleSyllablesMiddle, name, uniqueSyllableChance);
		}

		//finish it off with this
		if (rand.nextFloat() < 0.9) {
			name += getRandomSyllableFromListWeighted(femaleSyllablesLateEnd, name, uniqueSyllableChance);
		}

		return name;
	}

	private String genFemaleLastName(float uniqueSyllableChance, float prefixChance, float suffixChance){
		String name = "";

		if (rand.nextFloat() < prefixChance) {
			name += "o\'";
		}
		//Get random start point
		name += getRandomSyllableFromList(femaleSyllablesStartEarly);

		//populate the middle of the name
		if (rand.nextFloat() < 0.9) {
			name += getRandomSyllableFromListWeighted(femaleSyllablesMiddle, name, uniqueSyllableChance);
		}
		if (rand.nextFloat() < 0.2) {
			name += getRandomSyllableFromListWeighted(femaleSyllablesMiddle, name, uniqueSyllableChance);
		}

		//finish it off with this
		if (rand.nextFloat() < 0.9) {
			name += getRandomSyllableFromListWeighted(femaleSyllablesLateEnd, name, uniqueSyllableChance);
		}

		if (rand.nextFloat() < suffixChance) {
			name += Arrays.asList(EnumLastNameSuffix.values()).get(rand.nextInt(EnumLastNameSuffix.values().length));
		}

		return name;
	}

	private static String getRandomSyllableFromList(List<EnumSyllable> syllables){
		return syllables.get(rand.nextInt(syllables.size())).name().toLowerCase();
	}

	private static String getRandomSyllableFromListWeighted(List<EnumSyllable> syllables, String nameSoFar, float weight){
		String syllable = syllables.get(rand.nextInt(syllables.size())).name().toLowerCase();
		if(rand.nextFloat() < weight) {
			int count = 0;
			while (nameSoFar.contains(syllable) && count < syllables.size() * 2) {
				syllable = syllables.get(rand.nextInt(syllables.size())).name().toLowerCase();
				count++;
			}
		}
		return  syllable;
	}
}
