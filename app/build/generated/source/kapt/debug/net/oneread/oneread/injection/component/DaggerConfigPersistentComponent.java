package net.oneread.oneread.injection.component;

import android.content.Context;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import net.oneread.oneread.data.DataManager;
import net.oneread.oneread.injection.module.ActivityModule;
import net.oneread.oneread.ui.registration.RegActivity;
import net.oneread.oneread.ui.registration.RegActivity_MembersInjector;
import net.oneread.oneread.ui.registration.RegPresenter;
import net.oneread.oneread.ui.registration.RegPresenter_Factory;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerConfigPersistentComponent implements ConfigPersistentComponent {
  private Provider<DataManager> dataManagerProvider;

  private Provider<Context> contextProvider;

  private Provider<RegPresenter> regPresenterProvider;

  private DaggerConfigPersistentComponent(Builder builder) {
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {

    this.dataManagerProvider =
        new Factory<DataManager>() {
          private final ApplicationComponent applicationComponent = builder.applicationComponent;

          @Override
          public DataManager get() {
            return Preconditions.checkNotNull(
                applicationComponent.dataManager(),
                "Cannot return null from a non-@Nullable component method");
          }
        };

    this.contextProvider =
        new Factory<Context>() {
          private final ApplicationComponent applicationComponent = builder.applicationComponent;

          @Override
          public Context get() {
            return Preconditions.checkNotNull(
                applicationComponent.context(),
                "Cannot return null from a non-@Nullable component method");
          }
        };

    this.regPresenterProvider =
        DoubleCheck.provider(
            RegPresenter_Factory.create(
                MembersInjectors.<RegPresenter>noOp(), dataManagerProvider, contextProvider));
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

    private MembersInjector<RegActivity> regActivityMembersInjector;

    private ActivityComponentImpl(ActivityModule activityModule) {
      this.activityModule = Preconditions.checkNotNull(activityModule);
      initialize();
    }

    @SuppressWarnings("unchecked")
    private void initialize() {

      this.regActivityMembersInjector =
          RegActivity_MembersInjector.create(
              DaggerConfigPersistentComponent.this.regPresenterProvider);
    }

    @Override
    public void inject(RegActivity regActivity) {
      regActivityMembersInjector.injectMembers(regActivity);
    }
  }
}
