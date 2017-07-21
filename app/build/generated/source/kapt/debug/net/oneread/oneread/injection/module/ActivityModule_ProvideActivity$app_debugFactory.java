package net.oneread.oneread.injection.module;

import android.app.Activity;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ActivityModule_ProvideActivity$app_debugFactory implements Factory<Activity> {
  private final ActivityModule module;

  public ActivityModule_ProvideActivity$app_debugFactory(ActivityModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public Activity get() {
    return Preconditions.checkNotNull(
        module.provideActivity$app_debug(),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Activity> create(ActivityModule module) {
    return new ActivityModule_ProvideActivity$app_debugFactory(module);
  }
}
