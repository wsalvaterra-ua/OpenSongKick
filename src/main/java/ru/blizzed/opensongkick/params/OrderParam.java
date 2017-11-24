package ru.blizzed.opensongkick.params;

public class OrderParam extends Param<OrderParam.Type> {

    public enum Type {
        DESC, ASC
    }

    public OrderParam() {
        super("order");
    }

}
