package com.bjxiyang.zhinengshequ.myapplication.widgets;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.bjxiyang.zhinengshequ.R;

import java.util.ArrayList;
import java.util.List;


/**
 * ActionSheet for Android.
 * @author haohang (msdx.android@qq.com)
 * @version 0.2
 * @since 0.1
 */
public class CommonActionSheetDialog extends Dialog {
    private Button mCancel;
    private ListView mMenuItems;
    public MenutAdapter mAdapter;

    public MenuListener mMenuListener;
    public View mRootView;

    public MenuBackground mMenuBg = new MenuBackground(R.drawable.menu_item_top,
            R.drawable.menu_item_middle, R.drawable.menu_item_bottom, R.drawable.menu_item_single);
    private Animation mShowAnim;
    private Animation mDismissAnim;

    private boolean isDismissing;

    public List<String> enumlist = new ArrayList<>();

    public CommonActionSheetDialog(Context context) {
        super(context, R.style.ActionSheetDialog);
        getWindow().setGravity(Gravity.BOTTOM);
        initView(context);
    }



    public void commentView(Context context)
    {
        mMenuItems = (ListView) mRootView.findViewById(R.id.menu_items);
        mAdapter = new MenutAdapter();


//        mAdapter = new ArrayAdapter(context, R.layout.menu_item) {
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                View view = super.getView(position, convertView, parent);
//                setBackground(position, view);
//                return view;
//            }
//
//            private void setBackground(int position, View view) {
//                int count = getCount();
//                if (count == 1) {
//                    view.setBackgroundResource(mMenuBg.single);
//                } else if (position == 0) {
//                    view.setBackgroundResource(mMenuBg.top);
//                } else if (position == count - 1) {
//                    view.setBackgroundResource(mMenuBg.bottom);
//                } else {
//                    view.setBackgroundResource(mMenuBg.middle);
//                }
//            }
//        };
        mMenuItems.setAdapter(mAdapter);
    }

    private void initView(Context context) {
        mRootView = View.inflate(context, R.layout.dialog_common_action_sheet, null);
        mCancel = (Button) mRootView.findViewById(R.id.menu_cancel);
        mCancel.setText("取消");
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

        commentView(context);

        Resources res = context.getResources();
        Drawable grayshape = res.getDrawable(R.drawable.grayshape);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mRootView.setBackground(res.getDrawable(R.drawable.shape,null));
        }else{
            mRootView.setBackground(grayshape);
        }
        this.setContentView(mRootView);
        initAnim(context);
        mMenuItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mMenuListener != null) {

                    mMenuListener.onItemSelected(position, null);
                    dismiss();
                }
            }
        });
        setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (mMenuListener != null) {
                    mMenuListener.onCancel();
                }
            }
        });
    }

    private void initAnim(Context context) {
        setShowAnimation(AnimationUtils.loadAnimation(context, R.anim.translate_up));
        setDismissAnimation(AnimationUtils.loadAnimation(context, R.anim.translate_down));
    }

    /**
     * @param animation Showing animation.
     * @since 0.2
     */
    public void setShowAnimation(Animation animation) {
        mShowAnim = animation;
    }

    /**
     * @param animation Dismissing animation.
     * @since 0.2
     */
    public void setDismissAnimation(Animation animation) {
        mDismissAnim = animation;
        mDismissAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                dismissMe();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * Add menu item.
     * @param item The text of the item be added.
     * @return
     * @since 0.1
     */
    public CommonActionSheetDialog addMenuItem(String item) {

        enumlist.add(item);
        return this;
    }

    /**
     * Show or dismiss menu.
     * @since 0.1
     */
    public void toggle() {
        if (isShowing()) {
            dismiss();
        } else {
            show();
        }
    }

    @Override
    public void show() {
        mAdapter.notifyDataSetChanged();
        super.show();
        mRootView.startAnimation(mShowAnim);
    }

    @Override
    public void dismiss() {
        if(isDismissing) {
            return;
        }
        isDismissing = true;
        mRootView.startAnimation(mDismissAnim);
    }

    private void dismissMe() {
        super.dismiss();
        isDismissing = false;
    }

    /**
     * Return the menu listener.
     * @return
     */
    public MenuListener getMenuListener() {
        return mMenuListener;
    }

    /**
     * Set the menu listener
     * @param menuListener
     * @isnce 0.1
     */
    public void setMenuListener(MenuListener menuListener) {
        mMenuListener = menuListener;
    }

    /**
     * Set the menu item background.
     * @param top The top item's background.
     * @param middle The middle item's background.
     * @param bottom The bottom item's background.
     * @param single The background of the item, if there is only one in menu.
     * @since 0.2
     */
    public void setMenuBackground(int top, int middle, int bottom, int single) {
        mMenuBg.top = top;
        mMenuBg.middle = middle;
        mMenuBg.bottom = bottom;
        mMenuBg.single = single;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            dismiss();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    /**
     * Menu listener.
     * @since 0.1
     */
    public interface MenuListener {
        /**
         * When one of the menu items is selected, this method is called.
         * @param position the position of the menu item
         * @param item the text of the menu item
         * @since 0.1
         */
        void onItemSelected(int position, String item);

        /**
         * This method is called when cancel the menu.
         * @since 0.1
         */
        void onCancel();
    }

    /**
     * The background of each item of menu.
     * @since 0.2
     */
    public static class MenuBackground {
        public int top;
        public int middle;
        public int bottom;
        public int single;

        public MenuBackground() {}

        public MenuBackground(int top, int middle, int bottom, int single) {
            this.top = top;
            this.middle = middle;
            this.bottom = bottom;
            this.single = single;
        }
    }

    private class MenutAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return enumlist.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SimpleTextViewItem item = null;
            if (convertView == null){
                convertView =  LayoutInflater.from(getContext()).inflate(R.layout.menu_item,parent,false);
                item = new SimpleTextViewItem();
                item.textView = (TextView ) convertView.findViewById(R.id.text1);
                convertView.setTag(item);
            }else{
                item = (SimpleTextViewItem) convertView.getTag();
            }
            item.textView.setText(enumlist.get(position));
//            item.textView.settextc
            return convertView;
        }
    }
    private class SimpleTextViewItem{
        TextView textView;
    }
}
