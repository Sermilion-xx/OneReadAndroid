package net.oneread.oneread.injection.module;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import net.oneread.oneread.data.remote.OneReadService;
import okhttp3.OkHttpClient;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ApiModule_ProvideOneAccountServiceFactory implements Factory<OneReadService> {
  private final ApiModule module;

  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<Gson> gsonProvider;

  public ApiModule_ProvideOneAccountServiceFactory(
      ApiModule module, Provider<OkHttpClient> okHttpClientProvider, Provider<Gson> gsonProvider) {
    assert module != null;
    this.module = module;
    assert okHttpClientProvider != null;
    this.okHttpClientProvider = okHttpClientProvider;
    assert gsonProvider != null;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public OneReadService get() {
    return Preconditions.checkNotNull(
        module.provideOneAccountService(okHttpClientProvider.get(), gsonProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<OneReadService> create(
      ApiModule module, Provider<OkHttpClient> okHttpClientProvider, Provider<Gson> gsonProvider) {
    return new ApiModule_ProvideOneAccountServiceFactory(
        module, okHttpClientProvider, gsonProvider);
  }
}
