package wang.wangxinarhat.materialdesignsamples.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wang on 2016/3/1.
 */
public class HahaBean implements Parcelable {

    private int age;
    private String school;
    private String name;
    private boolean gender;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public static Creator<HahaBean> getCREATOR() {
        return CREATOR;
    }

    public HahaBean(int age, String school, String name, boolean gender) {

        this.age = age;
        this.school = school;
        this.name = name;
        this.gender = gender;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.age);
        dest.writeString(this.school);
        dest.writeString(this.name);
        dest.writeByte(gender ? (byte) 1 : (byte) 0);
    }

    public HahaBean() {
    }

    protected HahaBean(Parcel in) {
        this.age = in.readInt();
        this.school = in.readString();
        this.name = in.readString();
        this.gender = in.readByte() != 0;
    }

    public static final Parcelable.Creator<HahaBean> CREATOR = new Parcelable.Creator<HahaBean>() {
        public HahaBean createFromParcel(Parcel source) {
            return new HahaBean(source);
        }

        public HahaBean[] newArray(int size) {
            return new HahaBean[size];
        }
    };
}
