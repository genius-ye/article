# 自定义带清除按钮的EditText

### 源码：

	    package com.genius.view;

	    import android.content.Context;
	    import android.graphics.drawable.Drawable;
	    import android.text.Editable;
	    import android.text.TextWatcher;
	    import android.util.AttributeSet;
	    import android.util.Log;
	    import android.view.MotionEvent;
	    import android.view.View;
	    import android.widget.EditText;
	    
	    import com.genius.note.R;
	    
	    /**
	     * Created on 2016/3/8.
	     * 
	     * author : genius-ye
	     */
	    public class Clear_EditText extends EditText implements TextWatcher, View.OnFocusChangeListener {
	    
	        private static final String TAG = "Clear_EditText";
	        /**
	         * 右侧的删除按钮
	         **/
	        private Drawable mClearDrawable;
	        /**
	         * 手指点击的宽度(可修改后改变触摸灵敏度)
	         **/
	        private final int mWidth = 20;
	        /**
	         * 控件是否有焦点
	         */
	        private boolean hasFoucs;
	    
	        public Clear_EditText(Context context) {
	            super(context);
	            init();
	        }
	    
	        public Clear_EditText(Context context, AttributeSet attrs) {
	            super(context, attrs);
	            init();
	        }
	    
	        public Clear_EditText(Context context, AttributeSet attrs, int defStyle) {
	            super(context, attrs, defStyle);
	            init();
	        }
	    
	        private void init() {
	            // 获取EditText的DrawableRight,假如没有设置我们就使用默认的图片,获取图片的顺序是左上右下（0,1,2,3,）
	            mClearDrawable = getCompoundDrawables()[2];
	            Log.d(TAG, "mClearDrawable = " + mClearDrawable);
	            if (mClearDrawable == null) {
	                mClearDrawable = getResources().getDrawable(R.drawable.clear);
	            }
	    
	            mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
	            //默认设置隐藏图标
	            setClearIconVisible(false);
	            //设置焦点改变的监听
	            setOnFocusChangeListener(this);
	            //设置输入框里面内容发生改变的监听
	            addTextChangedListener(this);
	    
	        }
	    
	        @Override
	        public boolean onTouchEvent(MotionEvent event) {
	    
	            if (event.getAction() == MotionEvent.ACTION_UP) {
	                if (getCompoundDrawables()[2] != null) {
	                    int x = (int) event.getX();
	    //                Log.d(TAG, "x = " + x);
	    //                Log.d(TAG, "getCompoundDrawables()[2].getBounds().width()" + getCompoundDrawables()[2].getBounds().width());
	                    int distance = getWidth() - getCompoundDrawables()[2].getBounds().width();
	    //                Log.d(TAG, "distance = " + distance);
	                    // 减去手指的宽度
	                    if (x > distance - mWidth) {
	                        setText("");
	                    }
	                }
	            }
	    
	            return super.onTouchEvent(event);
	        }
	    
	        /**
	         * 当ClearEditText焦点发生变化的时候，判断里面字符串长度设置清除图标的显示与隐藏
	         */
	        @Override
	        public void onFocusChange(View v, boolean hasFocus) {
	            this.hasFoucs = hasFocus;
	            if (hasFocus) {
	                setClearIconVisible(getText().length() > 0);
	            } else {
	                setClearIconVisible(false);
	            }
	        }
	    
	    
	        /**
	         * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
	         *
	         * @param visible
	         */
	        protected void setClearIconVisible(boolean visible) {
	            Drawable right = visible ? mClearDrawable : null;
	            setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
	        }
	    
	    
	        /**
	         * 当输入框里面内容发生变化的时候回调的方法
	         */
	        @Override
	        public void onTextChanged(CharSequence s, int start, int count, int after) {
	            if (hasFoucs) {
	                setClearIconVisible(s.length() > 0);
	            }
	        }
	    
	    
	        @Override
	        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	    
	        }
	    
	        @Override
	        public void afterTextChanged(Editable s) {
	    
	        }
	    }
	    
### 布局：

	    <?xml version="1.0" encoding="utf-8"?>
	    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    	android:layout_width="match_parent"
	    	android:layout_height="match_parent"
	    	android:gravity="center">
	    
	    
	    	<com.genius.view.Clear_EditText
	    		android:id="@+id/actionbar_layout_multiAutoCompleteTextView"
	    		android:layout_width="wrap_content"
	    		android:layout_height="wrap_content"
	    		android:layout_weight="1"
	    		android:hint="search"
	    		android:visibility="gone" />
	    </LinearLayout>

> 若需要修改默认的删除按钮图标可在代码中增加 android:drawableRight="要修改的图片id";