package client.views.menu;

public class MenuViewModel {
    private MenuView menuView;
    public MenuViewModel() {
        menuView = new MenuView();
    }

    public MenuView getMenuView() {
        return menuView;
    }
}
