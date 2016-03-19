package wang.wangxinarhat.materialdesignsamples.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by wang on 2016/3/3.
 */
public class BaseFragment extends Fragment {


    protected Context mContext;
    protected Activity mActivity;
    protected FragmentManager fm;

    protected BackHandlerInterface backHandlerInterface;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
        try {
            backHandlerInterface = (BackHandlerInterface) getActivity();
        } catch (Exception e) {
            throw new ClassCastException("Hosting activity must implement BackHandlerInterface");
        }


        backHandlerInterface.setSelectedFragment(this);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
    }


    public interface BackHandlerInterface {
        void setSelectedFragment(BaseFragment backHandledFragment);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getActivity().getSupportFragmentManager();

        if (!(getActivity() instanceof BackHandlerInterface)) {
            throw new ClassCastException("Hosting activity must implement BackHandlerInterface");
        } else {
            backHandlerInterface = (BackHandlerInterface) getActivity();
        }


    }


    public boolean onBackPressed() {
        if (1 == fm.getBackStackEntryCount()) {

            getActivity().finish();
            return true;
        }
        return false;
    }


}
