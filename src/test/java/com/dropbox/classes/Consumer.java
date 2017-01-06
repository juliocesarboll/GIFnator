package com.dropbox.classes;

import java.io.IOException;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;

public class Consumer {

	private static final String ACCESS_TOKEN = "UhJIWAS1WQoAAAAAAAAAsN-a4-YxIxO0OqyEbP0lAge2LI2bCJaChd_X2BZji1cF";

	public static void main(String args[]) throws DbxException, IOException {
		// Create Dropbox client
		DbxRequestConfig config = new DbxRequestConfig("GIFnator");
		DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
		
		// Get current account info
		FullAccount account = client.users().getCurrentAccount();
		System.out.println(account.getName().getDisplayName());
/*
		// Get files and folder metadata from Dropbox root directory
		ListFolderResult result = client.files().listFolder("");
		
		
		while (true) {
			for (Metadata metadata : result.getEntries()) {
				System.out.println(metadata.getPathLower());
				
			}

			if (!result.getHasMore()) {
				break;
			}
			result = client.files().listFolderContinue(result.getCursor());
		}

		// Upload "test.txt" to Dropbox
		try (InputStream in = new FileInputStream("./Files/txt.txt")) {
			// FileMetadata metadata =
			// client.files().uploadBuilder("/test.txt").uploadAndFinish(in);
			client.files().uploadBuilder("/TEST2.txt").uploadAndFinish(in);
			in.close();
		}

		// Download "test.txt" from Dropbox
		try (OutputStream out = new FileOutputStream("./Files/txtD2.txt")) {
			// FileMetadata metadata =
			// client.files().downloadBuilder("/test.txt").download(out);
			client.files().downloadBuilder("/TEST2.txt").download(out);
			out.close();
		}
		
		
		// Guarantee share to a folder
		System.out.println(client.sharing().shareFolder("/teste2.txt"));

		// Guarantee share to a file
		SharedLinkMetadata sharedLinkMetadata = client.sharing().createSharedLinkWithSettings("/test1.0.gif");
		System.out.println(sharedLinkMetadata.getUrl());
		
		// Revoke shared link (file)
		client.sharing().revokeSharedLink("https://www.dropbox.com/s/dz5ugdoti2h8842/test0.1.gif?raw=1");
		System.out.println("done");
	    

		
		
		*/
	}
}
