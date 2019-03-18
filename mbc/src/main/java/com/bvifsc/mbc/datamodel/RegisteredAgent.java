package com.bvifsc.mbc.datamodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramesh on 14-01-2019.
 */
public class RegisteredAgent implements Parcelable{
    @SerializedName("entityId")
    int  entityId = 5;
    String entityName;

    public RegisteredAgent(){}

    protected RegisteredAgent(Parcel in) {
        entityId = in.readInt();
        entityName = in.readString();
    }

    public static final Creator<RegisteredAgent> CREATOR = new Creator<RegisteredAgent>() {
        @Override
        public RegisteredAgent createFromParcel(Parcel in) {
            return new RegisteredAgent(in);
        }

        @Override
        public RegisteredAgent[] newArray(int size) {
            return new RegisteredAgent[size];
        }
    };

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(entityId);
        dest.writeString(entityName);
    }
}
