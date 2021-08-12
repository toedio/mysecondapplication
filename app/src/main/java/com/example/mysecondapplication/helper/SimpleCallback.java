package com.example.mysecondapplication.helper;

public abstract class SimpleCallback<T> {

    public abstract void call();

    public Boolean success;
    public T object;
}
