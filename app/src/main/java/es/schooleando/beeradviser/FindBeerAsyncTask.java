package es.schooleando.beeradviser;

import android.os.AsyncTask;

/**
 * Created by ruben on 17/10/16.
 */

public class FindBeerAsyncTask extends AsyncTask<Integer, Void, Void> {

    @Override
    protected Void doInBackground(Integer... params) {
        try {
            Thread.sleep(params[0]);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}