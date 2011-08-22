package net.gedzis.blogeriunaujienos.common;

import net.gedzis.blogeriunaujienos.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class BaseActions {
	public boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null;
	}

	public void showNoInternetAllertBox(Context context) {
		new AlertDialog.Builder(context).setMessage(
				R.string.error_no_internet_connection).setTitle(
				R.string.alert_dialog_title).setCancelable(true)
				.setNeutralButton(android.R.string.cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
							}
						}).show();
	}
}
