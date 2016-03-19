package wang.wangxinarhat.materialdesignsamples.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wang on 2016/3/8.
 */
public class GameListBean implements Parcelable {
    private String title;
    private String time;
    private String courtName;
    private String applyPeople;
    private String maxPeople;
    private String distance;
    private int image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public String getApplyPeople() {
        return applyPeople;
    }

    public void setApplyPeople(String applyPeople) {
        this.applyPeople = applyPeople;
    }

    public String getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(String maxPeople) {
        this.maxPeople = maxPeople;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.time);
        dest.writeString(this.courtName);
        dest.writeString(this.applyPeople);
        dest.writeString(this.maxPeople);
        dest.writeString(this.distance);
        dest.writeInt(this.image);
    }

    public GameListBean() {
    }

    public GameListBean(String title, String time, String courtName, String applyPeople, String maxPeople, String diatance, int image) {
        this.title = title;
        this.time = time;
        this.courtName = courtName;
        this.applyPeople = applyPeople;
        this.maxPeople = maxPeople;
        this.distance = diatance;
        this.image = image;
    }

    protected GameListBean(Parcel in) {
        this.title = in.readString();
        this.time = in.readString();
        this.courtName = in.readString();
        this.applyPeople = in.readString();
        this.maxPeople = in.readString();
        this.distance = in.readString();
        this.image = in.readInt();
    }

    public static final Parcelable.Creator<GameListBean> CREATOR = new Parcelable.Creator<GameListBean>() {
        public GameListBean createFromParcel(Parcel source) {
            return new GameListBean(source);
        }

        public GameListBean[] newArray(int size) {
            return new GameListBean[size];
        }
    };
}
