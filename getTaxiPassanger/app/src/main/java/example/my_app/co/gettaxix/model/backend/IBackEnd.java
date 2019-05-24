package example.my_app.co.gettaxix.model.backend;

import example.my_app.co.gettaxix.model.entities.Ride;

public interface IBackEnd {
    void addTravel(Ride ride);
    void isExists(Ride ride,IAction<Boolean> action);
    void Test(Ride ride, IAction<String> action);
}
