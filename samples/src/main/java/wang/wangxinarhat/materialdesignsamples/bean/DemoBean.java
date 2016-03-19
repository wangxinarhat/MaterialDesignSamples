package wang.wangxinarhat.materialdesignsamples.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wang on 2016/1/13.
 */
public class DemoBean implements Parcelable {
    private String title;
    private String desc;
    private int photoId;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.desc);
        dest.writeInt(this.photoId);
    }


    public DemoBean(){}

    private DemoBean(Parcel in){
        this.title = in.readString();
        this.desc = in.readString();
        this.photoId = in.readInt();
    }

    public static final Parcelable.Creator<DemoBean> CREATOR = new Creator<DemoBean>() {
        @Override
        public DemoBean createFromParcel(Parcel source) {
            return new DemoBean(source);
        }

        @Override
        public DemoBean[] newArray(int size) {
            return new DemoBean[size];
        }
    };



    /**
     * Constructs a new instance of {@code Object}.
     */
    public DemoBean(String name, String age, int photoId) {
        this.title=name;
        this.desc=age;
        this.photoId=photoId;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getDesc() {
        return desc;
    }

    public int getPhotoId() {
        return photoId;
    }

    public String getTitle() {
        return title;
    }



}
