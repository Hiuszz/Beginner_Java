package client.models;

import shared.util.Subject;

public interface Manager extends Subject {
    public void loadFromServer();
    public void upToServer();
}
