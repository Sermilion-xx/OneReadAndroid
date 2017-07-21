// Generated code from Butter Knife. Do not modify!
package net.oneread.oneread.ui.base;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
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

    target.mDrawer = Utils.findOptionalViewAsType(source, 2131558522, "field 'mDrawer'", DrawerLayout.class);
    target.navigationView = Utils.findOptionalViewAsType(source, 2131558524, "field 'navigationView'", NavigationView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BaseActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mDrawer = null;
    target.navigationView = null;
  }
}
