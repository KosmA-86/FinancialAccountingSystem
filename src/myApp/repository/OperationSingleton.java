package myApp.repository;

import myApp.domain.Operation;

/**
 * Синголтон класс, для получения информации по операциям
 */
public class OperationSingleton {

    private final static OperationSingleton INSTANCE = new OperationSingleton();
    private Operation operation;

    private OperationSingleton() {
        //do nothing
    }

    public static OperationSingleton getFindOperation() {
        return INSTANCE;
    }

    public Operation getOperation() {

        return this.operation;
    }

    public void setOperation(Operation operation) {

        this.operation = operation;
    }

}
