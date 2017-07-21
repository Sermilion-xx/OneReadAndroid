package net.oneread.oneread.injection.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import okhttp3.OkHttpClient;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ApiModule_ProvideOkHttpClientFactory implements Factory<OkHttpClient> {
  private final ApiModule module;

  public ApiModule_ProvideOkHttpClientFactory(ApiModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public OkHttpClient get() {
    return Preconditions.checkNotNull(
        module.provideOkHttpClient(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<OkHttpClient> create(ApiModule module) {
    return new ApiModule_ProvideOkHttpClientFactory(module);
  }
}
