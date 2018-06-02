import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

public class AboutDialogHelper {

    public static void showAboutDialog(final Context context) {
        final AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setPositiveButton("Rate Us",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                launchPlayStore(context);
                                dialog.dismiss();
                            }
                        })
                .setNegativeButton("Share",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                share(context);
                                dialog.dismiss();
                            }
                        }).create();
        alertDialog.setTitle("About Us");
        alertDialog.setMessage("Copyright Message String");
        alertDialog.show();
    }

    private static void launchPlayStore(Context context) {
        Uri uri = Uri.parse("market://details?id=" + BuildConfig.APPLICATION_ID);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(goToMarket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void share(Context context) {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "Share via...");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, "Title"));
    }
}