package net.oneread.oneread.injection.component;

import android.app.Application;
import android.content.Context;
import com.google.gson.Gson;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import net.oneread.oneread.data.DataManager;
import net.oneread.oneread.data.DataManager_Factory;
import net.oneread.oneread.data.remote.OneReadService;
import net.oneread.oneread.injection.module.ApiModule;
import net.oneread.oneread.injection.module.ApiModule_ProvideGsonFactory;
import net.oneread.oneread.injection.module.ApiModule_ProvideOkHttpClientFactory;
import net.oneread.oneread.injection.module.ApiModule_ProvideOneAccountServiceFactory;
import net.oneread.oneread.injection.module.ApplicationModule;
import net.oneread.oneread.injection.module.ApplicationModule_ProvideApplication$app_debugFactory;
import net.oneread.oneread.injection.module.ApplicationModule_ProvideContext$app_debugFactory;
import net.oneread.oneread.injection.module.DataModule;
import okhttp3.OkHttpClient;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerApplicationComponent implements ApplicationComponent {
  private Provider<Context> provideContext$app_debugProvider;

  private Provider<Application> provideApplication$app_debugProvider;

  private Provider<OkHttpClient> provideOkHttpClientProvider;

  private Provider<Gson> provideGsonProvider;

  private Provider<OneReadService> provideOneAccountServiceProvider;

  private Provider<DataManager> dataManagerProvider;

  private DaggerApplicationComponent(Builder builder) {
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {

    this.provideContext$app_debugProvider =
        DoubleCheck.provider(
            ApplicationModule_ProvideContext$app_debugFactory.create(builder.applicationModule));

    this.provideApplication$app_debugProvider =
        DoubleCheck.provider(
            ApplicationModule_ProvideApplication$app_debugFactory.create(
                builder.applicationModule));

    this.provideOkHttpClientProvider =
        DoubleCheck.provider(ApiModule_ProvideOkHttpClientFactory.create(builder.apiModule));

    this.provideGsonProvider =
        DoubleCheck.provider(ApiModule_ProvideGsonFactory.create(builder.apiModule));

    this.provideOneAccountServiceProvider =
        DoubleCheck.provider(
            ApiModule_ProvideOneAccountServiceFactory.create(
                builder.apiModule, provideOkHttpClientProvider, provideGsonProvider));

    this.dataManagerProvider =
        DoubleCheck.provider(DataManager_Factory.create(provideOneAccountServiceProvider));
  }

  @Override
  public Context context() {
    return provideContext$app_debugProvider.get();
  }

  @Override
  public Application application() {
    return provideApplication$app_debugProvider.get();
  }

  @Override
  public OneReadService oneAccountService() {
    return provideOneAccountServiceProvider.get();
  }

  @Override
  public DataManager dataManager() {
    return dataManagerProvider.get();
  }

  public static final class Builder {
    private ApplicationModule applicationModule;

    private ApiModule apiModule;

    private Builder() {}

    public ApplicationComponent build() {
      if (applicationModule == null) {
        throw new IllegalStateException(
            ApplicationModule.class.getCanonicalName() + " must be set");
      }
      if (apiModule == null) {
        this.apiModule = new ApiModule();
      }
      return new DaggerApplicationComponent(this);
    }

    public Builder applicationModule(ApplicationModule applicationModule) {
      this.applicationModule = Preconditions.checkNotNull(applicationModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This
     *     method is a no-op. For more, see https://google.github.io/dagger/unused-modules.
     */
    @Deprecated
    public Builder dataModule(DataModule dataModule) {
      Preconditions.checkNotNull(dataModule);
      return this;
    }

    public Builder apiModule(ApiModule apiModule) {
      this.apiModule = Preconditions.checkNotNull(apiModule);
      return this;
    }
  }
}