package example.my_app.co.gettaxix.model.backend;

public interface IAction<T> {
    void onSuccess(T obj);
    void onFailure(Exception exception);
}
