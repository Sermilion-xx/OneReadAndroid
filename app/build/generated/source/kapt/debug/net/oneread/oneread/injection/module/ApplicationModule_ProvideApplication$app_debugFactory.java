package net.oneread.oneread.injection.module;

import android.app.Application;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ApplicationModule_ProvideApplication$app_debugFactory
    implements Factory<Application> {
  private final ApplicationModule module;

  public ApplicationModule_ProvideApplication$app_debugFactory(ApplicationModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public Application get() {
    return Preconditions.checkNotNull(
        module.provideApplication$app_debug(),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Application> create(ApplicationModule module) {
    return new ApplicationModule_ProvideApplication$app_debugFactory(module);
  }
}
