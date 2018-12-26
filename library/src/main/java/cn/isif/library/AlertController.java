package cn.isif.library;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StyleRes;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class AlertController {
    private IfAlert mAlert;
    private Window mWindow;

    public AlertController(IfAlert alert, Window window) {
        this.mAlert = alert;
        this.mWindow = window;
    }

    public Window getWindow() {
        return mWindow;
    }

    public IfAlert getAlert() {
        return mAlert;
    }

    public static class AlertParams {
        public Context mContext;
        public int mThemeRes;//Dialog 主题,有一个默认主题
        /**
         * 存放显示文本的控件和文本内容
         */
        public SparseArray<CharSequence> mTextArr = new SparseArray<>();
        /**
         * 存放点击事件的控件和监听
         */
        public SparseIntArray mClickArr = new SparseIntArray();

        /**
         * 点击空白是否可以取消,默认不可以
         */
        public boolean mCancelable = false;
        /**
         * Dialog 取消监听
         */
        public DialogInterface.OnCancelListener mOnCancelListener;
        /**
         * Dialog 消失监听
         */
        public DialogInterface.OnDismissListener mOnDismissListener;
        /**
         * Dialog 按键监听
         */
        public DialogInterface.OnKeyListener mOnKeyListener;
        /**
         * Dialog 布局 View
         */
        public View mView;

        /**
         * Dialog 布局 ID
         */
        public int mViewLayoutResId;
        public int mWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
        public int mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
        public int mGravity = Gravity.CENTER;
        public int mAnimation = R.style.IfAlertAnimation;
        public boolean mHasAnimation = true;
        public IfAlertClickListener ifAlertClickListener;

        public AlertParams(Context context, @StyleRes int mThemeRes) {
            this.mContext = context;
            this.mThemeRes = mThemeRes;

        }

        public void apply(AlertController controller) {
            IfAlertViewHelper viewHelper = null;
            if (0 != mViewLayoutResId) {
                viewHelper = new IfAlertViewHelper(mContext, mViewLayoutResId);
            }

            if (null != mView) {
                viewHelper = new IfAlertViewHelper(mContext, mView);
            }

            if (null == viewHelper) {
                throw new IllegalArgumentException("请设置Dialog布局");
            }

            controller.getAlert().setContentView(viewHelper.getContentView());

            viewHelper.setOnIfAlertClickListener(ifAlertClickListener);
            // 2. 设置文本
            for (int i = 0, len = mTextArr.size(); i < len; i++) {
                viewHelper.setText(mTextArr.keyAt(i), mTextArr.valueAt(i));
            }

            // 3. 设置点击事件
            for (int i = 0, len = mClickArr.size(); i < len; i++) {
                viewHelper.setOnClick(mClickArr.keyAt(i), mClickArr.valueAt(i));
            }

            // 4. 设置dialog宽高动画等
            Window window = controller.getWindow();
            window.setGravity(mGravity);
            if (mHasAnimation) {
                window.setWindowAnimations(mAnimation);
            }
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = mWidth;
            params.height = mHeight;
            window.setAttributes(params);
            controller.getAlert().setOnCancelListener(mOnCancelListener);
            controller.getAlert().setOnDismissListener(mOnDismissListener);
        }
    }
}
