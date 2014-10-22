// Generated code from Butter Knife. Do not modify!
package com.example.weixin;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ActivityMain$$ViewInjector {
  public static void inject(Finder finder, final com.example.weixin.ActivityMain target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296268, "field 'll_found' and method 'ClickFound'");
    target.ll_found = (android.widget.LinearLayout) view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.ClickFound();
        }
      });
    view = finder.findRequiredView(source, 2131296271, "field 'll_own' and method 'ClickOwn'");
    target.ll_own = (android.widget.LinearLayout) view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.ClickOwn();
        }
      });
    view = finder.findRequiredView(source, 2131296264, "field 'tv_weixin'");
    target.tv_weixin = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296267, "field 'tv_contacts'");
    target.tv_contacts = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296265, "field 'll_contacts' and method 'ClickContacts'");
    target.ll_contacts = (android.widget.LinearLayout) view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.ClickContacts();
        }
      });
    view = finder.findRequiredView(source, 2131296270, "field 'tv_found'");
    target.tv_found = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296262, "field 'll_weixin' and method 'ClickWeiXin'");
    target.ll_weixin = (android.widget.LinearLayout) view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.ClickWeiXin();
        }
      });
    view = finder.findRequiredView(source, 2131296273, "field 'tv_own'");
    target.tv_own = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296261, "field 'mPager'");
    target.mPager = (android.support.v4.view.ViewPager) view;
  }

  public static void reset(com.example.weixin.ActivityMain target) {
    target.ll_found = null;
    target.ll_own = null;
    target.tv_weixin = null;
    target.tv_contacts = null;
    target.ll_contacts = null;
    target.tv_found = null;
    target.ll_weixin = null;
    target.tv_own = null;
    target.mPager = null;
  }
}
