package uk.gov.dwp.esf.mi.common;

 public enum Ethnicity {
	
	
	WHITE_ENGLISH(White.ENGLISH), WHITE_WELSH(White.WELSH), WHITE_SCOTTISH(White.SCOTTISH), WHITE_NORTHERN_IRISH(White.NORTHERS_IRISH), WHITE_BRITISH(White.BRITISH), WHITE_IRISH(White.IRISH), 
	WHITE_GYPSY(White.GYPSY),WHITE_IRISH_TRAVELLER(White.IRISH_TRAVELLER),WHITE_OTHER(White.OTHER),
	MIXED_WHITE_BLACK_CARIBBEAN,MIXED_WHITE_BLACK_AFRICAN, WHITE_ASIAN, OTHER_ASIAN, BRITISH_INDIAN(British.INDIAN_ASIAN), BRITISH_PAKISTANI(British.PAKISTANI_ASIAN),BRITISH_BANGLADESI(British.BANGLADESI_ASIAN),
	BRITISH_CHINESE_ASIAN(British.CHINESE_ASIAN), BRITISH_OTHER_BLACK(British.OTHER_BLACK), BRITISH_AFRICAN_BLACK(British.AFRICAN_BLACK), BRITISH_CARIBBEAN_BLACK(British.CARIBBEAN_BLACK), 
	BRITISH_OTHER, OTHER_ETHNIC_ARAB,OTHER_ETHNIC;
	 	
	private Ethnicity(){}
	 
    Ethnicity(Enum<?>  e) {
	}
 
}
 /*White - English/Welsh/Scottish/Northern Irish/British
 White - Irish
 White - Gypsy or Irish Traveller
 White - Other
 Mixed/Multiple - White and Black Caribbean
 Mixed/Multiple - White and Black African
 Mixed/Multiple - White and Asian
 Mixed/Multiple - Other Asian/Asian
 British  - Indian Asian/Asian
 British  - Pakistani Asian/Asian
 British  - Bangladeshi Asian/Asian
 British  - Chinese Asian/Asian
 British  - Other Black/ African/Caribbean/Black
 British - African Black/ African/Caribbean/Black
 British - Caribbean Black/ African/Caribbean/Black
 British - Other
 Other ethnic group - Arab
 Other ethnic group - Other*/