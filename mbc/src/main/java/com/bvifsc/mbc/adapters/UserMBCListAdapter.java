package com.bvifsc.mbc.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bvifsc.core.R;
import com.bvifsc.mbc.Common.FragmentLoader;
import com.bvifsc.mbc.Common.MBC_Constants;
import com.bvifsc.mbc.Common.SharedPreferencesUtils;
import com.bvifsc.mbc.assemblers.MBC_PageLoder;
import com.bvifsc.mbc.pageModels.MBCView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Ramesh on 28-01-2019.
 */
public class UserMBCListAdapter extends RecyclerView.Adapter<UserMBCListAdapter.UserMBCListViewHolder> {
        Context context;
        List<String> userMBCs;
        SharedPreferencesUtils sharedPreferencesUtils;
        EventBus eventBus;

    public UserMBCListAdapter(Context context, List<String> userMBCs,SharedPreferencesUtils sharedPreferencesUtils ,EventBus eventBus)
    {
        this.context=context;
        this.userMBCs=userMBCs;
        this.sharedPreferencesUtils=sharedPreferencesUtils;
        this.eventBus=eventBus;

    }
    @NonNull
    @Override
    public UserMBCListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.user_mbc_list_row,null);

        return new UserMBCListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserMBCListViewHolder userMBCListViewHolder, int i) {
        String mbcNumber=userMBCs.get(i);
        userMBCListViewHolder.mbcNumber.setText(userMBCs.get(i));

               ClickableSpan onTextClickListener = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                try {
                    FragmentLoader fragmentLoader=(FragmentLoader) MBC_PageLoder.PAGE_MAPPING.get(MBC_Constants.MBC_VIEW).newInstance();
                    ((MBCView)fragmentLoader).setMbcNumber(((TextView) widget).getText().toString());
                    fragmentLoader.load();

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
            }
        };
        linkifyText(userMBCListViewHolder.mbcNumber,mbcNumber,onTextClickListener);

    }
    void linkifyText(TextView view, String text, ClickableSpan onTextLinkClickListener)
    {
        SpannableString spannableString = new SpannableString(text);
        int matcherStart = 0;
        int matcherEnd=text.length();

        spannableString.setSpan(onTextLinkClickListener, matcherStart, matcherEnd , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setText(spannableString);
        view.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public int getItemCount() {
        Log.d("@MBC", "item count : " +userMBCs.size());
        return userMBCs.size();
    }

    class UserMBCListViewHolder extends RecyclerView.ViewHolder {
        TextView mbcNumber;

        public UserMBCListViewHolder(@NonNull View itemView) {
            super(itemView);
            mbcNumber=itemView.findViewById(R.id.mbc_number);




        }

    }
}
