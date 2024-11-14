package net.scorgister.tam;

import java.io.IOException;

import net.argus.util.debug.Debug;

public class MainTam {

	public static void main(String[] args) throws IOException {
		NetworkSession session = Loader.loadDatas();
		Debug.log("session created");
		MergeData.merge(session);
		Debug.log("Data merged");
		MergeData.createStop(session);
		Debug.log("Stops created");
	}

}
