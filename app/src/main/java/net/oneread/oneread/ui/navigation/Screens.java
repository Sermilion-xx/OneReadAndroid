package net.oneread.oneread.ui.navigation;

import android.view.View;

public class Screens {

    public static Screen root(Screen screen) {
        Screen current = screen;
        while (current.getParentController() != null) {
            current = current.getParentScreen();
        }
        return current;
    }

    public static View rootView(Screen screen) {
        return root(screen).getView();
    }

}
