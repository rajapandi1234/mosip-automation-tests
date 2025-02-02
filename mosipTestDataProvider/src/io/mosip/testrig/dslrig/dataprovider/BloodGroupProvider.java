package io.mosip.testrig.dslrig.dataprovider;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import io.mosip.testrig.dslrig.dataprovider.models.DynamicFieldModel;
import io.mosip.testrig.dslrig.dataprovider.models.DynamicFieldValueModel;

public class BloodGroupProvider {
	private static Random rand = new Random();
	//static String [] bloodGroups = { "A+","A-","B+","B-","O+","O-","AB+","AB-"};
	
	//generate language specific blood group data
	public static Hashtable<String,List<DynamicFieldValueModel>> generate(int count, Hashtable<String, List<DynamicFieldModel>> dynaFields) {
		
		Hashtable<String,List<DynamicFieldValueModel>> tblBG = new Hashtable<String,List<DynamicFieldValueModel>>();
		
		Iterator<String> keyLangs = dynaFields.keys().asIterator();
		while(keyLangs.hasNext()) {
			
			DynamicFieldModel bgModel = null;
			List<DynamicFieldValueModel> bgs =null;
			String key = keyLangs.next();
			for(DynamicFieldModel fm: dynaFields.get(key)) {
				if(fm.getName().contains("blood")) {
					bgModel = fm;
					break;
				}
					
			}
			if(bgModel !=null && bgModel.getFieldVal().size()>=count) {
				bgs = new ArrayList<DynamicFieldValueModel>();
				//Random rand = new Random();
				for( int i=0; i < count; i++) {
					
					int idx = rand.nextInt(bgModel.getFieldVal().size());
					bgs.add( bgModel.getFieldVal().get(idx));
				}
				tblBG.put(key,  bgs);
			}
		}
		return tblBG;
	}
}
