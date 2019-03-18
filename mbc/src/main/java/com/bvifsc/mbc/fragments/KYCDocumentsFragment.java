package com.bvifsc.mbc.fragments;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.bvifsc.core.R;
import com.bvifsc.core.di.BVIRA_Application;
import com.bvifsc.core.fragments.BaseFragment;
import com.bvifsc.core.models.response.Action;
import com.bvifsc.core.models.response.PageResponseModel;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.Common.fragments.CommonDialogFragment;
import com.bvifsc.mbc.adapters.KYCDocsSpinnerAdapter;
import com.bvifsc.mbc.datamodel.KYC;
import com.bvifsc.mbc.datamodel.KYCDocuments;
import com.bvifsc.mbc.events.OnResponseErrorEvent;
import com.bvifsc.mbc.model.KYCDocumentsPageResponseModel;
import com.bvifsc.mbc.model.kycDocuments.KYCDocumentTypeModel;
import com.bvifsc.mbc.model.kycDocuments.KYCDocumentsListModel;
import com.bvifsc.mbc.model.UserAuthInfo;
import com.bvifsc.mbc.presenters.KYCDocumentsPresenter;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static android.app.Activity.RESULT_OK;


public class KYCDocumentsFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG= KYCDocumentsFragment.class.getName() + "  ";
    private static final int KYC_DOC_1_REQUEST=0;
    private static final int KYC_DOC_2_REQUEST=1;
    private static final int KYC_DOC_3_REQUEST=2;
    private static final  String DEFAULT_FILE_NAME="No File Chosen";


    // TODO: Rename and change types of parameters

    private Bitmap kycDoc1Bitmap;
    private Bitmap kycDoc2Bitmap;
    private Bitmap kycDoc3Bitmap;
    PageResponseModel kycDocsPageModel;
    KYCDocumentsListModel kycDocumentsListModel;
    List<KYC> kyc3docsDetails;
    TextView kycPageHeader;
    Spinner kycDoc1List;
    Spinner kycDoc2List;
    Spinner kycDoc3List;

    Button selectDoc1;
    Button selectDoc2;
    Button selectDoc3;

    TextView doc1View;
    TextView doc2View;
    TextView doc3View;

    Button submit;
    Button cancel;
    KYCDocuments kycDocuments;

    TextView kycDoc1RAComment;
    TextView kycDoc2RAComment;
    TextView kycDoc3RAComment;
    boolean duplicateDocType=false;

    @Inject
    KYCDocumentsPresenter kycDocumentsPresenter;

    @Inject
    UserAuthInfo userAuthInfo;


    public KYCDocumentsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static KYCDocumentsFragment newInstance(KYCDocumentsPageResponseModel kycDocumentsPageResponseModel) {
        KYCDocumentsFragment fragment = new KYCDocumentsFragment();
        Bundle args = new Bundle();
        args.putParcelable(MBC_Constants.KYC_DOCUMENTS, kycDocumentsPageResponseModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            KYCDocumentsPageResponseModel kycDocumentsPageResponseModel = getArguments().getParcelable(MBC_Constants.KYC_DOCUMENTS);
            if(kycDocumentsPageResponseModel!= null)
            {
                 kycDocsPageModel= kycDocumentsPageResponseModel.getPageInfo();
                 kycDocumentsListModel=kycDocumentsPageResponseModel.getKycDocumentsListModel();
                 kyc3docsDetails=kycDocumentsPageResponseModel.getKycDocsDetails();


            }

        }
    }

    @Override
    protected void injectFragment() {
        BVIRA_Application.getObjectGraph(getActivity().getApplicationContext()).inject(this);

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_mbc_kycdocuemnts;
    }

    @Override
    protected void initFragment(View rootView) {
        if(kycDocsPageModel != null && kycDocumentsListModel != null)
        {

            setUpHeaderAndTitle(rootView);
            setUpToSelectThreeDifferentTypeOfKYCDocs(rootView);
            setUpKYCDocumentPageActions(rootView);
            setUpToSetRACommentsOnKycDocuments(rootView);

        }

    }

    void setUpHeaderAndTitle(View rootView)
    {
     kycPageHeader = rootView.findViewById(R.id.kyc_documents_header);
     kycPageHeader.setText(kycDocsPageModel.getTitle());

    }


    void setUpToSelectThreeDifferentTypeOfKYCDocs(View rootView)
    {
         kycDoc1List=rootView.findViewById(R.id.kyc_doc1_spinner);
         kycDoc1List.setTag(KYC_DOC_1_REQUEST);
         kycDoc2List=rootView.findViewById(R.id.kyc_doc2_spinner);
         kycDoc2List.setTag(KYC_DOC_2_REQUEST);
         kycDoc3List=rootView.findViewById(R.id.kyc_doc3_spinner);
         kycDoc3List.setTag(KYC_DOC_3_REQUEST);

        selectDoc1= rootView.findViewById(R.id.kyc_doc1_select_file);
        selectDoc2= rootView.findViewById(R.id.kyc_doc2_select_file);
        selectDoc3= rootView.findViewById(R.id.kyc_doc3_select_file);

        doc1View=rootView.findViewById(R.id.kyc_doc1_imageName);

        doc1View.setText(DEFAULT_FILE_NAME);
        doc2View=rootView.findViewById(R.id.kyc_doc2_imageName);
        doc2View.setText(DEFAULT_FILE_NAME);
        doc3View=rootView.findViewById(R.id.kyc_doc3_imageName);
        doc3View.setText(DEFAULT_FILE_NAME);

        if(kycDocumentsListModel.getKycDocTypeList() !=null) {

            setUpSpinner(kycDoc1List, kycDocumentsListModel.getKycDocTypeList(), KYC_DOC_1_REQUEST);
            setUpSpinner(kycDoc2List, kycDocumentsListModel.getKycDocTypeList(), KYC_DOC_2_REQUEST);
            setUpSpinner(kycDoc3List, kycDocumentsListModel.getKycDocTypeList(), KYC_DOC_3_REQUEST);
        }


        if(kyc3docsDetails!= null)
        {
            String doc1FileName=kyc3docsDetails.get(0).getFileData().getFileName();
            doc1View.setText(doc1FileName);
            setUpToViewFileOnTextLinkClickListener(doc1FileName,kyc3docsDetails.get(0).getFileData().getBitmap(),doc1View);

            String doc2FileName=kyc3docsDetails.get(1).getFileData().getFileName();
            doc2View.setText(doc2FileName);
            setUpToViewFileOnTextLinkClickListener(doc2FileName,kyc3docsDetails.get(1).getFileData().getBitmap(),doc2View);

            String doc3FileName=kyc3docsDetails.get(2).getFileData().getFileName();
            doc3View.setText(doc3FileName);
            setUpToViewFileOnTextLinkClickListener(doc3FileName,kyc3docsDetails.get(2).getFileData().getBitmap(),doc3View);


            kycDocuments = new KYCDocuments();
            kycDocuments.setList(kyc3docsDetails);

            int index=0;
            for(KYCDocumentTypeModel kycDocumentTypeModel : kycDocumentsListModel.getKycDocTypeList())
            {
                if(kycDocumentTypeModel.getDocumentTypeId() == kyc3docsDetails.get(0).getKycDocType())
                    kycDoc1List.setSelection(index);
                if(kycDocumentTypeModel.getDocumentTypeId() == kyc3docsDetails.get(1).getKycDocType())
                    kycDoc2List.setSelection(index);
                if(kycDocumentTypeModel.getDocumentTypeId() == kyc3docsDetails.get(2).getKycDocType())
                    kycDoc3List.setSelection(index);
                index=index+1;

            }

            if(userAuthInfo.isKycDoc1Approved())
            {
                kycDoc1List.setEnabled(false);
                selectDoc1.setEnabled(false);

            }
            if(userAuthInfo.isKycDoc2Approved())
            {
                kycDoc2List.setEnabled(false);
                selectDoc2.setEnabled(false);

            }

            if(userAuthInfo.isKycDoc3Approved())
            {
                kycDoc3List.setEnabled(false);
                selectDoc3.setEnabled(false);

            }

        }

        else {
            kycDocuments = new KYCDocuments();

            List<KYC> initializeList = new ArrayList<>();
            initializeList.add(new KYC());
            initializeList.add(new KYC());
            initializeList.add(new KYC());
            kycDocuments.setList(initializeList);
        }





      selectDoc1.setOnClickListener(selectDoc1 -> {
          chooseDocument(KYC_DOC_1_REQUEST);

      });

      selectDoc2.setOnClickListener(selectDoc2 -> {
          chooseDocument(KYC_DOC_2_REQUEST);

      });

      selectDoc3.setOnClickListener( selectDoc3 -> {
          chooseDocument(KYC_DOC_3_REQUEST);

      });

    }

    void setUpToSetRACommentsOnKycDocuments(View rootView)
    {
        Log.d("@MBC", "setUpToSetRACommentsOnKycDocuments");
        kycDoc1RAComment = rootView.findViewById(R.id.ra_comment_doc1);
        kycDoc2RAComment=rootView.findViewById(R.id.ra_comment_doc2);
        kycDoc3RAComment=rootView.findViewById(R.id.ra_comment_doc3);



        if(userAuthInfo.getKycDoc1RAComment() == null)
        {
            rootView.findViewById(R.id.ra_comment_doc1_header).setVisibility(View.GONE);
            kycDoc1RAComment.setVisibility(View.GONE);
        }
        else
         kycDoc1RAComment.setText(userAuthInfo.getKycDoc1RAComment());

        if(userAuthInfo.getKycDoc2RAComment() == null)
        {
            kycDoc2RAComment.setVisibility(View.GONE);
            rootView.findViewById(R.id.ra_comment_doc2_header).setVisibility(View.GONE);
        }
        else
            kycDoc2RAComment.setText(userAuthInfo.getKycDoc2RAComment());

        if(userAuthInfo.getKycDoc3RAcomment() == null)
        {
            rootView.findViewById(R.id.ra_comment_doc3_header).setVisibility(View.GONE);
            kycDoc3RAComment.setVisibility(View.GONE);
        }
        else
            kycDoc3RAComment.setText(userAuthInfo.getKycDoc3RAcomment());


    }
    void setUpKYCDocumentPageActions(View rootView)
    {

        submit = rootView.findViewById(R.id.primaryButton);
        cancel=rootView.findViewById(R.id.secondaryButton);

        if(kycDocsPageModel.getButtonMap() != null)
        {
            if(kycDocsPageModel.getButtonMap().get(MBC_Constants.PRIMARY_BUTTON) != null)
            {
                Action primaryAction = kycDocsPageModel.getButtonMap().get(MBC_Constants.PRIMARY_BUTTON);

                submit.setText(primaryAction.getTitle());

                submit.setOnClickListener( view -> {

                    if(areValidInput())
                    {
                        kycDocumentsPresenter.uploadKYCDocs(kycDocuments,primaryAction);

                    }
                } );


            }
            else
                submit.setVisibility(View.GONE);

            if(kycDocsPageModel.getButtonMap().get(MBC_Constants.SECONDARY_BUTTON) != null)
            {

                Action secondaryAction = kycDocsPageModel.getButtonMap().get(MBC_Constants.SECONDARY_BUTTON);

                cancel.setText(secondaryAction.getTitle());

                cancel.setOnClickListener( view -> {
                    onBackPressed();

                } );

            }

        }
        else {
            submit.setVisibility(View.GONE);
            cancel.setVisibility(View.GONE);

        }


    }



    void setUpSpinner(Spinner spinner, List<KYCDocumentTypeModel> kycDocList, int REQUEST_CODE)
    {

        SpinnerAdapter kycDocsSpinnerAdapter= new KYCDocsSpinnerAdapter(getActivity(),kycDocList);
        spinner.setAdapter(kycDocsSpinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                duplicateDocType=false;


                if(position > 0) {
                    int REQUEST_CODE = Integer.parseInt(spinner.getTag().toString());
                    int docTypeId=kycDocList.get(position).getDocumentTypeId();
                    (kycDocuments.getList().get(REQUEST_CODE)).setKycDocType(docTypeId);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    void chooseDocument(int DOC_REQUEST_CODE)
    {
        Log.d("@MBC", TAG + "Choose Document" );

        Intent documentIntent  = new Intent(Intent.ACTION_PICK);
        documentIntent.setType("image/*");
        Intent chooseFileIntent = Intent.createChooser(documentIntent,"select Document");
        startActivityForResult(chooseFileIntent,DOC_REQUEST_CODE);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("@MBC", TAG + "onActivityFor Result" );
        switch (requestCode)
        {
            case KYC_DOC_1_REQUEST:
            {
                if( resultCode == RESULT_OK) {

                    Uri docUri = data.getData();
                    String docUriString = docUri.toString();
                    File file = new File(getRealPathFromURI(docUri));
                    String fileName =file.getName();
                    Log.d("@MBC","selected file Name " + fileName + file.getAbsolutePath() + file.getAbsolutePath());
                    String filePath=file.getAbsolutePath();

                    kycDocuments.getList().get(requestCode).getFileData().setFileName(fileName);

                    try {

                        kycDoc1Bitmap=getBitmapFromUri(docUri);
                        kycDocuments.getList().get(requestCode).getFileData().setBitmap(kycDoc1Bitmap);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    setUpToViewFileOnTextLinkClickListener(fileName,kycDoc1Bitmap,doc1View);



                }


                break;
            }

            case KYC_DOC_2_REQUEST:
            {

                if(resultCode == RESULT_OK) {
                    Uri docUri = data.getData();

                    String docUriString = docUri.toString();
                    File file = new File(getRealPathFromURI(docUri));
                    String fileName = file.getName();
                    Log.d("@MBC", "selected file Name " + fileName + file.getAbsolutePath());
                    String filePath = file.getAbsolutePath();
                    kycDocuments.getList().get(requestCode).getFileData().setFileName(fileName);
                    try {
                        kycDoc2Bitmap=getBitmapFromUri(docUri);
                        kycDocuments.getList().get(requestCode).getFileData().setBitmap(getBitmapFromUri(docUri));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    setUpToViewFileOnTextLinkClickListener(fileName, kycDoc2Bitmap, doc2View);


                }


                break;

            }


            case KYC_DOC_3_REQUEST:
            {
                if(resultCode == RESULT_OK) {
                    Uri docUri = data.getData();
                    String docUriString = docUri.toString();
                    File file = new File(getRealPathFromURI(docUri));

                    String fileName = file.getName();
                    Log.d("@MBC", "selected file Name " + fileName + file.getPath());
                    String filePath = docUri.getPath();
                    kycDocuments.getList().get(requestCode).getFileData().setFileName(fileName);
                    try {
                        kycDoc3Bitmap=getBitmapFromUri(docUri);
                        kycDocuments.getList().get(requestCode).getFileData().setBitmap(kycDoc3Bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    setUpToViewFileOnTextLinkClickListener(fileName, kycDoc3Bitmap, doc3View);
                }

                break;
            }
        }
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        Log.d("@MBC","getBitmapfrom uri");
        ParcelFileDescriptor parcelFileDescriptor =
                getActivity().getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();



        return image;
    }
    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (getActivity().getApplicationContext().getContentResolver() != null) {
            Cursor cursor = getActivity().getApplicationContext().getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int dataIndex = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(dataIndex);
                cursor.close();
            }
        }
        return path;
    }

    boolean areValidInput()
    {
        boolean isValid=true;
        duplicateDocType=false;
        if(doc1View.getText().toString().equals(DEFAULT_FILE_NAME))
        {
           isValid=false;
           doc1View.setTextColor(getResources().getColor(R.color.red));

        }

        if(doc2View.getText().toString().equals(DEFAULT_FILE_NAME))
        {
            isValid=false;
            doc2View.setTextColor(getResources().getColor(R.color.red));

        }

        if(doc3View.getText().toString().equals(DEFAULT_FILE_NAME))
        {
            isValid=false;
            doc3View.setTextColor(getResources().getColor(R.color.red));

        }

        if(kycDoc1List.getPositionForView(kycDoc1List.getSelectedView()) == 0)
        {


            TextView selectedView=(TextView) kycDoc1List.getSelectedView();
            selectedView.setError(kycDocumentsListModel.getKycDocTypeList().get(0).getDocumentType());
            selectedView.setTextColor(getResources().getColor(R.color.red));
            isValid=false;


        }

        if(kycDoc2List.getPositionForView(kycDoc2List.getSelectedView()) == 0)
        {

            TextView selectedView=(TextView) kycDoc2List.getSelectedView();
            selectedView.setError(kycDocumentsListModel.getKycDocTypeList().get(0).getDocumentType());
            selectedView.setTextColor(getResources().getColor(R.color.red));
            isValid=false;


        }

        if(kycDoc3List.getPositionForView(kycDoc3List.getSelectedView()) == 0)
        {

            TextView selectedView=(TextView) kycDoc3List.getSelectedView();
            selectedView.setError(kycDocumentsListModel.getKycDocTypeList().get(0).getDocumentType());
            selectedView.setTextColor(getResources().getColor(R.color.red));
            isValid=false;


        }


            if (kycDoc2List.getPositionForView(kycDoc2List.getSelectedView())
                    == kycDoc3List.getPositionForView(kycDoc3List.getSelectedView())) {

                ((TextView) kycDoc3List.getSelectedView()).setTextColor(getResources().getColor(R.color.red));


                 duplicateDocType=true;
        //        publishBusinessError();
            }


            if (kycDoc1List.getPositionForView(kycDoc1List.getSelectedView())
                    == kycDoc3List.getPositionForView(kycDoc3List.getSelectedView())) {

                ((TextView) kycDoc1List.getSelectedView()).setTextColor(getResources().getColor(R.color.red));


                duplicateDocType=true;


            }





            if (kycDoc1List.getPositionForView(kycDoc1List.getSelectedView())
                    == kycDoc2List.getPositionForView(kycDoc2List.getSelectedView())) {

                ((TextView) kycDoc2List.getSelectedView()).setTextColor(getResources().getColor(R.color.red));

                duplicateDocType=true;
                publishBusinessError();

            }
        if(duplicateDocType) {
            isValid=false;
            publishBusinessError();
        }




return isValid;
    }

    void setUpToViewFileOnTextLinkClickListener(String fileName,Bitmap bitmap,TextView docView)
    {
        Log.d("@MBC", "setUpToViewFileOnTextLinkClickListener");
        ClickableSpan onTextLinkClickListener= new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putBoolean("imageViewDialog",true);
                //args.putParcelable("kycImagePath",filePath);
              //  args.putParcelable("kycImagePath",uri);

                    args.putParcelable("kycBitmap",bitmap);


                CommonDialogFragment commonDialogFragment=CommonDialogFragment.newInstance(args);
                commonDialogFragment.show(getActivity().getSupportFragmentManager(),"ImageViewDialog");

            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getActivity().getApplicationContext().getResources().getColor(R.color.black));
            }
        };

        Log.d("@MBC", "fileName" + fileName);
        linkifyText(docView,fileName,onTextLinkClickListener);
        Log.d("@MBC", "fileName" + fileName);

    }

    void linkifyText(TextView view, String text, ClickableSpan onTextLinkClickListener)
    {
        SpannableString spannableString = new SpannableString(text);
        int matcherStart = 0;
        int matcherEnd=text.length();

        spannableString.setSpan(onTextLinkClickListener, matcherStart, matcherEnd , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setText(spannableString);
        view.setMovementMethod(LinkMovementMethod.getInstance());
    }

    void publishBusinessError()
    {
        OnResponseErrorEvent onResponseErrorEvent = new OnResponseErrorEvent();
        onResponseErrorEvent.setErrorMessage("Duplicate Document Type");
        onResponseErrorEvent.setMessageStyle("top");
            kycDocumentsPresenter.publishBusinessError(onResponseErrorEvent);
    }
    public void onBackPressed() {

        this.getActivity().getSupportFragmentManager().popBackStackImmediate();


    }

}
