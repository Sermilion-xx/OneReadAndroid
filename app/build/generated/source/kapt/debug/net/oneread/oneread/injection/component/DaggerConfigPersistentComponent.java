package net.oneread.oneread.injection.component;

import dagger.internal.Preconditions;
import javax.annotation.Generated;
import net.oneread.oneread.injection.module.ActivityModule;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerConfigPersistentComponent implements ConfigPersistentComponent {
  private DaggerConfigPersistentComponent(Builder builder) {
    assert builder != null;
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public ActivityComponent activityComponent(ActivityModule activityModule) {
    return new ActivityComponentImpl(activityModule);
  }

  public static final class Builder {
    private ApplicationComponent applicationComponent;

    private Builder() {}

    public ConfigPersistentComponent build() {
      if (applicationComponent == null) {
        throw new IllegalStateException(
            ApplicationComponent.class.getCanonicalName() + " must be set");
      }
      return new DaggerConfigPersistentComponent(this);
    }

    public Builder applicationComponent(ApplicationComponent applicationComponent) {
      this.applicationComponent = Preconditions.checkNotNull(applicationComponent);
      return this;
    }
  }

  private final class ActivityComponentImpl implements ActivityComponent {
    private final ActivityModule activityModule;

    private ActivityComponentImpl(ActivityModule activityModule) {
      this.activityModule = Preconditions.checkNotNull(activityModule);
    }
  }
}
