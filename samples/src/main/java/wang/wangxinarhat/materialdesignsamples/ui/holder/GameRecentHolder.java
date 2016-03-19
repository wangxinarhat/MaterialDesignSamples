package wang.wangxinarhat.materialdesignsamples.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.joooonho.SelectableRoundedImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import wang.wangxinarhat.materialdesignsamples.R;
import wang.wangxinarhat.materialdesignsamples.bean.GameListBean;
import wang.wangxinarhat.materialdesignsamples.utils.GlideUtils;

/**
 * Created by wang on 2016/3/8.
 */
public class GameRecentHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.item_list_recent_game_title)
    TextView title;
    @Bind(R.id.item_list_recent_game_time)
    TextView time;
    @Bind(R.id.item_list_recent_game_distance)
    TextView distance;
    @Bind(R.id.item_list_recent_game_court_name)
    TextView courtName;
    @Bind(R.id.item_list_recent_game_image)
    SelectableRoundedImageView image;

    public GameRecentHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setData(GameListBean data) {
        title.setText(data.getTitle());
        time.setText(data.getTime());
        distance.setText(data.getDistance());
        courtName.setText(data.getCourtName());

        if (data.getImage() > 0) {
            GlideUtils.loadImage(data.getImage(), image);
        }
    }
}
