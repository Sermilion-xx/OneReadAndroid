package net.oneread.oneread.injection.module;

import android.app.Application;
import android.content.SharedPreferences;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DataModule_ProvideSharedPreferencesFactory
    implements Factory<SharedPreferences> {
  private final DataModule module;

  private final Provider<Application> appProvider;

  public DataModule_ProvideSharedPreferencesFactory(
      DataModule module, Provider<Application> appProvider) {
    assert module != null;
    this.module = module;
    assert appProvider != null;
    this.appProvider = appProvider;
  }

  @Override
  public SharedPreferences get() {
    return Preconditions.checkNotNull(
        module.provideSharedPreferences(appProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<SharedPreferences> create(
      DataModule module, Provider<Application> appProvider) {
    return new DataModule_ProvideSharedPreferencesFactory(module, appProvider);
  }
}
