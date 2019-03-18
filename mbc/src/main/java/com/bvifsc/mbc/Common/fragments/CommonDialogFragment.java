package com.bvifsc.mbc.Common.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bvifsc.core.R;
import com.bvifsc.core.di.BVIRA_Application;

import java.io.FileDescriptor;
import java.io.IOException;

public class CommonDialogFragment extends DialogFragment {



    public CommonDialogFragment() {
        // Required empty public constructor
    }

    public static CommonDialogFragment newInstance(Bundle args) {


        CommonDialogFragment fragment = new CommonDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        injectFragment();
       boolean imageViewDialog =getArguments().getBoolean("imageViewDialog",false);
       boolean progressDialog = getArguments().getBoolean("progressDialog",false);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        if(imageViewDialog)
        {
            Uri uri = getArguments().getParcelable("kycImagePath");
            Bitmap kycDoc1Bitmap=getArguments().getParcelable("kycBitmap");
              View view =LayoutInflater.from(getActivity()).inflate(R.layout.ra_mbc_kyc_document_view, null);
             alertDialogBuilder.setView(view);
             view.findViewById(R.id.kyc_doc_close).setOnClickListener(view1 -> {
                 Log.d("@MBC", "alerDialog dismiss");
                 dismiss();
             });

            //Bitmap kycDoc1Bitmap= null;
            //try {

              //  kycDoc1Bitmap = getBitmapFromUri(uri);
            //} catch (IOException e) {
              //  e.printStackTrace();
            //}
            ImageView imageView=view.findViewById(R.id.kyc_image);
            imageView.setImageBitmap(kycDoc1Bitmap);


        }

        if(progressDialog)
        { alertDialogBuilder = new AlertDialog.Builder(getActivity());
            alertDialogBuilder.setView(R.layout.ra_progress_bar);}

           AlertDialog alertDialog = alertDialogBuilder.create();

        return  alertDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    void injectFragment()
    {
        BVIRA_Application.getObjectGraph(getActivity().getApplicationContext()).inject(this);

    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getActivity().getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }





    /**
 * This interface must be implemented by activities that contain this
 * fragment to allow an interaction in this fragment to be communicated
 * to the activity and potentially other fragments contained in that
 * activity.
 * <p>
 * See the Android Training lesson <a href=
 * "http://developer.android.com/training/basics/fragments/communicating.html"
 * >Communicating with Other Fragments</a> for more information.
 */

}
