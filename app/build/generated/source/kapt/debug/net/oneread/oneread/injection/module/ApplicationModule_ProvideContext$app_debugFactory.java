package net.oneread.oneread.injection.module;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ApplicationModule_ProvideContext$app_debugFactory implements Factory<Context> {
  private final ApplicationModule module;

  public ApplicationModule_ProvideContext$app_debugFactory(ApplicationModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public Context get() {
    return Preconditions.checkNotNull(
        module.provideContext$app_debug(),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Context> create(ApplicationModule module) {
    return new ApplicationModule_ProvideContext$app_debugFactory(module);
  }
}
