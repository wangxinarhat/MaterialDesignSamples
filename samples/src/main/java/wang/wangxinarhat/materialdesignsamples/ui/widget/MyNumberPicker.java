package wang.wangxinarhat.materialdesignsamples.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import wang.wangxinarhat.materialdesignsamples.R;

/**
 * Created by wang on 2016/3/17.
 */
public class MyNumberPicker extends LinearLayout implements View.OnClickListener, TextWatcher {

    private int mQuantity;

    public int getmQuantity() {
        return mQuantity;
    }

    @Bind(R.id.layout_number_picker_view_minus)
    TextView minus;
    @Bind(R.id.layout_number_picker_view_plus)
    TextView plus;
    @Bind(R.id.layout_number_picker_view_quantity)
    TextView quantity;

    public MyNumberPicker(Context context) {
        super(context);
        init(context);
    }

    public MyNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyNumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyNumberPicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        View layout = inflate(context, R.layout.layout_number_picker_view, this);
        ButterKnife.bind(this, layout);

        mQuantity = 1;

        quantity.setText(String.valueOf(mQuantity));
        quantity.addTextChangedListener(this);
        minus.setOnClickListener(this);
        plus.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (R.id.layout_number_picker_view_minus == v.getId()) {

            if (mQuantity > 1) {
                mQuantity--;
            }

        } else if (R.id.layout_number_picker_view_plus == v.getId()) {
            if (mQuantity < 2) {
                mQuantity++;
            }
        }

        quantity.setText(String.valueOf(mQuantity));
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        if (1 == mQuantity) {
            plus.setClickable(true);
            minus.setClickable(false);
        } else if (2 == mQuantity) {
            plus.setClickable(false);
            minus.setClickable(true);

        } else {
            plus.setClickable(false);
            minus.setClickable(false);
        }

    }
}
