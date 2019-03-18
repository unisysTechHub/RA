package com.bvifsc.mbc.network.request;

import android.graphics.Bitmap;
import android.util.Base64;
import android.util.JsonWriter;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ramesh on 24-12-2018.
 */
public class FormData  {


    Map<String, String> headers = new HashMap<>();
    String twoHyphens = "--";
    String boundary = "--ARCFormBoundaryvgrvnph55ewmi";
    String contentDisposition;
    String newLine = "\r\n";
    String contentType;
    String endOfFormData= "--";
    ByteArrayOutputStream byteArrayOutputStream ;
    DataOutputStream dataOutputStream ;
    StringBuilder formData;
    JsonWriter writer;

    public void addFile(String key,FileData file)
    {

       if(dataOutputStream ==null) {

           setFormDataHeader();

       }
        try {

            Log.d("@Ramesh ", boundary + newLine);
            dataOutputStream.writeBytes(boundary);
            dataOutputStream.writeBytes(newLine);
            formData.append(boundary);
            formData.append(newLine);

            //if (i == 1)
            contentDisposition = "Content-Disposition:form-data;name=" + "\""+ key + "\";" + "filename=" + "\"" + file.getFileName() +"\";";

            Log.d("@Ramesh ", contentDisposition + newLine);

            dataOutputStream.writeBytes(contentDisposition);
            dataOutputStream.writeBytes(newLine);
            formData.append(contentDisposition);
            formData.append(newLine);

            String filecontentType = "Content-Type: image/jpeg;";
            Log.d("@Ramesh ", filecontentType + newLine);

            dataOutputStream.writeBytes(filecontentType);
            dataOutputStream.writeBytes(newLine);
            dataOutputStream.writeBytes(newLine);

            formData.append(filecontentType);
            formData.append(newLine);
            formData.append(newLine);


            ByteArrayInputStream fileInputStream = new ByteArrayInputStream(getBitmapByteArray(file.getBitmap()));
            int bytesAvailable = fileInputStream.available();

            int maxBufferSize = 1024 * 1024;
            int bufferSize = Math.min(bytesAvailable, maxBufferSize);
            byte[] buffer = new byte[bufferSize];

            int bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            while (bytesRead > 0) {
                dataOutputStream.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }

            dataOutputStream.writeBytes(newLine);
            formData.append(getStringImage(file.getBitmap()));
            formData.append(newLine);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public void addJson(String key,JSONObject json)
    {
        //form data header
        if(dataOutputStream ==null) {
            Log.d("@MBC", "set Form data header");
            setFormDataHeader();
        }


        try {

            dataOutputStream.writeBytes(boundary);
            dataOutputStream.writeBytes(newLine);

            formData.append(boundary);
            formData.append(newLine);


            contentDisposition = "Content-Disposition:form-data; name="+"\""+key+"\";";
            Log.d("@Rmesh ", contentDisposition + newLine);
            dataOutputStream.writeBytes(contentDisposition);
            dataOutputStream.writeBytes(newLine);

            formData.append(contentDisposition);
            formData.append(newLine);

            String stringcontentType = "Content-Type: text/plan;charset=UTF-8;";
            Log.d("@Ramesh ", stringcontentType + newLine);
            dataOutputStream.writeBytes(stringcontentType);
            dataOutputStream.writeBytes(newLine);
            dataOutputStream.writeBytes(newLine);

            formData.append(stringcontentType);
            formData.append(newLine);
            formData.append(newLine);

          writer = new JsonWriter(new OutputStreamWriter(dataOutputStream, "UTF-8"));

            try {
                writer.beginObject();
                writer.name("userId").value(json.getInt("userId"));
                writer.name("kycDocumentTypeId");
                writer.beginArray();
                JSONArray jsonArray=json.getJSONArray("kycDocumentTypeId");
                for(int i=0;i<jsonArray.length();i++)
                {
                    writer.value(jsonArray.getInt(i));
                }

                writer.endArray();
                writer.endObject();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            writer.close();

            String kycdocs = "{\"userId\":634,\"kycDocumentTypeId\":[4,6,3]}";

            //kycdocs= String.valueOf(json);
            Log.d("@Ramesh ", json.toString() + newLine);


            //    dataOutputStream.writeBytes(json.toString(4));

            dataOutputStream.writeBytes(newLine);
            formData.append(kycdocs);
            formData.append(newLine);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //end of data--



    }

    public void addString(String key, String text)
    {
        //form data header
        if(formData ==null) {
            Log.d("@MBC", "set Form data header");
            setFormDataHeader();
        }

        try {
        dataOutputStream.writeBytes(boundary);
        dataOutputStream.writeBytes(newLine);

        formData.append(boundary);
        formData.append(newLine);


        contentDisposition = "Content-Disposition:form-data; name="+"\""+key+"\";";
        Log.d("@Rmesh ", contentDisposition + newLine);
        dataOutputStream.writeBytes(contentDisposition);
        dataOutputStream.writeBytes(newLine);

        formData.append(contentDisposition);
        formData.append(newLine);

        String stringcontentType = "Content-Type: text/plan;charset=UTF-8;";
        Log.d("@Ramesh ", stringcontentType + newLine);
        dataOutputStream.writeBytes(stringcontentType);
        dataOutputStream.writeBytes(newLine);
        dataOutputStream.writeBytes(newLine);

        formData.append(stringcontentType);
        formData.append(newLine);
        formData.append(newLine);



        String kycdocs = "{\"userId\":634,\"kycDocumentTypeId\":[4,6,3]}";

        //kycdocs= String.valueOf(json);
        Log.d("@Ramesh ", text + newLine);


        dataOutputStream.writeBytes(text);
        dataOutputStream.writeBytes(newLine);
        formData.append(kycdocs);
        formData.append(newLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //end of data--




    }

    private String getStringImage(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //if(file.getFileType() == FileData.FILE_TYPE.IMAGE_JPEG)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        //else
        //if(file.getFileType() == FileData.FILE_TYPE.IMAGE_PNG)
          //  file.getBitmap().compress(Bitmap.CompressFormat.PNG, 100, baos);

        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;

    }

    private byte[] getBitmapByteArray(Bitmap bitmap)
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();

    }


    public byte[] getBytes()
    {

       if(formData != null) {
           formData.append(boundary + "--");
           try {
               dataOutputStream.writeBytes(boundary + "--");

           } catch (IOException e) {
               e.printStackTrace();
           }
           Log.d("@Ramesh", boundary + endOfFormData);
           //return formData.toString().getBytes();
           return  byteArrayOutputStream.toByteArray();
       }
       return null;
    }



    public Map<String, String> getHeaders() {
        return headers;
    }

    private void setFormDataHeader() {
        Log.d("@MBC","set FormDat header" );
        formData= new StringBuilder();
         byteArrayOutputStream = new ByteArrayOutputStream();
         dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        this.headers.put("Content-Type", "multipart/form-data;boundary=ARCFormBoundaryvgrvnph55ewmi");
    }

    public String getAsString()
    {
        return  formData.toString();
    }

}
