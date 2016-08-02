package com.sherwin.polling;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.pi.service.ProcessService;
import com.pi.service.ProcessServiceImpl;

public class PollingClientUsage {
	

	public static void main(String[] args) throws Exception {

		PollingClient pc = new PollingClient("dev", "PhysicalInventory");
		String fname ="PollingFile.xml";
		File f = new File(fname);
		List<String> storesList = new ArrayList<String>();
		storesList.add("702906");
		pc.postToStores(storesList, "LIST");
		pc.write(f);
		String result = pc.postMaintenance();
		System.out.println("Request_id Created :" +  result);
	}

}
