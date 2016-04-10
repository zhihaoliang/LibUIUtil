package com.zhihaoliang.util.ui;

import android.app.Activity;
import android.content.Context;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;



/**
 * Created by haoliangzhi on 2015/8/28.
 */
public class UIUtil {

    public static int measureViewHeight(View paramView) {
        ViewGroup.LayoutParams p = paramView.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(lpHeight,
                    View.MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED);
        }
        paramView.measure(childWidthSpec, childHeightSpec);
        return paramView.getMeasuredHeight();
    }

    public static int measureViewWidth(View paramView) {
        ViewGroup.LayoutParams p = paramView.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(lpHeight,
                    View.MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED);
        }
        paramView.measure(childWidthSpec, childHeightSpec);
        return paramView.getMeasuredWidth();
    }

    public static void showToast(Context context, String text) {
        View localView = LayoutInflater.from(context).inflate(
               R.layout.common_toast, null);
        ((TextView) localView.findViewById(R.id.textview)).setText(text);
        Toast localToast = new Toast(context);
        localToast.setGravity(Gravity.CENTER, 1, 16);
        localToast.setView(localView);
        localToast.setDuration(Toast.LENGTH_SHORT);
        localToast.show();
    }

    public static void showToast(Context context, int textId) {
        View localView = LayoutInflater.from(context).inflate(
                R.layout.common_toast, null);
        ((TextView) localView.findViewById(R.id.textview)).setText(textId);
        Toast localToast = new Toast(context);
        localToast.setGravity(Gravity.CENTER, 1, 16);
        localToast.setView(localView);
        localToast.setDuration(Toast.LENGTH_SHORT);
        localToast.show();
    }

    public static View getFragmentView(Activity activity, WebView webView, WebViewClient client,String url) {
        // 支持缩放
        webView.getSettings().setSupportMultipleWindows(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);// 可以读取文件缓存(manifest生效)
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        // webView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
       /* if (activity.getScreenWidth() == 480) {
            webView.setInitialScale(15);
        }*/

        webView.setWebViewClient(client);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);
        return webView;
    }


    public static CharSequence getStyledString(Context context ,String[] text ,int[] styles ) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        for (int i = 0;i<text.length;i++){
            SpannableString spannableString = new SpannableString(text[i]);
            spannableString.setSpan(new TextAppearanceSpan(context, styles[i]), 0, text[i].length(), 0);
            builder.append(spannableString);
        }
        return builder.subSequence(0, builder.length());
    }

}
