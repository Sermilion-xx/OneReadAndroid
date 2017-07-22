// Generated code from Butter Knife. Do not modify!
package net.oneread.oneread.ui.base;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BaseActivity_ViewBinding implements Unbinder {
  private BaseActivity target;

  @UiThread
  public BaseActivity_ViewBinding(BaseActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BaseActivity_ViewBinding(BaseActivity target, View source) {
    this.target = target;

    target.mProgress = Utils.findOptionalViewAsType(source, 2131558533, "field 'mProgress'", ProgressBar.class);
    target.mToolbar = Utils.findOptionalViewAsType(source, 2131558589, "field 'mToolbar'", Toolbar.class);
    target.mDrawer = Utils.findOptionalViewAsType(source, 2131558529, "field 'mDrawer'", DrawerLayout.class);
    target.navigationView = Utils.findOptionalViewAsType(source, 2131558531, "field 'navigationView'", NavigationView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BaseActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mProgress = null;
    target.mToolbar = null;
    target.mDrawer = null;
    target.navigationView = null;
  }
}
