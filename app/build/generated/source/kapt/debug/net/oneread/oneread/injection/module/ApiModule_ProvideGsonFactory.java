package net.oneread.oneread.injection.module;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ApiModule_ProvideGsonFactory implements Factory<Gson> {
  private final ApiModule module;

  public ApiModule_ProvideGsonFactory(ApiModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public Gson get() {
    return Preconditions.checkNotNull(
        module.provideGson(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Gson> create(ApiModule module) {
    return new ApiModule_ProvideGsonFactory(module);
  }
}
