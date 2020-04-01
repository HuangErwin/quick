package com.huang.test.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;

import com.huang.test.R;


public class Config {

    private Dialog mDialog;

//    private Activity mActivity;
    /**
     * 获取单利实例
     */
    private static Config INSTANCE;

    public static Config getInstance() {
        if(null ==INSTANCE){
            INSTANCE = new Config();
        }
        return INSTANCE;
    }

    public void showProgress(Context context) {
        if(null ==context){
            return;
        }
        if(mDialog == null) {
            mDialog = new Dialog(context, R.style.Dialog);
        }
        View view =View.inflate(context, R.layout.progressbar, null);
        mDialog.setContentView(view);
        mDialog.show();
        mDialog.setCancelable(false);
        mDialog.setOnKeyListener(onKeyListener);
    }

//    public void showProgress(Context context,String hint) {
//        if(null ==context){
//            return;
//        }
//        if(mDialog == null) {
//            mDialog = new Dialog(context, R.style.Dialog);
//        }
//        View view =View.inflate(context, R.layout.progressbar, null);
////        TextView textView = (TextView) view.findViewById(R.id.pb_text);
////        textView.setText(hint);
//        mDialog.setContentView(view);
//        mDialog.show();
//        mDialog.setCancelable(false);
//        mDialog.setOnKeyListener(onKeyListener);
//    }
    /**
     * 监听显示dialog时的返回键
     */
    private DialogInterface.OnKeyListener onKeyListener = new DialogInterface.OnKeyListener() {
        @Override
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                dismissProgress();
            }
            return false;
        }
    };
    public void dismissProgress() {

        if ( null  != mDialog  ) {
            if(mDialog.isShowing()){
                try{
                    mDialog.dismiss();
                    mDialog = null;
                }catch (Exception e){

                }

            }

        }
    }
}
