package wang.wangxinarhat.materialdesignsamples.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import wang.wangxinarhat.materialdesignsamples.R;
import wang.wangxinarhat.materialdesignsamples.global.BaseApplication;
import wang.wangxinarhat.materialdesignsamples.ui.widget.MyNumberPicker;
import wang.wangxinarhat.materialdesignsamples.utils.EditUtils;
import wang.wangxinarhat.materialdesignsamples.utils.MyToast;

/**
 * Created by wang on 2016/3/4.
 */
public class EditTextDecimalLimitActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.edittext0)
    EditText editText0;
    @Bind(R.id.edittext1)
    EditText editText1;
    @Bind(R.id.btn)
    Button btn;
    @Bind(R.id.display_result)
    TextView inputResult;
    @Bind(R.id.number_picker)
    MyNumberPicker numberPicker;

    public static Intent getStartIntent() {
        Intent intent = new Intent();
        intent.setClass(BaseApplication.getApplication(), EditTextDecimalLimitActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edittext_demo);

        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        EditUtils.bindInputFilter(editText0);

        String digists = "0123456789abcdefghigklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        editText1.setKeyListener(DigitsKeyListener.getInstance(digists));


        btn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        inputResult.setText(editText0.getText().toString());

        MyToast.showShortToast(numberPicker.getmQuantity()+"");

    }
}
