package jack.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * by shenmingliang1
 * 2018.07.25 20:25.
 */
public class MultiLineRadioGroup extends RadioGroup {
    private MultiLineRadioGroup.OnMultiCheckedChangeListener mOnCheckedChangeListener;

    public MultiLineRadioGroup(Context context) {
        this(context, null);
    }

    public MultiLineRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnCheckedChangeListener(OnMultiCheckedChangeListener onCheckedChangeListener) {
        mOnCheckedChangeListener = onCheckedChangeListener;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof LinearLayout) {
            for (int i = 0; i < ((LinearLayout) child).getChildCount(); i++) {
                View view = ((LinearLayout) child).getChildAt(i);
                if (view instanceof RadioButton) {
                    final RadioButton button = (RadioButton) view;
                    button.setOnTouchListener(new OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                button.setChecked(!button.isChecked());
                                checkRadioButton(button);
                                if (mOnCheckedChangeListener != null) {
                                    mOnCheckedChangeListener.onCheckedChanged(
                                            MultiLineRadioGroup.this, button.getId(),
                                            button.isChecked());
                                }
                            }
                            return true;
                        }
                    });
                }
            }
        }

        super.addView(child, index, params);
    }

    private void checkRadioButton(RadioButton button) {
        View child;
        int radioCount = getChildCount();
        for (int i = 0; i < radioCount; i++) {
            child = getChildAt(i);
            if (child instanceof RadioButton) {
                if (child == button) {
                    //nothing here.
                } else {
                    ((RadioButton) child).setChecked(false);
                }
            } else if (child instanceof LinearLayout) {
                int childCount = ((LinearLayout) child).getChildCount();
                for (int j = 0; j < childCount; j++) {
                    View view = ((LinearLayout) child).getChildAt(j);
                    if (view instanceof RadioButton) {
                        final RadioButton b = (RadioButton) view;
                        if (button == b) {
                            //nothing here.
                        } else {
                            b.setChecked(false);
                        }
                    }
                }
            }
        }
    }

    public interface OnMultiCheckedChangeListener {
        void onCheckedChanged(MultiLineRadioGroup group, int buttonId, boolean isChecked);
    }
}
