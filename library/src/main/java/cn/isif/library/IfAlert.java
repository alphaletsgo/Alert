package cn.isif.library;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

public class IfAlert extends Dialog {
    AlertController alertController;

    public IfAlert(Context context, int themeResId) {
        super(context, themeResId);
        alertController = new AlertController(this, getWindow());
    }

    public static class Builder {
        private final AlertController.AlertParams mAlertParams;


        public Builder(Context context) {
            this(context, R.style.Def_IfAlert_Style);
        }

        public Builder(Context context, @StyleRes int themeRes) {
            mAlertParams = new AlertController.AlertParams(context, themeRes);
        }

        public Builder setContentView(View view) {
            mAlertParams.mView = view;
            mAlertParams.mViewLayoutResId = 0;
            return this;
        }

        public Builder setContentView(int layoutResId) {
            mAlertParams.mView = null;
            mAlertParams.mViewLayoutResId = layoutResId;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            mAlertParams.mCancelable = cancelable;
            return this;
        }


        public Builder setText(@IdRes int viewId, CharSequence text) {
            mAlertParams.mTextArr.put(viewId, text);
            return this;
        }


        public Builder setFromBottom() {
            mAlertParams.mGravity = Gravity.BOTTOM;
            return this;
        }

        public Builder setAnimation(@StyleRes int styleAnim) {
            mAlertParams.mAnimation = styleAnim;
            return this;
        }

        public Builder setHasAnimation(boolean hasAnimation) {
            mAlertParams.mHasAnimation = hasAnimation;
            return this;
        }

        public Builder setFullWidth() {
            mAlertParams.mWidth = ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }

        public Builder setWidthAndHeight(int width, int height) {
            mAlertParams.mWidth = width;
            mAlertParams.mHeight = height;
            return this;
        }

        public Builder setOnClick(@IdRes int viewId) {
            mAlertParams.mClickArr.put(mAlertParams.mClickArr.size(), viewId);
            return this;
        }

        public Builder setOnIfAlertClickListener(IfAlertClickListener ifAlertClickListener) {
            mAlertParams.ifAlertClickListener = ifAlertClickListener;
            return this;
        }

        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            mAlertParams.mOnCancelListener = onCancelListener;
            return this;
        }

        public Builder setOnOnDismissListener(OnDismissListener onDismissListener) {
            mAlertParams.mOnDismissListener = onDismissListener;
            return this;
        }

        public Builder setOnKeyListener(OnKeyListener onKeyListener) {
            mAlertParams.mOnKeyListener = onKeyListener;
            return this;
        }


        public IfAlert create() {
            final IfAlert alert = new IfAlert(mAlertParams.mContext, mAlertParams.mThemeRes);
            mAlertParams.apply(alert.alertController);
            alert.setCancelable(mAlertParams.mCancelable);
            if (mAlertParams.mCancelable) {
                alert.setCanceledOnTouchOutside(true);
            }
            alert.setOnCancelListener(mAlertParams.mOnCancelListener);
            alert.setOnDismissListener(mAlertParams.mOnDismissListener);
            if (mAlertParams.mOnKeyListener != null) {
                alert.setOnKeyListener(mAlertParams.mOnKeyListener);
            }
            return alert;
        }

        public IfAlert show() {
            IfAlert alert = create();
            alert.show();
            return alert;
        }


    }

}
