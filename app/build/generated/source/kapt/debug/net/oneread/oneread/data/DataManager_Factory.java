package net.oneread.oneread.data;

import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;
import net.oneread.oneread.data.remote.OneReadService;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DataManager_Factory implements Factory<DataManager> {
  private final Provider<OneReadService> oneReadServiceProvider;

  public DataManager_Factory(Provider<OneReadService> oneReadServiceProvider) {
    assert oneReadServiceProvider != null;
    this.oneReadServiceProvider = oneReadServiceProvider;
  }

  @Override
  public DataManager get() {
    return new DataManager(oneReadServiceProvider.get());
  }

  public static Factory<DataManager> create(Provider<OneReadService> oneReadServiceProvider) {
    return new DataManager_Factory(oneReadServiceProvider);
  }
}
